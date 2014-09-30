package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			throw new RuntimeException("Erro ao adicionar um administrador!");
		}
	}
	
	public Administrador validaLogin(String nomeDeUsuario, String senha) {
		String sql = "SELECT * FROM Administrador";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Administrador admin = null;
			while (rs.next()) {
				if(nomeDeUsuario.equals(rs.getString("nome_de_usuario"))
						&& senha.equals(rs.getString("senha"))) {
					admin = new Administrador();
					admin.setNome(rs.getString("nome"));
					admin.setEmail(rs.getString("email"));
					admin.setNomeDeUsuario(rs.getString("nome_de_usuario"));
					break;
				}
			}
			rs.close();
			stmt.close();
			return admin;
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new IllegalArgumentException("Administrador não encontrado no banco!");
		}
	}
	
	public boolean existeNomeDeUsuario(String nomeDeUsuario) {
		String sql = "SELECT * FROM Administrador";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if(nomeDeUsuario.equals(rs.getString("nome_de_usuario"))) {
					return true;
				}
			}
			rs.close();
			stmt.close();
			return false;
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new IllegalArgumentException("Erro ao acessar o banco!");
		}
	}
	
}
