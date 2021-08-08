package GUI;

import Employee_Info.EInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Log_In implements Message{
    private JTextField textField;
    private JTextField textField1;
    private JButton button;
    private JButton button2;
    private String id;
    private String password;

    /**
     * creates a login gui that allows the user to either login or sign up
     * @param out
     * @param in
     */
    @Override
    public void createMessage(ObjectOutputStream out, ObjectInputStream in) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(4,1);
        jFrame.setLayout(gridLayout);
        jFrame.setTitle("Log in");
        jFrame.setSize(500, 300);

        textField = new JTextField("Id", 20);
        textField1 = new JTextField("Password", 20);
        button = new JButton("Log in");
        button2 = new JButton("Create Account");

        jFrame.add(textField);
        jFrame.add(textField1);
        jFrame.add(button);
        jFrame.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Sign_Up signUp = new Sign_Up();
                signUp.createMessage(out,in);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EInfo eInfo = new EInfo();
                eInfo.setId(textField.getText());
                eInfo.setPassword(textField1.getText());
                boolean found = false;
                try {
                    out.writeBoolean(true);
                    out.flush();

                    out.writeObject(eInfo);
                    out.flush();

                    found = in.readBoolean();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                if (!found) {
                    jFrame.dispose();
                    try {
                        out.writeBoolean(false);
                        out.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    ErrorThree errorThree = new ErrorThree();
                    errorThree.createMessage(out, in);
                } else {
                    jFrame.dispose();
                    Logged logged = new Logged();
                    logged.createMessage(out,in);
                }
            }
        });

        jFrame.setVisible(true);
        jFrame.pack();
    }
}
