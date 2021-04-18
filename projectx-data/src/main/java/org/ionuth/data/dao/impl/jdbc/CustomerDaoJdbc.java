package org.ionuth.data.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ionuth.data.dao.BaseDaoJdbc;
import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.model.Customer;

public class CustomerDaoJdbc extends BaseDaoJdbc implements CustomerDao {
	
	
	@Override
	public Customer getCustomerById(int id) {
		Customer customer = null;
		try {
			Connection conn = getConnection();
			String strSql = "select * from customer where customer_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer();
				customer.setCustomerId( rs.getInt("customer_id") );
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setDob( new Date(rs.getDate("date_of_birth").getTime()) );
				customer.setRegisterTime( new Date(rs.getTimestamp("register_time").getTime()) );
			}
			conn.close();
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return customer;
	}
	
	@Override
	public void insertCustomer(Customer customer) {
		try {
			Connection conn = getConnection();
			String strSql = "insert into customer(first_name,last_name,date_of_birth,register_time)" +
					" values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1,customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setDate(3, new java.sql.Date(customer.getDob().getTime()));
			ps.setTimestamp(4, new Timestamp(customer.getRegisterTime().getTime()));
			ps.execute();
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> custList = new ArrayList<Customer>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String strSql = "select * from customer";
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId( rs.getInt("customer_id") );
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setDob( new Date(rs.getDate("date_of_birth").getTime()) );
				customer.setRegisterTime( new Date(rs.getTimestamp("register_time").getTime()) );
				custList.add(customer);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return custList;	
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
