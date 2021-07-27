package org.ionuth.data.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ionuth.data.DataNotFoundException;
import org.ionuth.data.dao.BaseDaoJdbc;
import org.ionuth.data.dao.CustomerAddressDao;
import org.ionuth.data.model.Customer;
import org.ionuth.data.model.CustomerAddress;

public class CustomerAddressDaoJdbc extends BaseDaoJdbc implements CustomerAddressDao {

	@Override
	public CustomerAddress getAddress(long addressId) {
		CustomerAddress addr = null;
		try {
			Connection conn = getConnection();
			String strSql = "select * from customer_address where address_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, addressId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				addr = mapRs2Address(rs);
				rs.close();
				conn.close();
			}
			else {
				throw new DataNotFoundException("CustomerAdddress not found id: " + addressId);
			}
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return addr;
	}
	
	@Override
	public List<CustomerAddress> getAddresses(long customerId) {
		List<CustomerAddress> addrList = new ArrayList<CustomerAddress>();
		Connection conn = getConnection();
		String strSql = "select * from customer_address where customer_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerAddress addr = mapRs2Address(rs);
				addrList.add(addr);
			}
		} catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return addrList;
	}

	@Override
	public List<CustomerAddress> getAllAddresses() {
		List<CustomerAddress> addrList = new ArrayList<CustomerAddress>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String strSql = "select * from customer_address";
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				CustomerAddress addr = mapRs2Address(rs);
				addrList.add(addr);
			}
		} catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return addrList;
	}
	
	
	@Override
	public void insertAddress(CustomerAddress address) {
		try {
			Connection conn = getConnection();
			String strSql = "insert into customer_address(customer_id," +
					"country,city,street,address_no,zip_code,additional_info) " +
					"values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, address.getCustomerId());
			ps.setString(2, address.getCountry());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getStreet());
			ps.setShort(5, address.getNumber());
			ps.setString(6, address.getZipCode());
			ps.setString(7, address.getAdditionalInfo());
			ps.executeUpdate();
		} catch(SQLException ex) {	
			ex.printStackTrace(System.err);	
		}
	}

	@Override
	public void updateAddress(CustomerAddress address) {
		try {
			Connection conn = getConnection();
			String strSql = "update customer_address set " + 
					"country=?,city=?,street=?,address_no=?,zip_code=?,additional_info=? " +
					"where address_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, address.getCountry());
			ps.setString(2, address.getCity());
			ps.setString(3, address.getStreet());
			ps.setShort(4, address.getNumber());
			ps.setString(5, address.getZipCode());
			ps.setString(6, address.getAdditionalInfo());
			ps.setLong(7, address.getAddressId());
			ps.executeUpdate();
		} catch(SQLException ex) {	
			ex.printStackTrace(System.err);	
		}
		
	}

	@Override
	public void deleteAddress(long addressId) {
		try {
			Connection conn = getConnection();
			String strSql = "delete from customer_address where address_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, addressId);
			ps.executeUpdate();
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	private CustomerAddress mapRs2Address(ResultSet rs) throws SQLException {
		CustomerAddress addr = new CustomerAddress();
		addr.setAddressId(rs.getLong("address_id"));
		addr.setCustomerId(rs.getLong("customer_id"));
		addr.setCountry(rs.getString("country"));
		addr.setCity(rs.getString("city"));
		addr.setStreet(rs.getString("street"));
		addr.setNumber(rs.getShort("address_no"));
		addr.setZipCode(rs.getString("zip_code"));
		addr.setAdditionalInfo(rs.getString("additional_info"));
		return addr;
	}
}
