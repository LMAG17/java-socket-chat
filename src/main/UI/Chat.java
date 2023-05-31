package main.UI;

import main.Client.Client;
import main.Interfaces.IChat;

import javax.swing.*;
import java.awt.*;

public class Chat implements IChat {
    JFrame frame = new JFrame();
    JPanel window = new JPanel();
    JPanel chatContainer = new JPanel();
    JPanel messageContainer = new JPanel();
    JTextField messageInput = new JTextField();
    Client client;

    /**
     * Crea el panel contenedor del chat
     * @param nickName recibe el nombre recuperado en la pantalla anterior
     */
    public void createChatWindow(String nickName) {
        showUI();
        client = new Client(this);
        client.startConnection(nickName);
    }

    /**
     * Configura la ventana que va a mostrar el chat
     */
    public void setFrame() {
        frame.add(window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chat");
        frame.getContentPane().setBackground(new Color(112, 177, 181));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Configura la ventana contenedora del chat y hud de mensajes
     */
    public void setWindow() {
        window.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        window.setLayout(new GridLayout(0, 1));
        window.add(new JLabel("Servido poli"));
    }

    /**
     * Configura la visual que mostrara los mensajes entrantes
     */
    public void setChatContainer() {
        chatContainer.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        chatContainer.setLayout(new GridLayout(0, 1));
        chatContainer.setPreferredSize(new Dimension(450, 450));
        window.add(chatContainer);
    }

    /**
     * Configura la visual que mostrara el hud para enviar los mensajes
     */
    public void setMessageContainer() {
        messageContainer.setLayout(new GridLayout(1, 2));
        JButton button = new JButton("Enviar");
        button.addActionListener(e -> {
            String message = messageInput.getText();
            client.sendMessage(message);
        });
        messageContainer.add(messageInput);
        messageContainer.add(button);
        window.add(messageContainer);
    }

    /**
     * lanza cada uno de los metodos que configuran y muestran la interfaz
     */
    public void showUI() {
        setWindow();
        setChatContainer();
        setMessageContainer();
        setFrame();
    }

    /**
     * Escucha los mensajes recibidos por el servidor
     * @param message mensaje recibido
     */
    @Override
    public void onMessage(String message) {
        chatContainer.setVisible(false);
        chatContainer.add(new JLabel(message));
        chatContainer.setVisible(true);
    }
}
