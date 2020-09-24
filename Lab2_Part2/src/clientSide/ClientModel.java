package clientSide;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import customerModel.Customer;
/**
 * Client Model provides the logic reaquired on the Client side for the Client Management System
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class ClientModel {
	
	ObjectOutputStream objectOut = null;
	ObjectInputStream objectIn = null;
//	ArrayList<Customer> customers = new ArrayList<Customer>();
	Scanner stdin = null;
	
	
	
//	public ClientModel() {
//		ObjectOutputStream  oos = new 
//                ObjectOutputStream(sChannel.socket().getOutputStream());s
//	}
	
	/**
	 * Serializes the Customer Object and sends it to the Server
	 * @param customer the customer object being sent to the server
	 */
	public void sendCustomer(Customer customer) {
		
		try {
//			System.out.println(customer);
			objectOut.writeObject(customer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Serializes a string and sends it to the server
	 * @param output string being sent to the server
	 */
	public void sendString(String output) {
		try {
			objectOut.writeObject(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Processes the ArrayList of Customers returned from the server to an Array of 
	 * Customers
	 * @return Customer [] searchResults
	 */
	public Customer [] getCustomers() {

		Customer customer;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("results"));
		}catch (IOException e) {
			e.printStackTrace();
		}
			ArrayList<Customer> customers = new ArrayList<>();
			try {
				customers = (ArrayList<Customer>) objectIn.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
		Customer [] searchResults = customers.toArray(new Customer[customers.size()]);
		return searchResults;
	}
}
