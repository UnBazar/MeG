package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.meg.exception.DAOException;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

public class QuadroDAO {
	private Connection connection;
	/**
	 * Cria uma conexão com o banco de dados através da classe ConnectionFactory. 
	 */
	public QuadroDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Adiciona um quadro ao banco de dados
	 * 
	 * @param quadro	Objeto a ser adicionado ao banco
	 */
	public void adicionar(Quadro quadro) {
		if(!existeQuadro(quadro)){
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
				throw new DAOException("O sistema nao conseguiu adicionar um quadro. Excecao: " 
											+ sqlException.getMessage(), this.getClass().getName());
			}
		}
	}
	
	/**
	 * Verifica se o quadro passado por parametro existe no banco de dados.
	 * @param quadro	objeto a ser verificado no banco de dados
	 * @return	true caso exista
	 */
	public boolean existeQuadro(Quadro quadro){
		String sql = "SELECT * FROM Quadro "
				+ "WHERE estado_id = ? "
				+ "AND secao_id = ? "
				+ "AND descricao_id = ? "
				+ "AND ano = ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, quadro.getEstado().getId());
			ps.setInt(2, quadro.getSecao().getId());
			ps.setInt(3, quadro.getDescricao().getId());
			ps.setInt(4, quadro.getAno());
			ResultSet rs = ps.executeQuery();
			boolean existeQuadro = rs.first();
			rs.close();
			ps.close();
			return existeQuadro;
		}catch(SQLException exception){
			throw new DAOException("Erro em metodo existeQuadro. Causa:" 
										+ exception.getMessage(), this.getClass().getClass().getName());
		}
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
			rs.close();
			ps.close();
			if(quadros.isEmpty()){
				String mensagem = "Tentou obterLista dos anos de "+anoInicial+" - "+anoFinal+" e nada foi retornado.";
				throw new DAOException(mensagem, this.getClass().getName());
			}
			return quadros;
		}catch (SQLException sqlException) {
			throw new DAOException("Em metodo obterLista, a seguinte excecao foi lancada: "
										+ sqlException.getMessage(), this.getClass().getName());
		}
	}
	
	/**
	 * 
	 * @param ano 
	 * @param secao
	 * @param descricao
	 * @return
	 */
	public List<Quadro> obterLista(int ano, Secao secao, Descricao descricao) {
		String sql = "SELECT * FROM Quadro "
				+ "WHERE secao_id = ? "
				+ "AND descricao_id = ? "
				+ "AND ano = ?";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, secao.getId());
			stmt.setInt(2, descricao.getId());
			stmt.setInt(3, ano);
			ResultSet rs = stmt.executeQuery();
			List<Quadro> lista = new ArrayList<>();
			while(rs.next()){
				Quadro quadro = new Quadro();
				quadro.setAno(ano);
				Estado estado = new Estado();
				estado.setId(rs.getInt("estado_id"));
				quadro.setEstado(estado);
				quadro.setSecao(secao);
				quadro.setDescricao(descricao);
				quadro.setValor(rs.getFloat("valor"));
				lista.add(quadro);
			}
			rs.close();
			stmt.close();
			return lista;
		}catch(SQLException exception){
			throw new DAOException("No segundo metodo obterLista a seguinte excecao foi gerada: "
										+ exception.getMessage(), this.getClass().getName());
		}
		
	}
}
