package Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Server {
    private static ArrayList<ServerConnections> threadPool = new ArrayList<>();

    /**
     * creates a ServerSocket, then waits for a client to connect before giving it an instance of Main.ServerConnections
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        reader.setIp();
        reader.setIds();
        HashSet<String> ids = reader.getIds();
        Hashtable<String, String> ip = reader.getIp();
        ServerSocket serverSocket = new ServerSocket(40000);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();

                System.out.println("A new client is connected: " + socket);

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


                ServerConnections connections = new ServerConnections();
                connections.setIn(in);
                connections.setOut(out);
                connections.setSocket(socket);
                connections.setIds(ids);
                connections.setIp(ip);

                connections.start();
            } catch (IOException e) {
                socket.close();
                serverSocket.close();
                System.out.println(socket + " disconnected");
                break;
            }
        }
        serverSocket.close();
    }
}
