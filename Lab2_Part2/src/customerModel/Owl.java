package customerModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Owl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private String search;
	private String switchCase;
	
	public Owl(ArrayList<Customer> customerList, String search, String switchCase) {
		this.setCustomerList(customerList);
		this.setSearch(search);
		this.setSwitchCase(switchCase);
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSwitchCase() {
		return switchCase;
	}

	public void setSwitchCase(String switchCase) {
		this.switchCase = switchCase;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
}
