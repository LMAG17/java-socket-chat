package main.Server;

import main.ClientHandler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverListener;
    private final ArrayList<ClientHandler> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    public void start(int port) throws IOException {
        serverListener = new ServerSocket(port);
        while (true) {
            ClientHandler clientThread = new ClientHandler(serverListener.accept(), clients);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
