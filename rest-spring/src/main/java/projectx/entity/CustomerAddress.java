package projectx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;
	
	@Column(name = "contry", length = 50, nullable = false)
	private String country;
	
	@Column(name = "city", length = 50, nullable = false)
	private String city;
	
	@Column(name = "street", length = 100, nullable = false)
	private String street;
	
	@Column(name = "address_no", nullable = false)
	private short number;
	
	@Column(name = "zip_code", length = 10)
	private String zipCode;
	
	@Column(name = "additional_info", length = 300)
	private String additionalInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	public CustomerAddress() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		sb.append( "Addr: ").append(country).append(" ").append(city).append(" ");
		sb.append(street).append(" no: ").append(number);
		return sb.toString();
	}

}
