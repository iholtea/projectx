package projectx.dao;

import java.util.List;

import projectx.entity.Customer;

public interface CustomerDao {
	
	List<Customer> findAll();
	Customer save(Customer customer);
	Customer findOne(Long id);
	int delete(Long id);
	void update(Customer customer);
	
}
