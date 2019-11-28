package no.ntnu.imt3281.S2018.ClientServerCommunication;

import javafx.application.Platform;

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

    private ServerSocket serverSocket;
    private boolean shutdown;

    private void start() {
        startListener();
        startProcessor();
        startSender();
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
            Timer timer = new Timer();
            TimerTask listen = new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            serverSocket = new ServerSocket(4444);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        while (!shutdown) {
                            getConnections();
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(listen, 50L, 50L);
        });

        listener.start();
    }


    /**
     * Function for retrieving new client connections
     */
    private void getConnections() {
        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = new Connection(clientSocket);
    }


}
