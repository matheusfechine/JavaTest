package com.ef.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionFactory {

	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost/javatest?useSSL=false", "root", "root");
	}
	
}
