package main;

import main.Server.Server;
import main.UI.UICreator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new UICreator().showUI();
        new Server().start(9999);
    }
}