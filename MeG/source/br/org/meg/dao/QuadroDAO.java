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
	/**
	 * Obtem uma lista de objetos do tipo Quadro que tem atributos relativos 
	 * aos parametros passados.
	 * 
	 * @param anoInicial	tempo o qual o grafico começa a contar
	 * @param anoFinal	tempo que o grafico para de contar
	 * @param estado	refere-se a unidade federativa que sera usado no grafico
	 * @param secao	relativo ao setor da economia
	 * @param descricao	titulo do quadro
	 * @return	uma Lista contendo quadros referentes aos parametros passados
	 */
	public List<Quadro> obterLista(int anoInicial, int anoFinal, Estado estado, Secao secao, Descricao descricao) {
		String sql = "SELECT * FROM Quadro "
				+ "WHERE estado_id = ? "
				+ "AND secao_id = ? "
				+ "AND descricao_id = ? "
				+ "AND ano >= ? AND ano <= ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, estado.getId());
			ps.setInt(2, secao.getId());
			ps.setInt(3, descricao.getId());
			ps.setInt(4, anoInicial);
			ps.setInt(5, anoFinal);
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
			if(quadros.isEmpty())
				throw new NullPointerException();
			return quadros;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
