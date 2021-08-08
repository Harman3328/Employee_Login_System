package Main;
import GUI.Log_In;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    /**
     * client side interface
     * @param args
     */
    public static void main(String[] args) {
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            socket = new Socket("localhost", 40000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Log_In logIn = new Log_In();
            logIn.createMessage(out,in);
            while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
            System.out.println(socket + " disconnected");
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
