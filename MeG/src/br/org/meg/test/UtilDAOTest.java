package org.meg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.meg.dao.UtilDAO;

public class UtilDAOTest {
	private UtilDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new UtilDAO();
	}

	@Test
	public void getSiglaEstadoTest(){
		String sigla = "AC";
		assertEquals(dao.getSiglaEstado(1), sigla);
	}
	
	@Test
	public void getSalarioMinimoTest(){
		int ano = 2008;
		assertEquals(dao.getSalarioMinimo(ano), 415.00 , 0.001);
	}
	
	@Test
	public void obterErrosTest(){
		assertNotNull(dao.obterErros());
	}

	@Test
	public void getHistoricoTest(){
		int id = 1;
		assertNotNull(dao.getHistory(id));
	}
}
