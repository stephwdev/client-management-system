package serverSide;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import customerModel.Customer;
import customerModel.Owl;

/**
 * Server Controller takes care of all the communications between the client and the server model
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class ServerController implements Runnable{

	private ServerModel theModel;
	ArrayList<Customer> customerList = new ArrayList<>();
	private Socket aSocket;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private BufferedReader socketIn;

	/**
	 * Constructor that sets the model to use and the socket for communication
	 * @param m Model to be connected to
	 * @param aSocket socket used to send and recieve data from the Client
	 */
	public ServerController(ServerModel m, Socket aSocket) {
		this.theModel = m;
		this.aSocket = aSocket;
	}

	/**
	 * Provides a switch statement when a Client object is passed to it, will allow 
	 * for the addition, deletion and updating of a client object
	 * @param customer to be added, deleted or updated
	 */
	public void serverSwitch (Owl hedwig) {

		switch (hedwig.getSwitchCase()) {

		case "Add":
			theModel.addCustomer(hedwig.getCustomerList().get(0));
			break;

		case "Update":
			theModel.updateCustomer(hedwig.getCustomerList().get(0));
			break;

		case "Delete":
			theModel.deleteCustomer(hedwig.getCustomerList().get(0));
			break;

		case "Name":
			customerList = theModel.searchCustomerName(hedwig.getSearch());
			Owl pig = new Owl(customerList, null, null);
			try {
				objectOut.writeObject(pig);
//				objectOut.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "Id":
			customerList = theModel.searchCustomerID(Integer.parseInt(hedwig.getSearch()));
			Owl barn = new Owl(customerList, null, null);
			try {
				objectOut.writeObject(barn);
//				objectOut.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "Type":
			customerList = theModel.searchCustomerType(hedwig.getSearch());
			Owl hoot = new Owl(customerList, null, null);
			try {
				objectOut.writeObject(hoot);
//				objectOut.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * Runs the communication between the client and the server on the server side
	 */
	@Override
	public void run() {

//		theModel.removeTable();
		theModel.createTable();
		if(theModel.isDBEmpty()) {
			theModel.fillTable();
		}	

		Owl hedwig = null;

		try {
			objectOut = new ObjectOutputStream(aSocket.getOutputStream());
			objectIn = new ObjectInputStream(aSocket.getInputStream());
			while(true) {
				hedwig = (Owl) objectIn.readObject();
				System.out.println(hedwig.getSwitchCase());
				System.out.println(hedwig.getSearch());
				//					System.out.println(customer.getReply());
				serverSwitch(hedwig);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			aSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}

