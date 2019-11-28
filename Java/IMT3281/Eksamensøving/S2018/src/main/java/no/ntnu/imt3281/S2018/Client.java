package no.ntnu.imt3281.S2018;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Objects of this class acts as clients in a multiuser message system.
 * The clients can send/receive objects of any class to each other through the server.
 * The server will send any object received to all clients except for the the one client that sent the object.
 * 
 * @author oeivindk
 *
 */
public class Client {
	private String name;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket s;
	private boolean stopping = false;
	
	/**
	 * Create a new client, give it a name.
	 * 
	 * @param name the name of the client to create.
	 */
	public Client (String name) {
		this.name = name;
	}
	
	/**
	 * Connect to a server at the given address and port. Also starts a new thread that will listen to messages 
	 * from the server.
	 * 
	 * @param server what address (IP or name) hosts the server
	 * @param port on what port is the server listening
	 * @throws IOException thrown if no connection to the server can be established.
	 */
	public void connect (String server, int port) throws IOException {
		s = new Socket(server, port);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		new Thread(()-> {
			listen();
		}).start();
	}
	
	/**
	 * Listens for messages sent from the server. If a message is received and that message is a String object
	 * the string is shown in the console.
	 * 
	 * If a message containing a String with the value of "TERMINATE" is received from the server the clients closes
	 * the streams and socket and stops listening for messages from the server.
	 */
	private void listen() {
		while (stopping==false) {
			try {
				Object o = ois.readObject();
				if (o instanceof String) {
					System.out.printf("Client '%s' got: %s\n", name, o.toString());
					if (o.toString().equals("TERMINATE")) {
						stopping=true;
					}
				}
			} catch (ClassNotFoundException e) {
				stopping=true;			// Do the safe thing, stop the client
			} catch (IOException e) {
				stopping=true;
			}
		}
		try {
			ois.close();
			ois = null;
			s.close();
			s = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Send an object to the server, flushes the stream when the object has been written.
	 * 
	 * @param o the objet to send to the server
	 * @throws IOException gets thrown if the object can not be sent to the server.
	 */
	public void writeObject (Object o) throws IOException {
		if (oos!=null) {
			oos.writeObject(o);
			oos.flush();
		}
	}
	
	/**
	 * Stops this client, ie sends a message to the server notifying the server that this client is leaving, 
	 * then closes the streams and sockets and stops the listening thread.
	 */
	public void stop() {
		stopping = true;
		try {
			System.out.println("Stopping client "+name);
			writeObject("SHUTDOWN");
			oos.close();
			oos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns true if this client is active, that is, the client is listening for messages from the server.
	 * If the connections are closed and the client is not listening for messages from the server false is returned.
	 * 
	 * @return true for active clients, false for inactive (closed) clients.
	 */
	public boolean active() {
		return stopping==false;
	}

	/**
	 * Test the client (and server) class by creating five clients and connecting them to the server. 
	 * Then 20 iterations are run where a random client is picked and that client is used to send a message.
	 * There is a random 1/10 chance that the selected client is directed to close the connection.
	 * If a client is selected that has been stopped a message that an inactive client has been selected is 
	 * outputed to the console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Client clients[] = new Client[5];
		for (int i=0; i<clients.length; i++) {			// Create n clients
			clients[i] = new Client("Testing "+(i+1));
		}
		try {
			for (int i=0; i<clients.length; i++) {		// Connect the clients to the server
				clients[i].connect("localhost", 4444);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random rand = new Random();
		for (int i=0; i<20; i++) {			// Do 20 iterations, send message from random client
			try {
				boolean stopClient = (rand.nextInt(10)==0);	// A 10% chance of stopping a client
				int rnd = rand.nextInt(clients.length);		// What client to send from
				if (clients[rnd].active()) {					// The client is active
					if (stopClient)
						clients[rnd].stop();
					else										// Send a message from this client
						clients[rnd].writeObject("Hello there "+i);
				} else {										// The client has been previously stopped
					System.out.println("inactive client : Testing" + (rnd+1));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
