package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ErrorTwo implements Message{

    /**
     * creates an gui that displays an error for sign up
     * @param out
     * @param in
     */
    @Override
    public void createMessage(ObjectOutputStream out, ObjectInputStream in) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));
        frame.setTitle("Error");
        frame.setSize(500, 300);

        JLabel label = new JLabel();
        label.setText("Error: Passwords do not match");
        JButton button1 = new JButton("Ok");
        frame.add(label);
        frame.add(button1);

        button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Sign_Up signUp = new Sign_Up();
                signUp.createMessage(out, in);
            }
        });
        frame.setVisible(true);
        frame.pack();
    }
}
