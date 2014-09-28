package br.org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.org.meg.model.Descricao;
import br.org.meg.model.Estado;
import br.org.meg.model.Quadro;
import br.org.meg.model.Secao;

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
			throw new RuntimeException("Erro ao acessar o banco de dados!");
		}
	}
	
	public void remover(Quadro quadro) {
		
	}
	
	public List<Quadro> obterLista(int anoInicial, int anoFinal, Estado estado, Secao secao, Descricao descricao) {
		String sql = "SELECT *FROM Quadros "
				+ "INNER JOIN Estado ON estado_id = ? AND "
				+ "INNER JOIN Secao ON secao_id = ? AND "
				+ "INNER JOIN Descricao ON descricao_id = ?  WHERE ano >= ? AND ano <= ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, anoInicial);
			ps.setInt(2, anoFinal);
			ps.setInt(3, estado.getId());
			ps.setInt(4, secao.getId());
			ps.setInt(5, descricao.getId());
			ResultSet rs = ps.executeQuery();
			List<Quadro> quadros = new ArrayList<Quadro>();
			while(rs.next()){
				Quadro quadro = new Quadro();
				quadro.setDescricao(descricao);
				quadro.setEstado(estado);
				quadro.setSecao(secao);
				quadro.setAno(rs.getInt("ano"));
				quadro.setValor(rs.getFloat("valor"));
				quadros.add(quadro);				
			}
			return quadros;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
