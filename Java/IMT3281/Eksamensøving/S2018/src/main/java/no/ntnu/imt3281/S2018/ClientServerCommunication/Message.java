package no.ntnu.imt3281.S2018.ClientServerCommunication;

/**
 * Class containing variables for a message sent from client -> server or vice verca
 */
public class Message {

    private String text;

    public Message(String data) {
        text = data;
    }

    public String getText() {
        return text;
    }
}
