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
                    System.out.println("Client connected: " + clientSocket);

                    Thread clientThread = new Thread(() -> HandleClient(clientSocket));
                    clientThread.start();
                    //serverSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void HandleClient(Socket ClientSocket){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(ClientSocket.getOutputStream(), true);
            String message;

            while ((message = in.readLine()) != null) {
                out.println(message);
            }

            in.close();
            out.close();
            ClientSocket.close();
        }catch(IOException e){
            System.out.println("a user has disconnceted");
            e.printStackTrace();
        }
    }
}
//TODO: fix code that keeps reading the client stream in an infinite loop.