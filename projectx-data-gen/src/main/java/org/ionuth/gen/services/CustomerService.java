package org.ionuth.gen.services;

import java.util.List;

import org.ionuth.data.model.Customer;

public interface CustomerService {
	
	List<Customer> getCustomers(int size);
	
}
