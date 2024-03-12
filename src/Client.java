import java.io.*;
import java.net.*;

public class Client extends User {
    static CreateGUI gui = new CreateGUI();

    public static void main(String[] args) {
        Client.name = "bob";
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Connected to server.");
            out.writeObject(Client.name);

            gui.buildGUI(400, 400);

            while (true) {
                System.out.println("Checking for new messages in chatOut");
                if (!gui.chatOut.isEmpty()) {
                    System.out.println("New messages found. Sending to server");
                    for (Message message : gui.chatOut) {
                        System.out.println("Message content: " + message.messageContent.getText());
                        System.out.println("Sender: " + message.senderLabel.getText());
                        out.writeObject(message.messageContent.getText());
                        out.flush();
                    }
                    gui.chatOut.clear();

                } else {
                    System.out.println("No new messages in chatOut. Waiting...");
                }

                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
