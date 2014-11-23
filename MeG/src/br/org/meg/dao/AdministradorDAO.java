package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.meg.exception.DAOException;
import org.meg.model.Administrador;

public class AdministradorDAO {
	private Connection connection;
		
	/**
	 * Cria uma conexão com o banco de dados através da classe ConnectionFactory. 
	 */
	public AdministradorDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Adiciona um administrador no banco de dados.
	 * 
	 * @param adm Objeto a ser adicionado ao banco
	 */
	public void adicionar(Administrador administrador) {
		String sql = "INSERT INTO Administrador(nome, nome_de_usuario, email, senha)"
				+ " values(?,?,?,?)";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, administrador.getNome());
			stmt.setString(2, administrador.getNomeDeUsuario());
			stmt.setString(3, administrador.getEmail());
			stmt.setString(4, administrador.getSenha());
			stmt.execute();
			stmt.close();
		} catch(SQLException sqlException) {
			throw new DAOException("Erro ao adicionar um administrador!", this.getClass().getName());
		}
	}
	
	/**
	 *	Metodo que realiza busca de administrador no banco, caso o nomeDeUsuario e a senha
	 *	Estejam corretos o usuario é valido 
	 * @param nomeDeUsuario	Nome a ser verificado como login
	 * @param senha	Palavra a ser verificada como senha
	 * @return	nulo caso nome ou senha estejam errados. 
	 * 			Ou um administrador com seus dados
	 */
	public Administrador buscaAdministrador(String nomeDeUsuario, String senha){
		String sql = "SELECT * FROM Administrador where nome_de_usuario = ? AND senha = ?";
		Administrador administrador = null;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nomeDeUsuario);
			stmt.setString(2, senha);
			stmt.setMaxRows(1);
			ResultSet rs = stmt.executeQuery();
			if(rs.first()) {
				administrador = new Administrador();
				administrador.setNome(rs.getString("nome"));
				administrador.setSenha(rs.getString("senha"));
				administrador.setEmail(rs.getString("email"));
				administrador.setNomeDeUsuario(rs.getString("nome_de_usuario"));	
			}
			rs.close();
			stmt.close();
		} catch (SQLException sqlException) {
		}
		return administrador;
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
