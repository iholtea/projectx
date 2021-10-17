package projectx.dataimport;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectx.entity.CustomerAddress;

@Service
public class GenerateAddressService {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private StreetService streetService;
	
	Set<CustomerAddress> existing = new HashSet<>();
	Random rand = new Random();
	
	public CustomerAddress getAddressUnique() {
		CustomerAddress address = generate();
		while( existing.contains(address) ) {
			address = generate();
		}
		existing.add(address);
		return address;
	}
	
	public CustomerAddress getAddress() {
		return generate();
	}
	
	private CustomerAddress generate() {
		PojoCity pc = cityService.getRandomCity();
		short number = (short)(1 + rand.nextInt(999));
		CustomerAddress address = new CustomerAddress();
		address.setCountry(pc.getCountry());
		address.setCity(pc.getCity());
		address.setStreet(streetService.getRandomStreet());
		address.setNumber(number);
		address.setZipCode("12345-6789");
		address.setAdditionalInfo("additional info");
		return address;
	}
	
	// format is 12345-6789
	private String generateZipCode() {
		int nr = 1 + rand.nextInt(99999);
		String partA = StringUtils.leftPad( String.valueOf(nr) , 5, '0');
		nr = 1 + rand.nextInt(9999);
		String partB = StringUtils.leftPad( String.valueOf(nr) , 4, '0');
		return partA + "-" + partB;
	}
}
