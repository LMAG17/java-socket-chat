package main;

import main.Server.Server;
import main.UI.UICreator;

import java.io.IOException;

public class Main {
    /**
     * Corre el programa
     */
    public static void main(String[] args) throws IOException {
        /*Muestra la interfaz*/
        new UICreator().showUI();
        /*Arranza el servidor*/
        new Server().start(9999);
    }
}