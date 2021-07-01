package org.projectx.rest.jersey.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ionuth.data.dao.CustomerAddressDao;
import org.ionuth.data.dao.impl.jdbc.CustomerAddressDaoJdbc;
import org.ionuth.data.model.CustomerAddress;

@Path("/customers/{customerId}/addresses")
public class CustomerAddressResource {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerAddress> getAllAddresses() {
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		List<CustomerAddress> listAddr = addressDao.getAllAddresses();
		return listAddr;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerAddress> getAddressesByCustomer(
			@PathParam("customerId") long customerId) {
			
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		List<CustomerAddress> listAddr = addressDao.getAddresses(customerId);
		return listAddr;
		
	}
	
	
}
