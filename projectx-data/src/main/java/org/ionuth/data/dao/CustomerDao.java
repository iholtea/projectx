package org.ionuth.data.dao;

import java.util.List;

import org.ionuth.data.model.Customer;

public interface CustomerDao {
	
	Customer getCustomer(long id);
	List<Customer> getAllCustomers();
	List<Customer> getCustomersPaginated(int pageSize, int pageStart);
	List<Customer> getCustomersByAge(int age, String compareType);
	void insertCustomer(Customer customer);
	void insertCustomer(Customer customer, boolean isReturningId);
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
	
}
