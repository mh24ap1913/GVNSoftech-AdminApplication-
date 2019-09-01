package in.co.brings.Beans;

import java.sql.Timestamp;

public class InformCustomer {
	
	private int customerId;
	private String customerName;
	private String customerEmail;
	private Timestamp Doj;
	private int status;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Timestamp getDoj() {
		return Doj;
	}
	public void setDoj(Timestamp doj) {
		Doj = doj;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
