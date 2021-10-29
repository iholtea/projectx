package projectx.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import projectx.dao.CustomerDao;
import projectx.entity.Customer;
import projectx.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerDao custDao;
	
	@GetMapping("/")
	public List<EntityModel<Customer>> getAllCustomers() {
		return custDao.findAll().stream().map( cust -> {
			EntityModel<Customer> emc = EntityModel.of(cust);
			Link selfLink = linkTo(methodOn(this.getClass()).getCustomer(cust.getCustomerId()))
					.withSelfRel();
			emc.add(selfLink);
			return emc;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public EntityModel<Customer> getCustomer(@PathVariable long id) {
		Customer cust = custDao.findOne(id);
		if(cust==null) {
			throw new ResourceNotFoundException("Customer not found id: " + id);
		}
		EntityModel<Customer> emc = EntityModel.of(cust);
		Link link = linkTo(methodOn(this.getClass()).getAllCustomers()).withRel("all-customers");
		emc.add(link);
		return emc;
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		customer.setRegisterTime(new Date());
		custDao.save(customer);
		// builds an URI with the created customer path /customers/{newId}
		// it will add a response header: location = http://localhost:8080/api/customers/4
		URI newCustUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getCustomerId()).toUri();
		// TODO the client will redirect to the edit page customerEdit.html?customerId=${customerId}
		// and it will get the customerId from the response body containing the added customer
		// see if we can get it from the location header
		//return ResponseEntity.created(newCustUri).build();
		return ResponseEntity.created(newCustUri).body(customer);
	}
	
	

}
