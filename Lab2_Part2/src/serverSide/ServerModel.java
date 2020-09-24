package serverSide;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import customerModel.Customer;

/**
 * Server Model contains the databse connection, as well as all the logic to inteact with the 
 * database itself. It perfroms any searches, additons, deletions, and updates to customers and also can create
 * a database, create a table and fill it from a file.
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class ServerModel {
	
	// Attributes
		private Connection conn; //Object of type connection from the JDBC class that deals with connecting to the database
		private Statement statement; // object of type statement from JDBC class that enables the creation "Query statements"
		private ResultSet rs; // object of type ResultSet from the JDBC class that stores the result of the query
		private String tableName = "clientTable", databaseName = "ClientManagementDB", dataFile = "clients.txt";

		public String connectionInfo = "jdbc:mysql://localhost:3306/ClientManagementDB?verifyServerCertificate=false&useSSL=true",  
				  	  login          = "root",
				  	  password       = "ensf5934";
		/**
		 * Sets up the connection to the Database
		 */
		public ServerModel() {
			try{
				// If this throws an error, make sure you have added the mySQL connector JAR to the project
				Class.forName("com.mysql.jdbc.Driver");
				
				// If this fails make sure your connectionInfo and login/password are correct
				conn = DriverManager.getConnection(connectionInfo, login, password);
				System.out.println("Connected to: " + connectionInfo + "\n");
				
				
			}
			catch(SQLException e) { e.printStackTrace(); }
			catch(Exception e) { e.printStackTrace(); }
		}

		/**
		 * Create a database in MySQL if it doesn't exist.
		 */
		public void createDB()
		{
			try {
				statement = conn.createStatement();
				statement.executeUpdate("CREATE DATABASE " + databaseName);
				System.out.println("Created Database " + databaseName);
			} 
			catch( SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 *  Create a data table inside of the database to hold customers.
		 */
		public void createTable()
		{
			String sql = "CREATE TABLE " + tableName + "(" +
					     "ID INT(4) AUTO_INCREMENT, " +
					     "FIRSTNAME VARCHAR(20) NOT NULL, " + 
					     "LASTNAME VARCHAR(20) NOT NULL, " +
					     "ADDRESS VARCHAR(50) NOT NULL, " +
					     "POSTALCOD CHAR(7) NOT NULL, " +
					     "PHONENUMBER CHAR(12) NOT NULL, " +
					     "CLIENTTYPE CHAR(1) NOT NULL, " +
					     "PRIMARY KEY ( id ))";
			try{
				statement = conn.createStatement();
				statement.executeUpdate(sql);
				System.out.println("Created Table " + tableName);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * This method removes the table from the database.
		 */
		public void removeTable()
		{
			String sql = "DROP TABLE " + tableName;
			try{
				statement = conn.createStatement();
				statement.executeUpdate(sql);
				System.out.println("Removed Table " + tableName);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * This method fills the table from a CSV file.
		 */
		public void fillTable() {
	        try {
	            Scanner sc = new Scanner(new FileReader(dataFile));
	            while (sc.hasNext()) {
	                String customerInfo[] = sc.nextLine().split(";");
	                addCustomer(new Customer(0,customerInfo[0],
	                        customerInfo[1],
	                        customerInfo[2],
	                        customerInfo[3],
	                        customerInfo[4],
	                        customerInfo[5]));
	            }
	            sc.close();
	        } catch (FileNotFoundException e) {
	            System.err.println("File " + dataFile + " Not Found!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		
		/**
		 * This method adds a customer to the table in the database.
		 * @param customer : the information regarding the customer.
		 */
		public void addCustomer(Customer customer)
		{
			String sql = "INSERT INTO " + tableName +
					"(FIRSTNAME, LASTNAME, ADDRESS, POSTALCOD, PHONENUMBER, CLIENTTYPE) VALUES (?,?,?,?,?,?)"; 

			try{
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setString(1, customer.getFirstName());
				pStat.setString(2, customer.getLastName());
				pStat.setString(3, customer.getAddress());
				pStat.setString(4, customer.getPostalCode());
				pStat.setString(5, customer.getPhoneNum());
				pStat.setString(6, customer.getCustomerType());
				pStat.executeUpdate();
				pStat.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * This method updates the customer in the database.
		 * @param customer : the customer to be updated.
		 */
		public void updateCustomer(Customer customer)
		{
			String sql = "UPDATE " + tableName +
					" SET FIRSTNAME=?, LASTNAME=?, ADDRESS=?, POSTALCOD=?, PHONENUMBER=?, CLIENTTYPE=? WHERE ID=?"; 

			try{
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setString(1, customer.getFirstName());
				pStat.setString(2, customer.getLastName());
				pStat.setString(3, customer.getAddress());
				pStat.setString(4, customer.getPostalCode());
				pStat.setString(5, customer.getPhoneNum());
				pStat.setString(6, customer.getCustomerType());
				pStat.setInt(7, customer.getID());
				pStat.executeUpdate();
				pStat.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * This method deletes a customer based on the ID.
		 * @param customer : the customer to delete.
		 */
		public void deleteCustomer(Customer customer)
		{
			String sql = "DELETE FROM " + tableName + " WHERE ID = ?"; 

			try{
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setInt(1, customer.getID());
				pStat.executeUpdate();
				pStat.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * This method prints the whole database.
		 */
		public void printTable()
		{
			try {
				String sql = "SELECT * FROM " + tableName;
				statement = conn.createStatement();
				ResultSet customers = statement.executeQuery(sql);
				while(customers.next())
				{
					System.out.println(customers.getInt("ID") + " " + 
									   customers.getString("FIRSTNAME") + " " + 
									   customers.getString("LASTNAME") + " " + 
									   customers.getString("ADDRESS") + " " + 
									   customers.getString("POSTALCOD") + " " +
									   customers.getString("PHONENUMBER") + " " +
									   customers.getString("CLIENTTYPE"));
				}
				customers.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * This method searches the database based on the customer ID and returns the customer list.
		 * @param id : the id to search.
		 * @return : customer array.
		 */
		public ArrayList<Customer> searchCustomerID(int id)
		{
			String sql = "SELECT * FROM " + tableName + " WHERE ID=?";
			ResultSet customer;
			ArrayList<Customer> customerList = new ArrayList<>();
			try {
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setInt(1,id);
				customer = pStat.executeQuery();
				
				while(customer.next())
				{
					customerList.add(new Customer(customer.getInt("ID"),
									customer.getString("FIRSTNAME"), 
									customer.getString("LASTNAME"), 
									customer.getString("ADDRESS"), 
									customer.getString("POSTALCOD"),
									customer.getString("PHONENUMBER"),
									customer.getString("CLIENTTYPE")));
				}
				pStat.close();
			} catch (SQLException e) { e.printStackTrace(); }
			
			return customerList;
		}
		/**
		 * This method searches the database by the last name column.
		 * @param lastName : what to search for.
		 * @return : a customer list of the matching last names.
		 */
		public ArrayList<Customer> searchCustomerName(String lastName)
		{
			String sql = "SELECT * FROM " + tableName + " WHERE LASTNAME=?";
			ResultSet customer;
			ArrayList<Customer> customerList = new ArrayList<>();
			try {
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setString(1,lastName);
				customer = pStat.executeQuery();
				while(customer.next())
				{
					customerList.add(new Customer(customer.getInt("ID"),
							customer.getString("FIRSTNAME"), 
							customer.getString("LASTNAME"), 
							customer.getString("ADDRESS"), 
							customer.getString("POSTALCOD"),
							customer.getString("PHONENUMBER"),
							customer.getString("CLIENTTYPE")));
				}
				pStat.close();
			} catch (SQLException e) { e.printStackTrace(); }
			
			return customerList;
		}
		
		
		/**
		 * Search by customer type, R or C.
		 * @param type : the type of customer to search for.
		 * @return : list of the customers that match this type.
		 */
		public ArrayList<Customer> searchCustomerType(String type)
		{
			String sql = "SELECT * FROM " + tableName + " WHERE CLIENTTYPE=?";
			ResultSet customer;
			ArrayList<Customer> customerList = new ArrayList<>();
			try {
				PreparedStatement pStat = conn.prepareStatement(sql);
				pStat.setString(1,type);
				customer = pStat.executeQuery();
				while(customer.next())
				{
					customerList.add(new Customer(customer.getInt("ID"),
							customer.getString("FIRSTNAME"), 
							customer.getString("LASTNAME"), 
							customer.getString("ADDRESS"), 
							customer.getString("POSTALCOD"),
							customer.getString("PHONENUMBER"),
							customer.getString("CLIENTTYPE")));
				}
				pStat.close();
			} catch (SQLException e) { e.printStackTrace(); }
			
			return customerList;
		}
		
		public boolean isDBEmpty() {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM " + tableName;
			ResultSet client;
			try {
				PreparedStatement pStatement = conn.prepareStatement(sql);
				client = pStatement.executeQuery();
				if(client.next())
				{
					pStatement.close();
					return false;
				}
				else {
					pStatement.close();
					return true;
				}

			} catch (SQLException e) { e.printStackTrace(); }
			return false;
		}
		


}
