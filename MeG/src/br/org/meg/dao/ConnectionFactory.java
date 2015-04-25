package org.meg.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.meg.exception.DAOException;

public class ConnectionFactory {
	// This connection is created when app has begin
	private static Connection connection = createConnection();

	/**
	 * Create an connection to work with database
	 * 
	 * @return {@link Connection} used in all Data access object
	 */
	private static Connection createConnection() {
		try {
			// To populate with necessary data to set connection
			Properties properties = new Properties();
			// Get path of file config
			String path = System.getProperty("user.home")+"/mydb.cfg";
			// Create an inputStream with path
			FileInputStream inputStream = new FileInputStream(path);
			
			properties.load(inputStream);
			String host = properties.getProperty("host").toString();
			String username = properties.getProperty("username").toString();
			String password = properties.getProperty("password").toString();
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// return created connection
			return DriverManager.getConnection(host, username, password);
		} catch (IOException | SQLException exception) {
			// Registers error in BD if connection has failed
			throw new DAOException("Failed when initiate App, exception: "
					+ exception.getMessage(), "ConnectionFactory");
		}
	}
	
	/**
	 * @return static connection created one time
	 */
	public static Connection getConnection() {
		return ConnectionFactory.connection;
	}
}
