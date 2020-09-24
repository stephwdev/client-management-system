package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Customer Management Server class is responsible for opening the server connection
 * that clients can connect to and creating the thread pool for multi client access
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class CustomerManagementServer {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private ServerModel theModel;
	private ExecutorService pool;

	/**
	 * Constructor that opens the server socket and creates a thread pool
	 */
	public CustomerManagementServer() {
		try {
			serverSocket = new ServerSocket(9898);
			pool = Executors.newFixedThreadPool(10);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a model, and passes it to the controller with a socket to connect the controller and model and 
	 * provide a connection with the client. Executes the thread pool.
	 * @throws ClassNotFoundException
	 */
	public void runServer() throws ClassNotFoundException {
		try {
			while(true) {
				aSocket = serverSocket.accept();
				System.out.println("Client Server Accepted.");
				
				ServerModel theModel = new ServerModel();

				ServerController theController = new ServerController(theModel, aSocket); 

				pool.execute(theController);

//				aSocket.close(); 
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main (String [] args) {
		CustomerManagementServer server = new CustomerManagementServer();
		try {
			server.runServer();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
