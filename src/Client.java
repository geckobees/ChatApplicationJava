import java.io.*;
import java.net.*;

public class Client {
    static CreateGUI gui = new CreateGUI();
    static String name = "bob"; // Assuming User is a class with a static field 'name'

    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server.");
            out.println(name); // Send the client name to the server

            gui.buildGUI(400, 400);
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Message received: " + message);
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
                        out.println(message.messageContent.getText());
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
