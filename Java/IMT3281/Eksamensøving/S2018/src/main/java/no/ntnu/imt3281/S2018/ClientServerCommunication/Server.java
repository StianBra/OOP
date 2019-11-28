package no.ntnu.imt3281.S2018.ClientServerCommunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Main class for the server
 */
public class Server {
    private final ArrayList<Connection> connections = new ArrayList<>();
    private final LinkedBlockingQueue<Message> messages = new LinkedBlockingQueue<>();
    private boolean shutdown;

    public Server() {
        startListener();
        startReceiver();
        startSender();
    }

    /**
     * Starts the sender-thread that sends messages to clients
     */
    private void startSender() {
        Thread sender = new Thread(() -> {
            while (!shutdown) {
                if (!messages.isEmpty()) {
                    Message msg = null;
                    try {
                        msg = messages.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < connections.size(); i++) {
                        if (i != msg.getClientNumber() && connections.get(i) != null) {
                            try {
                                connections.get(i).sendMessage(msg.getMessage());
                            } catch (IOException e) {
                                System.out.println("Connection " + i + "is disconnected");
                                connections.set(i, null);
                            }
                        }
                    }
                }
            }
        });
        
        sender.start();
    }

    /**
     * Starts the receiver-thread that receives messages from clients
     */
    private void startReceiver() {
        Thread receiver = new Thread(() -> {
            while (!shutdown) {
                for (int i = 0; i < connections.size(); i++) {
                    if (connections.get(i) != null) {
                        Object obj = connections.get(i).readMessage();

                        if (obj instanceof String && obj.toString().equals("SHUTDOWN")) {
                            connections.get(i).disconnect();
                            connections.set(i, null);
                        } else {
                            messages.add(new Message(i, obj));
                        }
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        receiver.start();
    }

    /**
     * Starts the listener-thread that listens for clients on a server socket
     */
    private void startListener() {
        Thread listener = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(4444);

                while (!shutdown) {
                    Socket s = serverSocket.accept();

                    synchronized (connections) {
                        connections.add(new Connection(s));
                    }
                }

                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        listener.start();
    }

    /**
     * Main-method for the server
     * @param args Command-line arguments, not used
     */
    public static void main(String[] args) {
        new Server();
    }
}
