package org.ionuth.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/app01";
	private static final String DB_USER = "ionut";
	private static final String DB_PASS = "ionut";
	
	public Connection getConnection() {
		
		Connection conn = null;
		try {
			/*
			 * Class.forName is needed only when running from an application server
			 * and using directly the DriverManager.getConnection instead of using
			 * the server managed connection pool data source
			 */
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(System.err);
		}
		return conn;
	}
	
}
