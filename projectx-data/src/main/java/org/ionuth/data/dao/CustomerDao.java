package org.ionuth.data.dao;

import java.util.List;

import org.ionuth.data.model.Customer;

public interface CustomerDao {
	
	public Customer getCustomerById(int id);
	public List<Customer> getAllCustomers();
	public void insertCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	
}
