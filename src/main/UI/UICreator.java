package main.UI;

import javax.swing.*;
import java.awt.*;

public class UICreator {
    JPanel window = new JPanel();
    JPanel container = new JPanel();
    JFrame frame = new JFrame();

    /**
     * Configura el contenedor de la visual que permite ingrsar usuarios al server
     */
    public void setContainer() {
        container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        container.setLayout(new GridLayout(0, 3));
        container.add(new JLabel("Apodo"));
        JTextField nameTextField = new JTextField();
        container.add(nameTextField);
        JButton button = new JButton("Unirse al server");
        button.addActionListener(e -> {
            new Thread(() -> {
                Chat chat = new Chat();
                chat.createChatWindow(nameTextField.getText());
            }).start();
        });
        container.add(button,2,2);
        window.add(container);
    }

    /**
     * Configura el panel que contiene el programa
     */
    public void setWindow() {
        window.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        window.setLayout(new GridLayout(0, 1));
        window.add(new JLabel("Servidor poli"));
    }

    /**
     * Muestra el programa en una ventana
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
     * Orquesta la configuracion y lanzamiento de la interfaz de usuario
     */
    public void showUI() {
        setWindow();
        setContainer();
        setFrame();
    }

}
