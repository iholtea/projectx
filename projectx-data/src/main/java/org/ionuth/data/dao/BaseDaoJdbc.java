package org.ionuth.data.dao;

import java.sql.Connection;

import org.ionuth.data.ConnectionProvider;

public class BaseDaoJdbc {
	
	protected Connection getConnection() {
		return new ConnectionProvider().getConnection();
	}
	
}
