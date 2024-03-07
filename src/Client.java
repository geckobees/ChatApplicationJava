import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12346;

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            System.out.println("Connected to server.");

            // Assuming you have a reference to your GUI instance
            CreateGUI gui = new CreateGUI();
            gui.buildGUI(400, 400);
            out.writeObject(gui.message);
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
