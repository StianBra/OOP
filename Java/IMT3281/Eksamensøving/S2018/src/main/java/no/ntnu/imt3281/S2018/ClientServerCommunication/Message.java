package no.ntnu.imt3281.S2018.ClientServerCommunication;

/**
 * Class containing variables for a message sent from client -> server or vice verca
 */
public class Message {

    private int clientNumber;
    private Object msg;

    public Message(int client, Object message) {
        clientNumber = client;
        msg = message;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public Object getMessage() {
        return msg;
    }
}
