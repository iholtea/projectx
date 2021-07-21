package org.ionuth.data.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private Date registerTime;
	
	@XmlElement(name = "links")
	List<HateosLink> hateosLinks = new ArrayList<HateosLink>();
	
	public Customer() {}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public List<HateosLink> getHateosLinks() {
		return hateosLinks;
	}
	
	public void setHateosLiks(List<HateosLink> hateosLinks) {
		this.hateosLinks = hateosLinks;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ").append(customerId);
		sb.append(" Name: ").append(firstName).append(" ").append(lastName);
		sb.append(" Email: ").append(email);
		sb.append(" DoB: ").append(df.format(dob));
		sb.append(" Reg Time: ").append(tf.format(registerTime));
		return sb.toString();
	}
	
}
