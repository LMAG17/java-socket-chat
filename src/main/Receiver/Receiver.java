package main.Receiver;

import main.Interfaces.IChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    private ServerSocket receiverSocket;
    private Socket socket;
    private BufferedReader in;
    public void start(int port, IChat ui) {
        try {
            receiverSocket = new ServerSocket(port);
            socket = receiverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread receiver = new Thread(new Runnable() {
                String message;

                @Override
                public void run() {
                    while (true) {
                        try {
                            message = in.readLine();
                            ui.onMessage(message);
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                    }
                }
            });
            receiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            socket.close();
            receiverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
