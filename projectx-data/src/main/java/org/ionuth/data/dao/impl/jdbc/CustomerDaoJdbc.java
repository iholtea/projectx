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

import org.ionuth.data.DataNotFoundException;
import org.ionuth.data.dao.BaseDaoJdbc;
import org.ionuth.data.dao.CustomerDao;
import org.ionuth.data.model.Customer;

public class CustomerDaoJdbc extends BaseDaoJdbc implements CustomerDao {
	
	
	@Override
	public Customer getCustomer(long id) {
		Customer customer = null;
		try {
			Connection conn = getConnection();
			String strSql = "select * from customer where customer_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer = mapRs2Customer(rs);
				rs.close();
				conn.close();
			}
			else {
				throw new DataNotFoundException("Customer not found id: " + id);
			}
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
		
		return customer;
	}
	
	@Override
	public void insertCustomer(Customer customer) {
		insertCustomer(customer, false);
	}
	
	@Override
	public void insertCustomer(Customer customer, boolean isReturningId) {
		try {
			Connection conn = getConnection();
			String strSql = "insert into customer(first_name,last_name,email,date_of_birth,register_time)" +
					" values(?,?,?,?,?)";
			if(isReturningId) {
				strSql += " returning customer_id";
			}
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1,customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setDate(4, new java.sql.Date(customer.getDob().getTime()));
			ps.setTimestamp(5, new Timestamp(customer.getRegisterTime().getTime()));
			if(isReturningId) {
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					customer.setCustomerId( rs.getLong("customer_id") );
				}
			} else {
				ps.executeUpdate();
			}
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	@Override
	public void updateCustomer(Customer customer) {
		try {
			Connection conn = getConnection();
			String strSql = "update customer set first_name=?,last_name=?,email=?,date_of_birth=?" +
					" where customer_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1,customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setDate(4, new java.sql.Date(customer.getDob().getTime()));
			ps.setLong(5, customer.getCustomerId());
			ps.executeUpdate();
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
			String strSql = "select * from customer order by customer_id desc";
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				Customer customer = mapRs2Customer(rs);
				custList.add(customer);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return custList;	
	}
	
	@Override
	public List<Customer> getCustomersPaginated(int pageSize, int pageStart) {
		List<Customer> custList = new ArrayList<Customer>();
		try {
			Connection conn = getConnection();
			String strSql = "select * from customer order by customer_id " + 
					"limit ? offset ?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			int offset = (pageStart-1)*pageSize;
			ps.setInt(1, pageSize);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Customer customer = mapRs2Customer(rs);
				custList.add(customer);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return custList;
	}
	
	@Override
	public List<Customer> getCustomersByAge(int age, String compareType) {
		List<Customer> custList = new ArrayList<Customer>();
		try {
			Connection conn = getConnection();
			String compareOp = "lt".equals(compareType) ? "<=" : ">=";
			String strSql = "select * from customer " + 
					"where date_part('year', age(date_of_birth))" + compareOp + age + 
					" order by customer_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				Customer customer = mapRs2Customer(rs);
				custList.add(customer);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return custList;
	}

	
	@Override
	public void deleteCustomer(long id) {
		try {
			Connection conn = getConnection();
			String strSql = "delete from customer where customer_id=?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setLong(1, id);
			ps.executeUpdate();
		}	
		catch( SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	private Customer mapRs2Customer (ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId( rs.getLong("customer_id") );
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setEmail(rs.getString("email"));
		customer.setDob( new Date(rs.getDate("date_of_birth").getTime()) );
		customer.setRegisterTime( new Date(rs.getTimestamp("register_time").getTime()) );
		return customer;
	}

}
