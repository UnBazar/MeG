package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.org.meg.exception.DAOException;
import br.org.meg.model.Administrador;

public class AdministradorDAO {
	private Connection connection;
	
	public AdministradorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adicionar(Administrador adm) {
		String sql = "INSERT INTO Administrador(nome, nome_de_usuario, email, senha)"
				+ "values(?,?,?,?)";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, adm.getNome());
			stmt.setString(2, adm.getNomeDeUsuario());
			stmt.setString(3, adm.getEmail());
			stmt.setString(4, adm.getSenha());
			stmt.execute();
			stmt.close();
		} catch(SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException("Erro ao adicionar um administrador!");
		}
	}
	
	/**
	 *	Metodo que realiza verificacao de usuario no banco, caso o nomeDeUsuario e a senha
	 *	Estejam corretos o usuario é valido 
	 * @param nomeDeUsuario	Nome a ser verificado como login
	 * @param senha	Palavra a ser verificada como senha
	 * @return	Um administrador nulo caso nome ou senha estejam errados. 
	 * 			Ou um administrador com seus dados
	 */
	public Administrador validaLogin(String nomeDeUsuario, String senha) {
		String sql = "SELECT * FROM Administrador where nome_de_usuario = ? AND senha = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nomeDeUsuario);
			stmt.setString(2, senha);
			stmt.setMaxRows(1);
			ResultSet rs = stmt.executeQuery();
			if(rs.first()){
				Administrador administrador = new Administrador();
				administrador.setNome(rs.getString("nome"));
				administrador.setSenha(rs.getString("senha"));
				administrador.setEmail(rs.getString("email"));
				administrador.setNomeDeUsuario(rs.getString("nome_de_usuario"));
				rs.close();
				stmt.close();
				return administrador;
			}
			rs.close();
			stmt.close();
			throw new DAOException("Login inválido!");
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException("Administrador não encontrado no banco!");
		}
	}
	
	public boolean existeNomeDeUsuario(String nomeDeUsuario) {
		String sql = "SELECT * FROM Administrador WHERE nome_de_usuario = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nomeDeUsuario);
			ResultSet rs = stmt.executeQuery();
			boolean existeNome = rs.first();
			rs.close();
			stmt.close();
			if (!existeNome) return false;
			else throw new DAOException("Nome de usuário já existe!");
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException("Erro ao acessar o banco!");
		}
	}
	
}
