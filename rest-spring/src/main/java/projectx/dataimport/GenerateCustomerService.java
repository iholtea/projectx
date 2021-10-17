package projectx.dataimport;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectx.entity.Customer;

@Service
public class GenerateCustomerService {
	
	@Autowired
	private FirstNameService fns;
	
	@Autowired
	private LastNameService lns;
	
	@Autowired
	private DateService ds;
	
	private Set<String> existingLogins = new HashSet<>();
	private List<String> emailProviders = Arrays.asList("gmail.com","yahoo.com","icloud.com","aol.com", "gmx.com"); 
	private int epSize = emailProviders.size();
	Random rand = new Random();
	
	public Customer getCustomerUnique() {
		Customer cust = generate();
		while( loginExists(cust) ) {
			cust = generate();
		}
		existingLogins.add( (cust.getFirstName().charAt(0) + cust.getLastName()).toLowerCase() );
		return cust;
	}
	
	public Customer getCustomer() {
		return generate();
	}
	
	private Customer generate() {
		String firstName = fns.getRandomName();
		String lastName = lns.getRandomName();
		String login =  (firstName.charAt(0) + lastName).toLowerCase();
		String email = login + "@" + emailProviders.get( rand.nextInt(epSize) );
		Date dob = ds.getDate(1950, 2005);
		Date registerTime = ds.getDateTime(2015, 2020);
		Customer cust = new Customer();
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setEmail(email);
		cust.setDob(dob);
		cust.setRegisterTime(registerTime);
		return cust;
	}
	
	private boolean loginExists(Customer cust) {
		String login =  (cust.getFirstName().charAt(0) + cust.getLastName()).toLowerCase();
		return  existingLogins.contains(login);
	}

}
