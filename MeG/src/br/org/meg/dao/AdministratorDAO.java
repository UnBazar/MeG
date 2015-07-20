package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.meg.exception.DAOException;
import org.meg.model.Administrator;

/**
 * Operate in data of table Administrator
 * 
 */
public class AdministratorDAO {
	private Connection connection;
		
	Logger logger = Logger.getLogger("AdministratorDAO");
	
	/** 
	 * Creates a connection with the database through ConnectionFactory class.
	 */
	public AdministratorDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Adds an administrator into the database.
	 * 
	 * @param administrator Object to be added into the database.
	 */
	public void addAdministrator(Administrator administrator) {
		String sql = "INSERT INTO Administrador(nome, nome_de_usuario, email, senha)"
				+ " values(?,?,?,?)";
		try {
			// Create statement from sql comand
			PreparedStatement prepareStatement = this.connection.prepareStatement(sql);
			prepareStatement.setString(1, administrator.getName());
			prepareStatement.setString(2, administrator.getUserName());
			prepareStatement.setString(3, administrator.getEmail());
			prepareStatement.setString(4, administrator.getPassword());
			// Execute and close statement
			prepareStatement.execute();
			prepareStatement.close();
		} catch(SQLException sqlException) {
			logger.error("Error adding new administrator");
			throw new DAOException("catch an error while add an new administrator!", this.getClass().getName());
		}
	}
	
	/**
	 *	Method which searches for an administrator in the database. It's a valid 
	 *	Administrator if the userName and password are correct. 
	 *
	 * @param userName	Name to be verified as login.
	 * @param password	Word to be verified as password.
	 * @return	null in case the userName or password might be wrong. 
	 * 			Or an administrator with its data.
	 */
	public Administrator searchAdministrator(String userName, String password){
		String sql = "SELECT * FROM Administrador where nome_de_usuario = ? AND senha = ?";
		Administrator administrator = null;
		try{
			PreparedStatement prepareStatement = this.connection.prepareStatement(sql);
			prepareStatement.setString(1, userName);
			prepareStatement.setString(2, password);
			prepareStatement.setMaxRows(1);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.first()) {
				administrator = new Administrator();
				administrator.setName(resultSet.getString("nome"));
				administrator.setPassword(resultSet.getString("senha"));
				administrator.setEmail(resultSet.getString("email"));
				administrator.setUserName(resultSet.getString("nome_de_usuario"));	
			}else{
				// will return administrator like null
			}
			resultSet.close();
			prepareStatement.close();
		} catch (SQLException sqlException) {
			logger.error("Error searching administrator");
			throw new DAOException("Catch an error while search an administrator!", this.getClass().getName());
		}
		return administrator;
	}
	
	/**
	 * Verify if exists an name of administrator
	 * 
	 * @param nameOfAdministrator
	 * @return boolean that verifies if user name exists
	 */
	public boolean existsNameOfAdministrator(String nameOfAdministrator) {
		// Create sql command to get administrator with contraints
		String sql = "SELECT * FROM Administrador WHERE nome_de_usuario = ?";
		try {
			// Set statement and execute to get possible results
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, nameOfAdministrator);
			ResultSet resultSet = preparedStatement.executeQuery();
			// Get true if exists a name
			boolean nameExist = resultSet.first();
			
			resultSet.close();
			preparedStatement.close();
			
			return nameExist;
		} catch (SQLException sqlException) {
			logger.error("Error accessing database");
			throw new DAOException("Error accessing database", this.getClass().getName());
		}
	}
	
}
