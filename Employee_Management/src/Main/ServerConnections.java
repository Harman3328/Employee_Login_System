package Main;

import Employee_Info.EInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Hashtable;

public class ServerConnections extends Thread{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    boolean openSocket = true;
    private HashSet<String> ids;
    private Hashtable<String,String> ip;

    public ServerConnections() {
        super ("ServerConnectionThread");
    }

    /**
     * sets the set for ids
     * @param ids
     */
    public void setIds(HashSet<String> ids) {
        this.ids = ids;
    }

    /**
     * sets the hashtable for ids and passwords
     * @param ip
     */
    public void setIp(Hashtable<String,String> ip) {
        this.ip = ip;
    }

    /**
     * sets the socket
     * @param socket connecting socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * sets ObjectInputStream
     * @param in connecting socket's ObjectInputStream
     */
    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    /**
     * sets ObjectOutputStream
     * @param out connecting socket's ObjectOutputStream
     */
    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void sendToClient() throws IOException {
        try {
            boolean login = in.readBoolean();
            EInfo eInfo = (EInfo) in.readObject();
            boolean found = idFound(eInfo.getId());
            boolean key = ip.containsKey(eInfo.getId());
            if (found && !key && !login) {
                found = true;
            } else if (login && key && ip.get(eInfo.getId()).equals(eInfo.getPassword())){
                found = true;
            } else {
                found = false;
            }
            out.writeBoolean(found);
            out.flush();

            boolean success = in.readBoolean();
            if (success && !login) {
                String info = eInfo.getId() + " " + eInfo.getPassword();
                Writer.WriteFile(info);
                Reader reader = new Reader();
                reader.setIp();
                ip = reader.getIp();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(socket + " disconnected");
            openSocket = false;
        }
    }

    /**
     * traverses the set to see if the employee id exists
     * @param id
     * @return
     */
    private boolean idFound(String id) {
        for (String eId:ids) {
            if (eId.equals(id)) return true;
        }
        return false;
    }

    public void run() {
        while (openSocket) {
            try {
                sendToClient();
            } catch (IOException e) {
                try {
                    in.close();
                    out.close();
                    socket.close();
                    openSocket = false;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
                openSocket = false;
            }
        }
    }
}
