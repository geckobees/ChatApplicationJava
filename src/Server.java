import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (
                ServerSocket serverSocket = new ServerSocket(12346);

        ) {
            System.out.println("Server started. Waiting for client connections...");
            while(true){
                try(
                        Socket clientSocket = serverSocket.accept();
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
                        ){
                    String username = (String) in.readObject();
                    Message recievedMessage = (Message) in.readObject();



                    System.out.println("Username received from client: " + username);
                    System.out.println("Client connected: " + clientSocket);
                    System.out.println("Recieved message: " + recievedMessage);


                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
