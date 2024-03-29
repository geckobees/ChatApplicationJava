import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int SERVER_PORT = 12346;

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
                ) {
                    System.out.println("Client connected: " + clientSocket);


                    String username = (String) in.readObject();
                    System.out.println("Username received from client: " + username);

                    while (in.available() > 0) {

                        String message = (String) in.readObject();
                        System.out.println("Message received from client: " + message);

                        String sender = (String) in.readObject();
                        System.out.println("Sender received from client: " + sender);

                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException e) {
                    System.out.println("Client disconnected abruptly.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//TODO: fix code that keeps reading the client stream in an infinite loop.