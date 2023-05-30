package main.Sender;

import org.junit.Test;

class ClientTest {
    @Test
    public void testConnection() {
        Sender sender = new Sender();
        sender.startConnection("192.168.5.105",9999);
        //String response = sender.sendMessage("Hello World");
        //assert response.equals("Hello client");
    }
}