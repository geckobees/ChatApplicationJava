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
        ) {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
            out.write(Client.name);

            gui.buildGUI(400, 400);

            while (true) {
                if (!gui.chatOut.isEmpty()) {
                    for (Message message : gui.chatOut) { // nothing here that is a bug
                        out.println(message.messageContent.getText());
                        out.flush();
                        String response = in.readLine();
                        System.out.println(response);
                    }
                    gui.chatOut.clear();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
