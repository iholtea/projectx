package org.projectx.rest.jersey.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.dao.impl.jdbc.CustomerDaoJdbc;
import org.ionuth.data.model.Customer;
import org.ionuth.data.model.HateosLink;

@Path("/customers")
public class CustomerResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/*
	 * same as getCustomersWithParams() but we group all the filtering paramters
	 * in a @BeanParam - Jersey will take care of binding them
	 */
	public List<Customer> getCustomers(@BeanParam CustomerFilterBean filterBean ) {
		int pageSize = filterBean.getPageSize();
		int pageStart = filterBean.getPageStart();
		int age = filterBean.getAge();
		String compareType = filterBean.getCompareType();
		CustomerDao custDao = new CustomerDaoJdbc();
		if(pageSize>0 && pageStart>0) {
			return custDao.getCustomersPaginated(pageSize, pageStart);
		} else if(age>0 && compareType!=null && compareType.length()>0) {
			return custDao.getCustomersByAge(age, compareType);
		} else {
			return custDao.getAllCustomers();
		}
	}
	
	@GET
	@Path("/with_params")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomersWithParams(@QueryParam("pageSize") int pageSize,
			@QueryParam("pageStart") int pageStart,
			@QueryParam("age") int age,
			@QueryParam("compareType") String compareType ) {
		
		CustomerDao custDao = new CustomerDaoJdbc();
		if(pageSize>0 && pageStart>0) {
			return custDao.getCustomersPaginated(pageSize, pageStart);
		} else if(age>0 && compareType!=null && compareType.length()>0) {
			return custDao.getCustomersByAge(age, compareType);
		} else {
			return custDao.getAllCustomers();
		}
	}
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") long customerId,
			@Context UriInfo uriInfo) {
		
		CustomerDao custDao = new CustomerDaoJdbc();
		Customer customer = custDao.getCustomer(customerId);
		
		URI selfUri = uriInfo.getBaseUriBuilder()
				.path(CustomerResource.class)
				.path( String.valueOf(customer.getCustomerId()) )
				.build();
		HateosLink selfLink = new HateosLink();
		selfLink.setLink(selfUri.toString());
		selfLink.setRel("self");
		customer.getHateosLinks().add(selfLink);
		
		URI addressesUri = uriInfo.getBaseUriBuilder()
				.path(CustomerAddressResource.class)
				.resolveTemplate("customerId", customerId)
				.build();
		HateosLink addressesLink = new HateosLink();
		addressesLink.setLink(addressesUri.toString());
		addressesLink.setRel("addresses");
		customer.getHateosLinks().add(addressesLink);
		
		return customer;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer, @Context UriInfo uriInfo) {
		CustomerDao custDao = new CustomerDaoJdbc();
		customer.setRegisterTime(new Date());
		custDao.insertCustomer(customer,true);
		URI newUri = uriInfo.getAbsolutePathBuilder()
				.path( String.valueOf(customer.getCustomerId()) )
				.build();
		return Response.status(Status.CREATED)
				.header(HttpHeaders.LOCATION, newUri)
				.entity(customer)
				.build();
		// there are a couple of shortcut methods for some status codes		
		// return Response.created(newUri).entity(customer).build();
		
	}
	
	@PUT
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(@PathParam("customerId") long id, Customer customer) {
		CustomerDao custDao = new CustomerDaoJdbc();
		customer.setCustomerId(id);
		custDao.updateCustomer(customer);
		return "Customer updated!";
	}
	
	@DELETE
	@Path("/{customerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(@PathParam("customerId") long id) {
		CustomerDao custDao = new CustomerDaoJdbc();
		custDao.deleteCustomer(id);
		return "Customer deleted!";
	}
}	
