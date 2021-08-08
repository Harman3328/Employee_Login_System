package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class Reader {
    private HashSet<String> ids = new HashSet<>();
    private static Hashtable<String, String> ip = new Hashtable<>();

    /**
     * reads file and stores the ids in a unique set
     */
    public void setIds() {
        try {
            Scanner scanner = new Scanner(new File("ids.txt"));
            while (scanner.hasNextLine()) {
                ids.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads file main.txt and stores the ids as keys and passwords as values in a hashtable
     */
    public void setIp() {
        try {
            Scanner scanner = new Scanner(new File("main.txt"));
            while (scanner.hasNext()) {
                String id = scanner.next();
                String password = scanner.next();
                ip.put(id,password);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a unique set of employee ids
     * @return
     */
    public HashSet<String> getIds() {
        return ids;
    }

    /**
     * returns a hashtable containing the employee ids as keys and the passwords as values
     * @return
     */
    public Hashtable<String, String> getIp() {
        return ip;
    }
}
