package GUI;

import Employee_Info.EInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sign_Up implements Message {
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;

    /**
     * creates a gui for sign up
     * @param out
     * @param in
     */
    @Override
    public void createMessage(ObjectOutputStream out, ObjectInputStream in) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FlowLayout flowLayout = new FlowLayout();
        jFrame.setLayout(flowLayout);
        jFrame.setTitle("Sign_Up");
        jFrame.setSize(500, 300);

        textField = new JTextField("Id", 20);
        textField1 = new JTextField("New Password", 20);
        textField2 = new JTextField("Confirm Password", 20);
        button = new JButton("Confirm");

        jFrame.add(textField);
        jFrame.add(textField1);
        jFrame.add(textField2);
        jFrame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EInfo eInfo = new EInfo();
                eInfo.setId(textField.getText());
                eInfo.setPassword(textField1.getText());
                boolean found = false;
                try {
                    out.writeBoolean(false);
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
                    ErrorOne errorOne = new ErrorOne();
                    errorOne.createMessage(out,in);

                } else if (!textField1.getText().equals(textField2.getText()) || textField.getText().equals("")) {
                    jFrame.dispose();
                    try {
                        out.writeBoolean(false);
                        out.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    ErrorTwo errorTwo = new ErrorTwo();
                    errorTwo.createMessage(out, in);
                } else {
                    try {
                        out.writeBoolean(true);
                        out.flush();
                        jFrame.dispose();
                        Log_In logIn = new Log_In();
                        logIn.createMessage(out, in);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
