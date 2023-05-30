package main.UI;

import javax.swing.*;
import java.awt.*;

public class UICreator {
    JPanel window = new JPanel();
    JPanel header = new JPanel();
    JFrame frame = new JFrame();

    public void setHeader() {
        header.add(new JLabel("Origen"));
        JTextField origin = new JTextField("9999");
        header.add(origin);
        header.add(new JLabel("Destino"));
        JTextField destiny = new JTextField("8888");
        header.add(destiny);
        header.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        header.setLayout(new GridLayout(0, 2));
        JButton button = new JButton("Nuevo chat");
        button.addActionListener(e -> {
            new Thread(() -> {
                Chat chat = new Chat();
                chat.createChatWindow(Integer.parseInt(origin.getText()), Integer.parseInt(destiny.getText()));
            }).start();
        });
        header.add(button);
        window.add(header);
    }

    public void setWindow() {
        window.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        window.setLayout(new GridLayout(0, 1));
        window.add(new JLabel("Pantalla envia los mensajes "));
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
