package clientSide;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import customerModel.Customer;

/**
 * Client View Class is the GUI for the Customer Management System
 * @author Chelsea Johnson and Stephanie Walsh
 * @since February 2020
 * @version 1.0
 *
 */
public class ClientView {

	//These variables are all related to the GUI.
	JFrame frame;
	private JTextField searchBar;
	private JTextField customerID;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField address;
	private JTextField postalCode;
	private JTextField phoneNum;
	JButton btnSearch, btnClearSearch, btnSave, btnDelete, btnClear;
	ButtonGroup radioButtons;
	JRadioButton rdbtnCustomerId, rdbtnLastName, rdbtnCustomerType;
	private JList<Customer> searchResults;
	JComboBox customerType;
	DefaultListModel customerListModel;


	/**
	 * Create the application.
	 */
	public ClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCustomerManagementS = new JLabel("Customer Management System");
		lblCustomerManagementS.setForeground(Color.GRAY);
		lblCustomerManagementS.setBackground(Color.GRAY);
		lblCustomerManagementS.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblCustomerManagementS.setBounds(253, 6, 190, 16);
		frame.getContentPane().add(lblCustomerManagementS);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnSearch.setBounds(158, 168, 85, 23);
		frame.getContentPane().add(btnSearch);

		searchBar = new JTextField();
		searchBar.setBounds(6, 164, 156, 27);
		frame.getContentPane().add(searchBar);
		searchBar.setColumns(10);

		btnClearSearch = new JButton("Clear Search");
		btnClearSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnClearSearch.setBounds(233, 165, 117, 29);
		frame.getContentPane().add(btnClearSearch);

		JLabel lblSearch = new JLabel("Search Customers");
		lblSearch.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSearch.setBounds(107, 27, 96, 16);
		frame.getContentPane().add(lblSearch);

		rdbtnCustomerId = new JRadioButton("Customer ID");
		rdbtnCustomerId.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnCustomerId.setBounds(6, 67, 141, 23);
		frame.getContentPane().add(rdbtnCustomerId);

		rdbtnLastName = new JRadioButton("Last Name");
		rdbtnLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnLastName.setBounds(6, 91, 141, 23);
		frame.getContentPane().add(rdbtnLastName);

		rdbtnCustomerType = new JRadioButton("Customer Type");
		rdbtnCustomerType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		rdbtnCustomerType.setBounds(6, 115, 141, 23);
		frame.getContentPane().add(rdbtnCustomerType);

		radioButtons = new ButtonGroup();
		radioButtons.add(rdbtnCustomerId);
		radioButtons.add(rdbtnLastName);
		radioButtons.add(rdbtnCustomerType);
		JLabel lblSearchFor = new JLabel("Enter the search parameters below:");
		lblSearchFor.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSearchFor.setBounds(16, 138, 265, 20);
		frame.getContentPane().add(lblSearchFor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 219, 340, 151);
		frame.getContentPane().add(scrollPane);

		customerListModel= new DefaultListModel<Customer>();
		searchResults = new JList<Customer>(customerListModel);
		searchResults.setVisibleRowCount(-1);
		scrollPane.setViewportView(searchResults);


		JLabel lblSelectTypeOf = new JLabel("Select type of search you want to do:");
		lblSelectTypeOf.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSelectTypeOf.setBounds(16, 43, 247, 16);
		frame.getContentPane().add(lblSelectTypeOf);

		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblCustomerInformation.setBounds(500, 27, 141, 16);
		frame.getContentPane().add(lblCustomerInformation);

		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblCustomerId.setBounds(423, 53, 61, 16);
		frame.getContentPane().add(lblCustomerId);

		customerID = new JTextField();
		customerID.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		customerID.setBounds(510, 48, 131, 26);
		frame.getContentPane().add(customerID);
		customerID.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblFirstName.setBounds(424, 91, 75, 16);
		frame.getContentPane().add(lblFirstName);

		firstName = new JTextField();
		firstName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		firstName.setBounds(511, 86, 130, 26);
		frame.getContentPane().add(firstName);
		firstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblLastName.setBounds(423, 119, 61, 16);
		frame.getContentPane().add(lblLastName);

