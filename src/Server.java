import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ) {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received from client: " + message);
                        out.println("Echo from server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    clientSocket.close();
                    System.out.println("Client disconnected.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
