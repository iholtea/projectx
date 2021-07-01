package org.ionuth.data.dao;

import java.util.List;

import org.ionuth.data.model.CustomerAddress;

public interface CustomerAddressDao {
	
	CustomerAddress getAddress(long addressId);
	List<CustomerAddress> getAddresses(long customerId);
	List<CustomerAddress> getAllAddresses();
	void insertAddress(CustomerAddress address);
	void updateCustomer(CustomerAddress address);
	void deleteAddress(long addressId);
}