		lastName = new JTextField();
		lastName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lastName.setBounds(511, 115, 130, 26);
		frame.getContentPane().add(lastName);
		lastName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel.setBounds(423, 153, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		address = new JTextField();
		address.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		address.setBounds(511, 148, 130, 26);
		frame.getContentPane().add(address);
		address.setColumns(10);

		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblPostalCode.setBounds(423, 175, 75, 16);
		frame.getContentPane().add(lblPostalCode);

		postalCode = new JTextField();
		postalCode.setForeground(Color.BLACK);
		postalCode.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		postalCode.setBounds(511, 174, 130, 26);
		frame.getContentPane().add(postalCode);
		postalCode.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblPhoneNumber.setBounds(423, 212, 85, 16);
		frame.getContentPane().add(lblPhoneNumber);

		phoneNum = new JTextField();
		phoneNum.setForeground(Color.BLACK);
		phoneNum.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		phoneNum.setBounds(511, 207, 130, 26);
		frame.getContentPane().add(phoneNum);
		phoneNum.setColumns(10);

		JLabel lblcustomerType = new JLabel("Customer Type:");
		lblcustomerType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblcustomerType.setBounds(423, 240, 76, 16);
		frame.getContentPane().add(lblcustomerType);

		customerType = new JComboBox();
		customerType.setModel(new DefaultComboBoxModel(new String[] {"---", "R", "C"}));
		customerType.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		customerType.setBounds(510, 238, 131, 27);
		frame.getContentPane().add(customerType);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnSave.setBounds(423, 289, 75, 29);
		frame.getContentPane().add(btnSave);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnDelete.setBounds(502, 289, 85, 29);
		frame.getContentPane().add(btnDelete);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnClear.setBounds(585, 289, 88, 29);
		frame.getContentPane().add(btnClear);

		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSearchResults.setBounds(16, 203, 85, 16);
		frame.getContentPane().add(lblSearchResults);

		frame.setVisible(true);
	}

	public int getCustomerID() {
		return getIntFromTextBox(customerID);
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLasttName() {
		return lastName.getText();
	}

	public String getAddress() {
		return address.getText();
	}

	public String getPostalCode() {
		return postalCode.getText();
	}

	public String getPhoneNum() {
		return phoneNum.getText();
	}

	public String getCustomerType() {
		return customerType.getSelectedItem().toString();
	}

	void addSearchListener(ActionListener listenForBtnSearch) {
		btnSearch.addActionListener(listenForBtnSearch);
	}

	void addClearSearchListener(ActionListener listenForBtnSearch) {
		btnClearSearch.addActionListener(listenForBtnSearch);
	}

	void addSaveListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	void addDeleteListener(ActionListener al) {
		btnDelete.addActionListener(al);
	}

	void addClearListener(ActionListener al) {
		btnClear.addActionListener(al);
	}

	void addClickCustomerListener(ListSelectionListener al) {
		searchResults.addListSelectionListener(al);
	}

	public String getSearchBar() {
		return searchBar.getText();
	}

	public void clearSearchBar() {
		searchBar.setText("");
	}

	public void setSearchResults(ArrayList<Customer> customer) {
		searchResults.clearSelection();
		for (int i = 0; i < customer.size(); i++) {
			customerListModel.addElement(customer.get(i));
		}
		
	}

	public void clearSearchResults() {
		customerListModel.clear();
		searchResults.setModel(customerListModel);
	}

	private int getIntFromTextBox (JTextField textField) {
		return Integer.parseInt(textField.getText());
	}

	/**
	 * Display the customer info in the customer Info panel.
	 * @param customer : the customer to show info for.
	 */
	public void displayCustomerInfo(Customer customer) {
		customerID.setText(Integer.toString(customer.getID()));
		firstName.setText(customer.getFirstName());
		lastName.setText(customer.getLastName());
		address.setText(customer.getAddress());
		postalCode.setText(customer.getPostalCode());
		phoneNum.setText(customer.getPhoneNum());
		customerType.setSelectedItem(customer.getCustomerType());
	}

	/**
	 * Clear the customer info from the customer Info panel.
	 */
	public void clearCustomerInfo() {
		customerID.setText("");
		firstName.setText("");
		lastName.setText("");
		address.setText("");
		postalCode.setText("");
		phoneNum.setText("");
		customerType.setSelectedItem("---");
	}

	/**
	 * Check the JList to see what customer is selected.
	 * @return : a customer if there is a customer selected or null if there isn't.
	 */
	public Customer checkCustomerList() {
		return searchResults.getSelectedValue();
	}

}
