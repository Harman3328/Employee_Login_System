package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    /**
     * writes the employee ids and passwords to main.txt
     * @param contents
     * @throws IOException
     */
    public static synchronized void WriteFile(String contents) {
       try {
           BufferedWriter writer = new BufferedWriter(new FileWriter("main.txt",true));
           writer.write(contents);
           writer.write("\n");
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
