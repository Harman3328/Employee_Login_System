package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Logged implements Message{

    /**
     * creates a gui that displays the message after logging in
     * @param out
     * @param in
     */
    @Override
    public void createMessage(ObjectOutputStream out, ObjectInputStream in) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridLayout(2,1));
        jFrame.setTitle("Success");
        jFrame.setSize(500, 300);

        JLabel label = new JLabel();
        label.setText("You have logged in");
        jFrame.add(label);
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
