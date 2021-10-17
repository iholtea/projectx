package projectx.dataimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import projectx.dao.CustomerDao;
import projectx.entity.Customer;
import projectx.entity.CustomerAddress;

@Component
public class DbInitCmdLineRunner implements CommandLineRunner {
	
	@Autowired
	private GenerateAddressService addrService;
	
	@Autowired
	private GenerateCustomerService custService;
	
	@Autowired
	private CustomerDao customerDao;
	
	private static final Logger log = LoggerFactory.getLogger(DbInitCmdLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		
		Customer cust;
		CustomerAddress addr;
		
		log.info("Starting Database Initialization with data...");
		
		for( int i=0; i<25; i++) {
			cust = custService.getCustomerUnique();
			for( int j=0; j<2; j++) {
				addr = addrService.getAddressUnique();
				cust.getAddresses().add(addr);
				addr.setCustomer(cust);
			}
			customerDao.save(cust);
		}
		
		log.info("Finished Database Initialization with data");
	}
	
}
