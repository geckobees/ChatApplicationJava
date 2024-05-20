import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int SERVER_PORT = 12346;

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket);
                    System.out.println(in.readLine());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//TODO: fix code that keeps reading the client stream in an infinite loop.