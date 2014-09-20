package br.org.meg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/MEG";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(url, "root", "root");
		} catch(SQLException sqlException) {
			System.err.println(sqlException);
			throw new RuntimeException("Erro ao estabelecer uma conexão com o bando de dados!");
		}
	}
}
