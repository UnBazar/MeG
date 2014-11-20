package org.meg.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static Connection connection = createConnection();

	private static Connection createConnection(){
		try{
	        Properties prop = new Properties();
	        prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
	        String host = prop.getProperty("host").toString();
	        String username = prop.getProperty("username").toString();
	        String password = prop.getProperty("password").toString();
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(host, username, password);
		}catch (IOException | SQLException e) {
			e.printStackTrace();
		}
        return connection;
    }
	
	public static Connection getConnection(){
		return ConnectionFactory.connection;
	}
}

