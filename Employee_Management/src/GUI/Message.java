package GUI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Message {

    /**
     * creates gui
     * @param out
     * @param in
     */
    public void createMessage(ObjectOutputStream out, ObjectInputStream in);
}
