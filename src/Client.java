import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    static CreateGUI gui = new CreateGUI();
    static String name;
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server.");
            //out.println(name); // Send the client name to the server

            gui.buildUserSelect(400, 400);

            Thread receiveThread = new Thread(() -> {
                try {
                    String message;

                    while ((message = in.readLine()) != null) {
                        String[] parts = message.split(":", 2);
                        String senderName = parts[0];
                        String messageContent = parts[1];

                        System.out.println("Message received: " + messageContent);
                        gui.SendMessageFromServer(messageContent, senderName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            while (true) {
                // Check if there are outgoing messages from GUI
                if (!gui.chatOut.isEmpty()) {
                    for (Message message : gui.chatOut) {
                        out.println(name + ":" + message.messageContent.getText());
                    }
                    out.flush(); // Flush the output stream to ensure data is sent immediately
                    gui.chatOut.clear();
                }

                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
