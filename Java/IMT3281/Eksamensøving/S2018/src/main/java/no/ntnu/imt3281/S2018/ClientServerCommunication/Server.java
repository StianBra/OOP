package no.ntnu.imt3281.S2018.ClientServerCommunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Main class for the server
 */
public class Server {
    private final ArrayList<Connection> connections = new ArrayList<>();
    private final LinkedBlockingQueue<Message> messages = new LinkedBlockingQueue<>();

    private boolean shutdown;

    private void start() {
        startListener();
        startProcessor();
        startSender();
    }

    public static void main(String[] args) {
        Server server = new Server();

        server.start();
    }

    /**
     * Starts the sender-thread that sends messages to clients
     */
    private void startSender() {
    }

    /**
     * Starts the receiver-thread that receives messages from clients
     */
    private void startProcessor() {
    }

    /**
     * Starts the listener-thread that listens for clients on a server socket
     */
    private void startListener() {
        Thread listener = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(4444);

                while (!shutdown) {
                    getConnections(serverSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listener.start();
    }


    /**
     * Function for retrieving new client connections
     */
    private void getConnections(ServerSocket serverSocket) {
        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = new Connection(clientSocket);

        synchronized (connections) {
            connections.add(connection);
        }
    }


}
