package customerModel;

import java.io.Serializable;

/**
 * Customer Class create and provides access to each element of a customer
 * extends serializable for transfer between clients an server.
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**These variables are the client information.*/
	private String firstName, lastName, address, postalCode, phoneNum, customerType;
	private int reply = 0;
	/**This is the id of the client in the database.*/
	private int id;
	
	/**
	 * COnstructor to create and Object of type Customer
	 * @param id customer ID
	 * @param firstName	customer first name
	 * @param lastName customer last name
	 * @param address customer address
	 * @param postalCode customer postal code
	 * @param phoneNum customer phone type
	 * @param customerType customer type
	 */
	public Customer(int id, String firstName, String lastName, String address, String postalCode, String phoneNum, String customerType) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNum = phoneNum;
		this.customerType	 = customerType;
	}
	
	public int getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	
	public String toString() {
		String customer = this.id + " " + this.firstName + " " + this.lastName + " " +
				this.customerType;
		return customer;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

}

