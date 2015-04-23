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
	 * @param adm Object to be added into the database.
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
	 * Verifica existencia do nome de usuario
	 * @param nomeDeUsuario
	 * @return booleano que verifica existencia do nome de usuario
	 */
	public boolean existeNomeDeUsuario(String nomeDeUsuario) {
		String sql = "SELECT * FROM Administrador WHERE nome_de_usuario = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nomeDeUsuario);
			ResultSet rs = stmt.executeQuery();
			boolean existeNome = rs.first();
			rs.close();
			stmt.close();
			return existeNome;
		} catch (SQLException sqlException) {
			throw new DAOException("Erro ao acessar o banco!", this.getClass().getName());
		}
	}
	
}
