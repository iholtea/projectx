package org.ionuth.gen.services.app;

import java.util.List;
import java.util.Random;

import org.ionuth.data.dao.CustomerAddressDao;
import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.dao.impl.jdbc.CustomerAddressDaoJdbc;
import org.ionuth.data.dao.impl.jdbc.CustomerDaoJdbc;
import org.ionuth.data.model.Customer;
import org.ionuth.data.model.CustomerAddress;
import org.ionuth.gen.services.AddressService;
import org.ionuth.gen.services.CityService;
import org.ionuth.gen.services.CustomerService;
import org.ionuth.gen.services.DateService;
import org.ionuth.gen.services.FirstNameService;
import org.ionuth.gen.services.LastNameService;
import org.ionuth.gen.services.StreetService;
import org.ionuth.gen.services.impl.AddressServiceImpl;
import org.ionuth.gen.services.impl.CustomerServiceImpl;
import org.ionuth.gen.services.impl.DateServiceImpl;
import org.ionuth.gen.services.impl.json.CityServiceJson;
import org.ionuth.gen.services.impl.json.FirstNameServiceJson;
import org.ionuth.gen.services.impl.json.LastNameServiceJson;
import org.ionuth.gen.services.impl.json.StreetServiceJson;

public class CustomersGenerator {
	
	AddressService as;
	CustomerService cs;
	CustomerDao customerDao;
	CustomerAddressDao addressDao;
	
	Random rand = new Random();
	
	public CustomersGenerator() {
		
		FirstNameService fns = new FirstNameServiceJson();
		LastNameService lns = new LastNameServiceJson();
		DateService ds = new DateServiceImpl();
		cs = new CustomerServiceImpl(fns, lns, ds);
		
		CityService cityService = new CityServiceJson();
		StreetService streetService = new StreetServiceJson();
		as = new AddressServiceImpl(cityService, streetService);
		
		customerDao = new CustomerDaoJdbc();
		addressDao = new CustomerAddressDaoJdbc();
				
	}
	
	private void saveCustomerToDB(Customer customer) {
		customerDao.insertCustomer(customer,true);
		int nrOfAddrs = 1 + rand.nextInt(3);
		for(int i=0;i<nrOfAddrs;i++) {
			CustomerAddress address = as.getAddress();
			address.setCustomerId(customer.getCustomerId());
			addressDao.insertAddress(address);
		}
	}
	
	public void generateCustomers(int size) {
		System.out.println("inserting customers");
		List<Customer> customers = cs.getCustomers(size);
		customers.forEach( cust -> saveCustomerToDB(cust) );
		System.out.println("finished");
	}
	
	public static void main(String[] args) {
		
		CustomersGenerator cg = new CustomersGenerator();
		cg.generateCustomers(100);
		
	}
	
}
