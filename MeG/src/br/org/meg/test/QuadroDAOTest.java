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
import org.meg.dao.FrameDAO;
import org.meg.exception.DAOException;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

public class QuadroDAOTest {
	private FrameDAO dao;
	private Frame quadro;
	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		dao = new FrameDAO();
		quadro = new Frame();
		quadro.setYear(404);
		quadro.setDescription(new Description(3));
		quadro.setSection(new Section(3));
		quadro.setState(new State(4));
		
	}
	
	public void createConnection(){
		this.connection = ConnectionFactory.getConnection();
	}
	
	
	@Test
	public void testObterLista() {
		assertFalse(dao.getFramesList(2008, 2012, new State(5), new Section(3), new Description(3)).isEmpty());
	}
	
	@Test(expected =  DAOException.class)
	public void testObterListaExcecao(){
		dao.getFramesList(1998, 2005, new State(5), new Section(3), new Description(3));
	}
	
	@Test
	public void testAdicionar(){
		dao.addFrame(quadro);
	}
	
	@Test
	public void testQuadroInexistente(){
		assertFalse(dao.frameExists(quadro));
	}

	@Test
	public void testeExisteQuadro(){
		Frame quadroExistente = new Frame();
		quadroExistente.setYear(2011);
		quadroExistente.setDescription(new Description(3));
		quadroExistente.setState(new State(5));
		quadroExistente.setSection(new Section(15));
		quadroExistente.setValue(529532);
		assertTrue(dao.frameExists(quadroExistente));
	}
	
	@After
	public void excluirQuadro(){
		createConnection();
		String sql = "DELETE FROM Quadro WHERE ano = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, this.quadro.getYear());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
