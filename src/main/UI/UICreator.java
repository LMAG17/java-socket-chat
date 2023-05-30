package main.UI;

import javax.swing.*;
import java.awt.*;

public class UICreator {
    JPanel window = new JPanel();
    JPanel header = new JPanel();
    JFrame frame = new JFrame();

    public void setHeader() {
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        header.setLayout(new GridLayout(0, 3));
        header.add(new JLabel("Apodo"));
        JTextField nameTextField = new JTextField();
        header.add(nameTextField);
        JButton button = new JButton("Unirse al server");
        button.addActionListener(e -> {
            new Thread(() -> {
                Chat chat = new Chat();
                chat.createChatWindow(nameTextField.getText());
            }).start();
        });
        header.add(button,2,2);
        window.add(header);
    }

    public void setWindow() {
        window.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        window.setLayout(new GridLayout(0, 1));
        window.add(new JLabel("Servidor poli"));
    }

    public void setFrame() {
        frame.add(window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chat");
        frame.getContentPane().setBackground(new Color(112, 177, 181));
        frame.pack();
        frame.setVisible(true);
    }

    public void showUI() {
        setWindow();
        setHeader();
        setFrame();
    }

}
