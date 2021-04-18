package org.ionuth.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private Date dob;
	private Date registerTime;
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Date getRegisterTime() {
		return registerTime;
	}
	
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ").append(customerId);
		sb.append(" Name: ").append(firstName).append(" ").append(lastName);
		sb.append(" DoB: ").append(df.format(dob));
		sb.append(" Reg Time: ").append(tf.format(registerTime));
		return sb.toString();
	}
	
}
