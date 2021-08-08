package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ErrorThree implements Message{

    /**
     * creates gui that displays the incorrect password or id for login
     * @param out
     * @param in
     */
    @Override
    public void createMessage(ObjectOutputStream out, ObjectInputStream in) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridLayout(2,1));
        jFrame.setTitle("Error");
        jFrame.setSize(500, 300);

        JLabel label = new JLabel();
        label.setText("Error: Incorrect Id or password");
        JButton button1 = new JButton("Ok");
        jFrame.add(label);
        jFrame.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Log_In logIn = new Log_In();
                logIn.createMessage(out,in);
            }
        });
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
