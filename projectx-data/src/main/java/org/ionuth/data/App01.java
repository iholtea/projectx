package org.ionuth.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.dao.impl.jdbc.CustomerDaoJdbc;
import org.ionuth.data.model.Customer;

public class App01 {
	
	
	public static void insertCustomers() {
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Customer cust = new Customer();
		CustomerDao custDao = new CustomerDaoJdbc();
		
		try {
			
			cust.setCustomerId(0);
			
			cust.setFirstName("Pop");
			cust.setLastName("Popescu");
			cust.setDob( df.parse("14/02/1999") );
			cust.setRegisterTime( tf.parse("15/03/2020 14:45:10") );
			
			cust.setFirstName("Ion");
			cust.setLastName("Ionescu");
			cust.setDob( df.parse("16/04/1998") );
			cust.setRegisterTime( tf.parse("17/09/2020 15:35:22") );
			
			cust.setFirstName("Vasile");
			cust.setLastName("Vasilescu");
			cust.setDob( df.parse("17/05/1997") );
			cust.setRegisterTime( tf.parse("18/19/2020 17:18:50") );
			
			custDao.insertCustomer(cust);
			
			System.out.println("done !!");
			
			
		} catch(ParseException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	public static void selectCustomerById() {
		Customer cust = null;
		CustomerDao custDao = new CustomerDaoJdbc();
		
		cust = custDao.getCustomerById(1);
		System.out.println(cust);
		
		System.out.println("-----------");
		
		cust = custDao.getCustomerById(3);
		System.out.println(cust);
	}
	
	public static void selectAllCustomers() {
		CustomerDao custDao = new CustomerDaoJdbc();
		List<Customer> custList = custDao.getAllCustomers();
		custList.stream().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		
		//insertCustomers();
		//selectCustomerById();
		
		selectAllCustomers();
		
	}
	
}
