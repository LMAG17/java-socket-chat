package main.UI;

import main.Sender.Sender;
import main.Interfaces.IChat;
import main.Receiver.Receiver;

import javax.swing.*;
import java.awt.*;

public class Chat implements IChat {
    JFrame frame = new JFrame();
    JPanel window = new JPanel();
    JPanel chatContainer = new JPanel();
    JPanel messageContainer = new JPanel();
    JTextField messageInput = new JTextField("Mensaje...");
    Receiver receiver = new Receiver();
    Sender sender;
    int dest;
    int org;

    public void createChatWindow(int origin, int destiny) {
        dest = destiny;
        org = origin;
        showUI();
        receiver.start(origin, this);
    }

    public void setFrame() {
        frame.add(window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chat");
        frame.getContentPane().setBackground(new Color(112, 177, 181));
        frame.pack();
        frame.setVisible(true);
    }

    public void setWindow() {
        window.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        window.setLayout(new GridLayout(0, 1));
        window.add(new JLabel("Pantalla envia los mensajes "));
    }

    public void setChatContainer() {
        chatContainer.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        chatContainer.setLayout(new GridLayout(0, 1));
        chatContainer.setPreferredSize(new Dimension(450, 450));
        window.add(chatContainer);
    }

    public void setMessageContainer() {
        JButton button = new JButton("Enviar");
        button.addActionListener(e -> {
            if (sender == null) {
                sender = new Sender();
                sender.startConnection("127.0.0.1", dest);
            }
            String message = messageInput.getText();
            sender.sendMessage(message);
            chatContainer.setVisible(false);
            chatContainer.add(new JLabel(org + " : " + message));
            chatContainer.setVisible(true);
        });
        messageContainer.add(messageInput);
        messageContainer.add(button);
        window.add(messageContainer);
    }

    public void showUI() {
        setWindow();
        setChatContainer();
        setMessageContainer();
        setFrame();
    }

    @Override
    public void onMessage(String message) {
        chatContainer.setVisible(false);
        chatContainer.add(new JLabel(dest + " : " + message));
        chatContainer.setVisible(true);
    }
}
