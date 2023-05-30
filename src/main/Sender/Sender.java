package main.Sender;

import java.io.PrintWriter;
import java.net.Socket;

public class Sender {
    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }


    public void sendMessage(String message) {
        try {
            out.println(message);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public void stopConnection() {
        try {
            clientSocket.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
