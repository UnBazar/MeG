package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.meg.exception.DAOException;
import org.meg.model.Administrator;

public class AdministratorDAO {
	private Connection connection;
		
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
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, administrator.getName());
			stmt.setString(2, administrator.getUserName());
			stmt.setString(3, administrator.getEmail());
			stmt.setString(4, administrator.getPassword());
			stmt.execute();
			stmt.close();
		} catch(SQLException sqlException) {
			throw new DAOException("Erro ao adicionar um administrador!", this.getClass().getName());
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
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			stmt.setMaxRows(1);
			ResultSet rs = stmt.executeQuery();
			if(rs.first()) {
				administrator = new Administrator();
				administrator.setName(rs.getString("nome"));
				administrator.setPassword(rs.getString("senha"));
				administrator.setEmail(rs.getString("email"));
				administrator.setUserName(rs.getString("nome_de_usuario"));	
			}
			rs.close();
			stmt.close();
		} catch (SQLException sqlException) {
		}
		return administrator;
	}
	
	/**
	 * Verify if exists an name of administrator
	 * 
	 * @param nameOfAdministrator
	 * @return booleano que verifica existencia do nome de usuario
	 */
	public boolean existsNameOfAdministrator(String nameOfAdministrator) {
		// Create sql command to get administrator with contraints
		String sql = "SELECT * FROM Administrador WHERE nome_de_usuario = ?";
		try {
			// Set statement and execute to get possible results
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, nameOfAdministrator);
			ResultSet resultSet = preparedStatement.executeQuery();
			// Get true if exist an name
			boolean nameExist = resultSet.first();
			
			resultSet.close();
			preparedStatement.close();
			
			return nameExist;
		} catch (SQLException sqlException) {
			throw new DAOException("Error accessing database", this.getClass().getName());
		}
	}
	
}
