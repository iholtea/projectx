package projectx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import projectx.dao.CustomerDao;
import projectx.entity.Customer;
import projectx.entity.CustomerAddress;

@SpringBootApplication
public class SpringRestApplication {
	
	public static void main(String[] args) {
		
		ApplicationContext appContext = 
				SpringApplication.run(SpringRestApplication.class, args);
		
		System.out.println("--------------------------------");
		System.out.println( "context type: " + appContext.getClass().getName());
		System.out.println("--------------------------------");
		
		SimpleDateFormat df = appContext.getBean("dateFormat", SimpleDateFormat.class);
		SimpleDateFormat tf = appContext.getBean("timestampFormat", SimpleDateFormat.class);
		
		Date now = new Date();
		System.out.println( "Current date: "  + df.format(now) );
		System.out.println( "Current timestamp: "  + tf.format(now) );
		System.out.println("--------------------------------");
		
		CustomerDao customerDao = appContext.getBean(CustomerDao.class);
		
		Customer cust;
		CustomerAddress address;
		
		try {
		
			cust = new Customer();
			cust.setFirstName("Gigel");
			cust.setLastName("Gigescu");
			cust.setEmail("ggicescu@gmail.com");
			cust.setDob( df.parse("09/11/1984") );
			cust.setRegisterTime( new Date() );
			
			address = new CustomerAddress();
			address.setCountry("Romania");
			address.setCity("Bucuresti");
			address.setStreet("Bdul Decebal");
			address.setNumber((short)123);
			address.setZipCode("123123");
			address.setCustomer(cust);
			cust.getAddresses().add(address);
			
			address = new CustomerAddress();
			address.setCountry("Romania");
			address.setCity("Roman");
			address.setStreet("Bdul Roman Musat");
			address.setNumber((short)45);
			address.setZipCode("889933");
			address.setCustomer(cust);
			cust.getAddresses().add(address);
			
			customerDao.save(cust);
			
			cust = new Customer();
			cust.setFirstName("Fanel");
			cust.setLastName("Fanescu");
			cust.setEmail("ffanescu@yahoo.com");
			cust.setDob( df.parse("23/07/1972") );
			cust.setRegisterTime( new Date() );
			
			address = new CustomerAddress();
			address.setCountry("France");
			address.setCity("Paris");
			address.setStreet("Bdul Louis Vouitton");
			address.setNumber((short)56);
			address.setZipCode("2233111");
			address.setCustomer(cust);
			cust.getAddresses().add(address);
			
			customerDao.save(cust);
			
		} catch(ParseException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
}
