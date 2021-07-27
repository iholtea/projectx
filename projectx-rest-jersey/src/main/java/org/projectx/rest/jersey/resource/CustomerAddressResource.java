package org.projectx.rest.jersey.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

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
	
	@GET
	@Path("/{addressId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerAddress getAddress(
			@PathParam("customerId") long customerId,
			@PathParam("addressId")  long addressId ) {
			
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		CustomerAddress addr = addressDao.getAddress(addressId);
		return addr;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAddress(CustomerAddress addr, @Context UriInfo uriInfo) {
		
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		addressDao.insertAddress(addr);
		URI newUri = uriInfo.getAbsolutePathBuilder()
				.path( String.valueOf(addr.getCustomerId()) )
				.build();
		return Response.status(Status.CREATED)
				.header(HttpHeaders.LOCATION, newUri)
				.build();
		// there are a couple of shortcut methods for some status codes		
		// return Response.created(newUri).entity(customer).build();
	}
	
	@PUT
	@Path("/{addressId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAddress(
			@PathParam("customerId") long customerId,
			@PathParam("addressId")  long addressId,
			CustomerAddress address) {
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		addressDao.updateAddress(address);
		return "Address updated!";
	}
	
	@DELETE
	@Path("/{addressId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(
			@PathParam("customerId") long customerId,
			@PathParam("addressId") long addressId) {
		CustomerAddressDao addressDao = new CustomerAddressDaoJdbc();
		addressDao.deleteAddress(addressId);
		return "Address deleted!";
	}
	
}
