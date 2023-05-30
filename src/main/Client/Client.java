package main.Client;

import main.Interfaces.IChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private PrintWriter out;
    private BufferedReader in;
    private final IChat chat;
    public Client(IChat iChat) {
        chat = iChat;
    }
    public void startConnection(String nickname) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 9999);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("/name " + nickname);
            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readLine();
                        if (message == null) break;
                        chat.onMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            out.println("/all " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
