package main.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;
    private final ArrayList<ClientHandler> clients;
    private String name = "poli";

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> serverClients) throws IOException {
        clients = serverClients;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();
                if (request.equals("exit")) break;
                else if (request.startsWith("/name")) setName(request);
                else if (request.startsWith("/all")) sendMessageToAllServer(request);
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
    }

    private void setName(String command) {
        String name = getCommandMessage(command);
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            this.name = "Anonymous";
        }
        sendMessageToAllServer("/all " + this.name + " se ha unido al server.");
    }

    private void sendMessageToAllServer(String command) {
        String message = getCommandMessage(command);
        if (message != null) {
            for (ClientHandler clientConnected : clients) {
                clientConnected.out.println(name + ": " + message);
            }
        }
    }

    private String getCommandMessage(String command) {
        int separatorIndex = command.indexOf(" ");
        if (separatorIndex != -1) {
            return command.substring(separatorIndex + 1);
        } else {
            return null;
        }
    }
}
