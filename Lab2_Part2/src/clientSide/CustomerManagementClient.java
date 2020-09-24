package clientSide;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Customer Management Client connects to a server and sets up communication streams
 * @author Chelsea Johsnon and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class CustomerManagementClient {
	
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;

	
	/**
	 * Constructor that creates a socket and connects to the server
	 * @param serverName name of the server to connect to
	 * @param portNumber port number of the server to connect to
	 */
	public CustomerManagementClient(String serverName, int portNumber) {
		
		try {
			aSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader (System.in));
//			keyboard input stream
			socketIn = new BufferedReader(new InputStreamReader (aSocket.getInputStream()));
//			socket input stream
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
					
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Communicate connects the the client view, model and socket to the controller  
	 */
	public void communicate() {
		
		ClientView theView = new ClientView();
		ClientModel theModel = new ClientModel();
		
		ClientController theController = new ClientController(theView, theModel, aSocket);
		theController.communicate();

	
	}
	
	public static void main (String [] args) throws IOException{
		CustomerManagementClient aClient = new CustomerManagementClient("localHost", 9898);
		aClient.communicate();

	}

}
