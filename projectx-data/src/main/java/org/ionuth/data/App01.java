package org.ionuth.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.ionuth.data.dao.CustomerAddressDao;
import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.dao.impl.jdbc.CustomerAddressDaoJdbc;
import org.ionuth.data.dao.impl.jdbc.CustomerDaoJdbc;
import org.ionuth.data.model.Customer;
import org.ionuth.data.model.CustomerAddress;

public class App01 {
	
	
	public static void insertCustomers() {
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Customer cust = new Customer();
		CustomerDao custDao = new CustomerDaoJdbc();
		
		try {
			
			cust.setCustomerId(0L);
			
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
	
	public static void insertCustomer() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Customer cust = new Customer();
		CustomerDao custDao = new CustomerDaoJdbc();
		
		try {
			
			cust.setCustomerId(0L);
			
			cust.setFirstName("Visinel");
			cust.setLastName("Visinescu");
			cust.setDob( df.parse("15/11/1949") );
			cust.setRegisterTime( tf.parse("17/01/2021 13:45:10") );
			
			custDao.insertCustomer(cust,true);
			System.out.println( "inserted custoomer_id: " + cust.getCustomerId() );
			
		} catch(ParseException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	public static void selectCustomerById() {
		Customer cust = null;
		CustomerDao custDao = new CustomerDaoJdbc();
		
		cust = custDao.getCustomer(1L);
		System.out.println(cust);
		System.out.println("-----------");
		
		cust = custDao.getCustomer(3L);
		System.out.println(cust);
		System.out.println("-----------");
	}
	
	public static void selectAllCustomers() {
		CustomerDao custDao = new CustomerDaoJdbc();
		List<Customer> custList = custDao.getAllCustomers();
		custList.forEach(System.out::println);
	}
	
	public static void selectCustomersPaginated(int limit, int offset) {
		CustomerDao custDao = new CustomerDaoJdbc();
		List<Customer> custList = custDao.getCustomersPaginated(limit, offset);
		custList.forEach(System.out::println);
	}
	
	public static void selectCustomersByAge(int age, String compareType) {
		CustomerDao custDao = new CustomerDaoJdbc();
		List<Customer> custList = custDao.getCustomersByAge(age, compareType);
		custList.forEach(System.out::println);
	}
	
	public static void insertCustomerAddresses() {
		
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		
		CustomerAddress address = new CustomerAddress();
		
		address.setCustomerId(1);
		address.setCountry("Romania");
		address.setCity("Bucuresti");
		address.setStreet("Bulevardul Decebal");
		address.setNumber((short)34);
		address.setZipCode("123456");
		address.setAdditionalInfo("some info");
		addressDao.insertAddress(address);
		
		address.setCustomerId(1);
		address.setCountry("Romania");
		address.setCity("Iasi");
		address.setStreet("Stefan cel Mare");
		address.setNumber((short)134);
		address.setZipCode("666555");
		address.setAdditionalInfo("some info");
		addressDao.insertAddress(address);
		
		address.setCustomerId(2);
		address.setCountry("Romania");
		address.setCity("Bucuresti");
		address.setStreet("Bulevardul Burebista");
		address.setNumber((short)23);
		address.setZipCode("222333");
		address.setAdditionalInfo("some info");
		addressDao.insertAddress(address);
		
		System.out.println("addresses inserted successfully");
		
	}
	
	public static void selectAllAddresses() {
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		List<CustomerAddress> addrList = addressDao.getAllAddresses();
		addrList.forEach(System.out::println);
	}
	
	public static void selectAddressesByCustomer(long customerId) {
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		List<CustomerAddress> addrList = addressDao.getAddresses(customerId);
		addrList.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		
		//insertCustomers();
		//insertCustomer();
		//selectCustomerById();
		//selectAllCustomers();
		//selectCustomersPaginated(3, 3);
		//selectCustomersByAge(30, "gt");
		
		//insertCustomerAddresses();
		//selectAllAddresses();
		selectAddressesByCustomer(2L);
	}
	
}
