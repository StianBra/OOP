package no.ntnu.imt3281.S2018.ClientServerCommunication;

import java.io.*;
import java.net.Socket;

/**
 * Class containing information about a client connection to a server
 */
public class Connection {
    // The socket the client uses to communicate with
    private Socket socket;

    // The writer the server uses to send to clients
    private ObjectOutputStream objectWriter;

    // The reader the server uses to receive from clients
    private ObjectInputStream objectReader;

    // The reader the client uses to communicate with
    private BufferedInputStream reader;


    /**
     * Creates a new user from a socket
     * @param clientSocket The socket the client communicates to the server with
     */
    public Connection(Socket clientSocket) {
        socket = clientSocket;

        try {
            objectReader = new ObjectInputStream(socket.getInputStream());
            objectWriter = new ObjectOutputStream(socket.getOutputStream());
            reader = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects a client by closing its input/output streams and the socket
     */
    public void disconnect() {
            try {
                sendMessage("TERMINATE");
                objectWriter.close();
                objectReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Sends a message to the client connection
     * @param message The message to send
     */
    public void sendMessage(Object message) throws IOException {
        if (message != null) {
            System.out.println("Writing message: " + message + " to " + socket.toString());
            objectWriter.writeObject(message);
            objectWriter.flush();
        }
    }

    /**
     * Retrieves a message from the client connection
     * @return The message that was retrieved, or null if no message
     */
    public Object readMessage() {
        try {
            if (reader.available() > 0) {
                return objectReader.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
