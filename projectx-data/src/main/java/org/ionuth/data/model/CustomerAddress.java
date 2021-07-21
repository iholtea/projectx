package org.ionuth.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerAddress {
	
	private long addressId;
	private long customerId;
	private String country;
	private String city;
	private String street;
	private short number;
	private String zipCode;
	private String additionalInfo;
	
	public long getAddressId() {
		return addressId;
	}
	
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public short getNumber() {
		return number;
	}
	
	public void setNumber(short number) {
		this.number = number;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
	        return true;
		}
	    if (!(obj instanceof CustomerAddress)) {
	        return false;
	    }
	    CustomerAddress other = (CustomerAddress)obj;
	    return this.country.equals(other.country) &&
	    		this.city.equals(other.city) &&
	    		this.street.equals(other.street) &&
	    		this.number == other.number;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(addressId);
		sb.append(" CustId: ").append(customerId);
		sb.append( " Addr: ").append(country).append(" ").append(city).append(" ");
		sb.append(street).append(" no: ").append(number);
		return sb.toString();
	}
}
