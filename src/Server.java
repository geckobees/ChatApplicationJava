import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (
                ServerSocket serverSocket = new ServerSocket(12346);
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            System.out.println("Server started. Waiting for client connections...");

            System.out.println("Client connected: " + clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
