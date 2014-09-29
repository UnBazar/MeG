package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.org.meg.model.Quadro;

public class QuadroDAO {
	private Connection connection;
	
	public QuadroDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adicionar(Quadro quadro) {
		String sql = "INSERT INTO Quadro(ano, valor, estado_id, secao_id, descricao_id) VALUES(?,?,?,?,?)";
		try {
			/*
			 * System.out.printf("\nestado_nome: %s estado_id: %d secao_nome: %s secao_id: %d\n"
			 *		+ "descricao_nome: %s descricao_id: %d valor: %.2f ano: %d", quadro.getEstado().getNome(), 
			 *		quadro.getEstado().getId(), quadro.getSecao().getNome(), quadro.getSecao().getId(),
			 *		quadro.getDescricao().getNome(), quadro.getDescricao().getId(), quadro.getValor(), quadro.getAno());
			 */
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
			throw new RuntimeException("Erro ao acessar o banco de dados!");
		}
	}
	
	public void remover(Quadro quadro) {
		
	}
	
	public List<Quadro> pegaLista() {
		return null;
	}
}
