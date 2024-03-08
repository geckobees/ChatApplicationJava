import java.io.*;
import java.net.*;

public class Client extends User{
    static Message message = new Message();
    public static void main(String[] args) {
        Client.name = "bob";
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            System.out.println("Connected to server.");
            out.writeObject(Client.name);
            CreateGUI gui = new CreateGUI();
            gui.buildGUI(400, 400);

            if (message.messageContent != null){
                out.writeObject(message.messageContent.getText());
                out.writeObject(message.senderLabel.getText());
                out.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
