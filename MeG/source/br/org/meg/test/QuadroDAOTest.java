package br.org.meg.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.org.meg.dao.ConnectionFactory;
import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Descricao;
import br.org.meg.model.Estado;
import br.org.meg.model.Quadro;
import br.org.meg.model.Secao;

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
		this.connection = new ConnectionFactory().getConnection();
	}
	
	
	@Test
	public void testObterLista() {
		assertFalse(dao.obterLista(2008, 2012, new Estado(5), new Secao(3), new Descricao(3)).isEmpty());
	}
	
	@Test(expected =  NullPointerException.class)
	public void testObterListaExcecao(){
		dao.obterLista(1998, 2005, new Estado(5), new Secao(3), new Descricao(3));
	}
	
	@Test
	public void testAdicionar(){
		dao.adicionar(quadro);
	}
	
//	@Test(expected = SQLException.class)
//	public void testParametroInvalidoObterLista(){
//		Nao se achou casos em que se retorna essa Exception
//	}
	
	@Test
	public void testQuadroInexistente(){
		assertFalse(dao.existeQuadro(quadro));
	}

	@Test
	public void testeExisteQuadro(){
		Quadro quadroExistente = new Quadro();
		quadroExistente.setAno(2010);
		quadroExistente.setDescricao(new Descricao(1));
		quadroExistente.setEstado(new Estado(22));
		quadroExistente.setSecao(new Secao(1));
		quadroExistente.setValor(339);
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
