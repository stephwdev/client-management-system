package clientSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import customerModel.Customer;
import customerModel.Owl;
/**
 * Client Controller Class provides listeners for all the GUI functions and allows communication 
 * with the Customer Management Server.
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class ClientController {

	private ClientView theView;
	private ClientModel theModel;
	private Socket aSocket;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private PrintWriter socketOut;
	private ArrayList<Customer> customerList;
	private Owl hedwig;
	
	/**
	 * Constructor that connects a view and model together and sets the server socket for communications
	 * @param v the view passed in from the Customer Management Client Class
	 * @param m the model passed in from the Customer Management Client Class
	 * @param aSocket the socket passed in from the Customer Management Client Class
	 */
	public ClientController(ClientView v, ClientModel m, Socket aSocket) {
		this.theView = v;
		this.theModel = m;
		this.aSocket = aSocket;
		
		theView.addClearSearchListener(new ClearSearchListener());
		theView.addSearchListener(new SearchListener());
		theView.addClearListener(new ClearListener());
		theView.addDeleteListener(new DeleteListener());
		theView.addSaveListener(new SaveListener());
		theView.addClickCustomerListener(new ClickCustomerListener());
	}
	
	/**
	 * Communicate provides the object streams and print writers used for communicating with 
	 * the server 
	 */
	public void communicate() {
		
//		Customer customer = null;
//		String input = null;

		try {
			objectOut = new ObjectOutputStream(aSocket.getOutputStream());
			objectIn = new ObjectInputStream(aSocket.getInputStream());
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This listener clears the search results and the search bar.
	 * @author stephaniewalsh
	 *
	 */
	class ClearSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			theView.clearSearchResults();
			theView.clearSearchBar();
			customerList = null;
		}	
	}
	
	/**
	 * This listener searches depending on the type of search you select.
	 * @author stephaniewalsh
	 *
	 */
	class SearchListener implements ActionListener {

		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String search = theView.getSearchBar();
//			System.out.println(search);
			String switchCase = "";
			
			if (theView.rdbtnCustomerId.isSelected()) {
				switchCase = "Id";
			}
			else if (theView.rdbtnLastName.isSelected()) {
				switchCase = "Name";
			}
			else if (theView.rdbtnCustomerType.isSelected()) {
				switchCase = "Type";
			}
			
			customerList = new ArrayList<Customer>();
			Owl hedwig = new Owl(customerList = null, search, switchCase);

			try {
				objectOut.writeObject(hedwig);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
//			customerList = new ArrayList<Customer>();
		
			Owl returnOwl = null;
				try {
					returnOwl = (Owl) objectIn.readObject();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				} 
			
			theView.setSearchResults(returnOwl.getCustomerList());
		}
	}
	
	/**
	 * This listener updates a client when info is added, or adds a new client if the client doesn't exist.
	 * @author stephaniewalsh
	 *
	 */
	class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id;
			String firstName = theView.getFirstName();
			String lastName = theView.getLasttName();
			String address = theView.getAddress();
			String pc = theView.getPostalCode();
			String phoneNum = theView.getPhoneNum();
			String customerType = theView.getCustomerType();
			String switchCase;
			customerList = new ArrayList<Customer>();
			
			if(theView.checkCustomerList() == null) {
				switchCase = "Add";
				id = 0;
			} else {
				switchCase = "Update";
				id = theView.getCustomerID();
			}
			
			
			customerList.add(new Customer(id, firstName,lastName, address, pc, phoneNum, customerType));
			Owl hedwig = new Owl(customerList, null, switchCase);
			try {
				objectOut.writeObject(hedwig);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	/**
	 * This listener deletes a client if they are in the system.
	 * @author stephaniewalsh
	 *
	 */
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int id = theView.getCustomerID();
			String firstName = theView.getFirstName();
			String lastName = theView.getLasttName();
			String address = theView.getAddress();
			String pc = theView.getPostalCode();
			String phoneNum = theView.getPhoneNum();
			String customerType = theView.getCustomerType();
			
			customerList = new ArrayList<Customer>();
			customerList.add(new Customer(id, firstName, lastName, address, pc, phoneNum, customerType));
			Owl hedwig = new Owl(customerList, null, "Delete");
			try {
				objectOut.writeObject(hedwig);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			theModel.sendCustomer(customer);
			
				
		}
	}
	
	/**
	 * This listener clears the Client Information panel.
	 * @author stephaniewalsh
	 *
	 */
	class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			theView.clearCustomerInfo();
			
		}
	}
	
	/**
	 * This listener checks the JList to see what's selected, then displays the info in the Client Info Pane.
	 * @author stephaniewalsh
	 *
	 */
	class ClickCustomerListener implements ListSelectionListener {
	        @Override
	        public void valueChanged(ListSelectionEvent e) {
	            if (e.getValueIsAdjusting() == false) {
	                if (theView.checkCustomerList() != null) {
	                    //System.out.println(theView.getSelectedClient());
	                    theView.displayCustomerInfo(theView.checkCustomerList());
	                }
	            }
	        }
	}
}
