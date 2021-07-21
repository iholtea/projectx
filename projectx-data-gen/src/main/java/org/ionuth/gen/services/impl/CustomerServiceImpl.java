package org.ionuth.gen.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.ionuth.data.model.Customer;
import org.ionuth.gen.services.CustomerService;
import org.ionuth.gen.services.DateService;
import org.ionuth.gen.services.FirstNameService;
import org.ionuth.gen.services.LastNameService;

public class CustomerServiceImpl implements CustomerService {
	
	private List<String> emailProviders = Arrays.asList("gmail.com","yahoo.com","icloud.com","aol.com", "gmx.com"); 
	private int epSize = emailProviders.size();
	
	FirstNameService fns;
	LastNameService lns;
	DateService ds;
	
	public CustomerServiceImpl(FirstNameService fns, LastNameService lns, DateService ds) {
		this.fns = fns;
		this.lns = lns;
		this.ds = ds;
	}
	
	@Override
	public List<Customer> getCustomers(int size) {
		
		List<Customer> customers = new LinkedList<Customer>();
		Set<String> logins = new HashSet<String>();
		
		Random rand = new Random();
		
		int crtIdx = 0;
		while(crtIdx<size) {
			String firstName = fns.getRandomName();
			String lastName = lns.getRandomName();
			String login =  (firstName.charAt(0) + lastName).toLowerCase();
			if( !logins.contains(login) ) {
				String email = login + "@" + emailProviders.get( rand.nextInt(epSize) );
				Date dob = ds.getDate(1950, 2005);
				Date registerTime = ds.getDateTime(2015, 2020);
				Customer cust = new Customer();
				cust.setFirstName(firstName);
				cust.setLastName(lastName);
				cust.setEmail(email);
				cust.setDob(dob);
				cust.setRegisterTime(registerTime);
				customers.add(cust);
				crtIdx++;
			}
		}
		
		return customers;
	}
	
	
}
