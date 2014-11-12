package org.meg.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.meg.dao.ConnectionFactory;
import org.meg.dao.QuadroDAO;
import org.meg.exception.DAOException;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

public class QuadroDAOTest {
	private QuadroDAO dao;
	private Quadro quadro;
	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		dao = new QuadroDAO();
		quadro = new Quadro();
		quadro.setAno(404);
		quadro.setDescricao(new Descricao(3));
		quadro.setSecao(new Secao(3));
		quadro.setEstado(new Estado(4));
		
	}
	
	public void createConnection(){
		this.connection = ConnectionFactory.getConnection();
	}
	
	
	@Test
	public void testObterLista() {
		assertFalse(dao.obterLista(2008, 2012, new Estado(5), new Secao(3), new Descricao(3)).isEmpty());
	}
	
	@Test(expected =  DAOException.class)
	public void testObterListaExcecao(){
		dao.obterLista(1998, 2005, new Estado(5), new Secao(3), new Descricao(3));
	}
	
	@Test
	public void testAdicionar(){
		dao.adicionar(quadro);
	}
	
	@Test
	public void testQuadroInexistente(){
		assertFalse(dao.existeQuadro(quadro));
	}

	@Test
	public void testeExisteQuadro(){
		Quadro quadroExistente = new Quadro();
		quadroExistente.setAno(2011);
		quadroExistente.setDescricao(new Descricao(3));
		quadroExistente.setEstado(new Estado(5));
		quadroExistente.setSecao(new Secao(15));
		quadroExistente.setValor(529532);
		assertTrue(dao.existeQuadro(quadroExistente));
	}
	
	@After
	public void excluirQuadro(){
		createConnection();
		String sql = "DELETE FROM Quadro WHERE ano = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, this.quadro.getAno());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
