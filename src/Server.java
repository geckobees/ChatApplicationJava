import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    static ArrayList<ClientHandler> Clients = new ArrayList<ClientHandler>();
    public static void main(String[] args) {
        final int SERVER_PORT = 12346;


        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    ClientHandler Client = new ClientHandler(clientSocket);
                    Clients.add(Client);
                    Client.start();
                    System.out.println(Clients);

                    System.out.println("Client connected: " + clientSocket);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, ClientHandler sender){
        for(ClientHandler client : Clients){
            if(client != sender){
                client.SendMessage(message);
            }
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;


    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        //this.name = n;
    }
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("working");
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message received: " + message);
                Server.broadcast(message, this);
            }
            in.close();


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("A user has disconnected");
        } finally {
            try {
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void SendMessage(String Message){
        out.println(Message);
    }
}