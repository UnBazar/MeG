package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.org.meg.exception.DAOException;
import br.org.meg.model.Quadro;

public class QuadroDAO {
	private Connection connection;
	
	public QuadroDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adicionar(Quadro quadro) {
		String sql = "INSERT INTO Quadro(ano, valor, estado_id, secao_id, descricao_id) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, quadro.getAno());
			stmt.setFloat(2, quadro.getValor());
			stmt.setInt(3, quadro.getEstado().getId());
			stmt.setInt(4, quadro.getSecao().getId());
			stmt.setInt(5, quadro.getDescricao().getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException(sqlException);
		}
	}
	
	public void remover(Quadro quadro) {
		
	}
	
	public List<Quadro> pegaLista() {
		return null;
	}
}
