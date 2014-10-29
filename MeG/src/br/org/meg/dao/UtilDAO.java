package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.meg.exception.DAOException;

public class UtilDAO {
	private Connection connection;
	public UtilDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	/**
	 * Método que pega o nome de um estado pela id no banco de dados
	 * @param id
	 * @return String com o nome do estado
	 */
	public String getNomeEstado(int id) {
		try{
			String sql = "SELECT nome FROM Estado WHERE id = ?";
			String nomeDoEstado = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeDoEstado = rs.getString("nome");
			}
			rs.close();
			stmt.close();
			return nomeDoEstado;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar o nome do estado no banco de dados");
			throw new DAOException(sqlException);
		}
	}
	/**
	 * Método que pega a sigla do estado pelo id no banco de dados
	 * @param id
	 * @return String com a sigla do estado
	 */
	public String getSiglaEstado(int id){
		try{
			String sql = "SELECT sigla FROM Estado WHERE id = ?";
			String siglaDoEstado = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				siglaDoEstado = rs.getString("sigla");
			}
			stmt.close();
			rs.close();
			
			return siglaDoEstado;
			
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar a sigla do estado no banco de dados");
			throw new DAOException(sqlException);
		}
	}
	/**
	 * Método que pega a id do estado pelo nome no banco de dados
	 * @param nome
	 * @return int com a id do estado
	 */
	public int getIdEstado(String nome){
		try{
			String sql = "SELECT id FROM Estado WHERE nome = ?";
			int idEstado = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idEstado = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idEstado;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar a id do estado no banco de dados");
			throw new DAOException(sqlException);
		}
	}
	/**
	 * Método que retorna o nome da seção no banco de dados
	 * @param id
	 * @return String com o nome da secao
	 */
	public String getNomeSecao(int id){
		try{
			String sql = "SELECT nome FROM Secao WHERE id = ?";
			String nomeSecao = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeSecao = rs.getString("nome");
			}
			stmt.close();
			rs.close();
			return nomeSecao;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar o nome da secao no banco de dados");
			throw new DAOException(sqlException);
		}
	}
	/**
	 * Método que retorna o id da seção no banco de dados
	 * @param nome
	 * @return int com a id da secao
	 */
	public int getIdSecao(String nome){
		try{
			String sql = "SELECT id FROM Secao WHERE nome = ?";
			int idSecao = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idSecao = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idSecao;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar o id da secao no banco de dados");
			throw new DAOException(sqlException);
		}
		
	}
	/**
	 * Método que retorna o nome da descricao no banco de dados
	 * @param id
	 * @return String com o nome da descricao
	 */
	public String getNomeDescricao(int id){
		try{
			String sql = "SELECT nome FROM Descricao WHERE id = ?";
			String nomeDescricao = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeDescricao = rs.getString("nome");
			}
			stmt.close();
			rs.close();
			return nomeDescricao;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar o nome da descricao no banco de dados");
			throw new DAOException(sqlException);
		}
	}
	/**
	 * Método que retorna o id da descricao no banco de dados
	 * @param nome
	 * @return Int com o id da descricao
	 */
	public int getIdDescricao(String nome){
		try{
			String sql = "SELECT id FROM Descricao WHERE nome = ?";
			int idDescricao = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idDescricao = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idDescricao;
		}catch(SQLException sqlException){
			System.err.println("Erro ao buscar o id da descricao no banco de dados");
			throw new DAOException(sqlException);
		}
	}

}
