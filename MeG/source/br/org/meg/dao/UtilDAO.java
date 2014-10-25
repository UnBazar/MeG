package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.org.meg.model.Erro;

public class UtilDAO {
	private Connection connection;
	
	public UtilDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	/**
	 * Registra uma excecao no banco de dados
	 * 
	 * @param erro contém informacoes 
	 */
	public void registraErro(Erro erro){
		String sql = "INSERT INTO ERRO(descricao, nomeDaClasseReferente, data, status) values(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, erro.getDescricao());
			stmt.setString(2, erro.getNomeDaClasseReferente());
			stmt.setDate(3, erro.getData());
			stmt.setInt(4, erro.getStatus());
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove um certo registro
	 * @param id Identificador do registro
	 */
	public void removeRegistroErro(int id){
		String sql = "DELETE FROM ERRO WHERE id = ?";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtem a lista de erros registrados
	 * 
	 * @return Uma lista todos erros
	 */
	public List<Erro> obterErros(){
		String sql = "SELECT * FROM ERRO";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Erro> erros = new ArrayList<Erro>();
			while(rs.next()){
				Erro erro = new Erro();
				erro.setData(rs.getDate("data"));
				erro.setNomeDaClasseReferente(rs.getString("nomeDaClasseReferente"));
				erro.setId(rs.getInt("id"));
				erro.setStatus(rs.getInt("status"));
				erro.setDescricao(rs.getString("descricao"));
				erros.add(erro);
			}
			rs.close();
			stmt.close();
			return erros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
