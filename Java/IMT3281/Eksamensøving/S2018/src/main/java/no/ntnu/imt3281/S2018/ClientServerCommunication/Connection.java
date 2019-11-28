package no.ntnu.imt3281.S2018.ClientServerCommunication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Class containing information about a client connection to a server
 */
public class Connection {
    // The socket the client uses to communicate with
    private Socket socket;

    // The writer the client uses to communicate with
    private BufferedWriter writer;

    // The reader the client uses to communicate with
    private BufferedReader reader;

    /**
     * Creates a new user from a socket
     * @param clientSocket The socket the client communicates to the server with
     */
    public Connection(Socket clientSocket) {
        socket = clientSocket;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects a client by closing its socket and output/input streams
     */
    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends a message to the client connection
     * @param message The message to send
     */
    public void sendMessage(Message message) {
        try {
            writer.write(message.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a message from the client connection
     * @return The message that was retrieved
     */
    public Message getMessage() {
        String data = null;
        try {
            data = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(data);
    }
}
