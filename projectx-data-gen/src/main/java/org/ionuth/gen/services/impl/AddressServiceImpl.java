package org.ionuth.gen.services.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ionuth.data.model.CustomerAddress;
import org.ionuth.gen.services.AddressService;
import org.ionuth.gen.services.CityService;
import org.ionuth.gen.services.StreetService;

public class AddressServiceImpl implements AddressService {
	
	CityService cityService;
	StreetService streetService;
	Random rand = new Random();
	
	Set<CustomerAddress> existing = new HashSet<CustomerAddress>();
	
	public AddressServiceImpl(CityService cityService, StreetService streetService) {
		this.cityService = cityService;
		this.streetService = streetService;
	}
	
	@Override
	public CustomerAddress getAddress() {
		CustomerAddress address = generate();
		while( existing.contains(address) ) {
			address = generate();
		}
		existing.add(address);
		return address;
	}
	
	private CustomerAddress generate() {
		Pair<String, String> cityPair = cityService.getRandomCity();
		String country = cityPair.getLeft();
		String city = cityPair.getRight();
		String street = streetService.getRandomStreet();
		short number = (short)(1 + rand.nextInt(999));
		String zipcode = generateZipCode();
		CustomerAddress address = new CustomerAddress();
		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);
		address.setZipCode(zipcode);
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
