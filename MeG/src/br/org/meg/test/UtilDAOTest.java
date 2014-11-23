package org.meg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.meg.dao.UtilDAO;
import org.mockito.Mock;

public class UtilDAOTest {
	private UtilDAO dao;
	private UtilDAO daoMock;
	@Before
	public void setUp() throws Exception {
		dao = new UtilDAO();
	}

	@Test
	public void getNomeEstadoTest() {
		String nome = "Acre";
		assertEquals(dao.getNomeEstado(1), nome);
	}

	@Test
	public void getSiglaEstadoTest(){
		String sigla = "AC";
		assertEquals(dao.getSiglaEstado(1), sigla);
	}
	
	@Test
	public void getIdEstadoTest(){
		int id = 1;
		assertEquals(dao.getIdEstado("Acre"), id);
	}
	
	@Test
	public void getNomeSecaoTest(){
		String nome = "Agricultura, pecuária, produção florestal, pesca e aquicultura";
		assertEquals(dao.getNomeSecao(1), nome);
	}
	
	@Test
	public void getIdSecaoTest(){
		int id = 1;
		assertEquals(dao.getIdSecao("Agricultura, pecuária, produção florestal, pesca e aquicultura"), id);
	}
	
	@Test
	public void getNomeDescricaoTest(){
		String nome = "Número de empresas e outras organizações (Unidades)";
		assertEquals(dao.getNomeDescricao(1), nome);
	}
	
	@Test
	public void getIdDescricaoTest(){
		int id = 1;
		assertEquals(dao.getIdDescricao("Número de empresas e outras organizações (Unidades)"), id);
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
		assertNotNull(dao.getHistorico(id));
	}
}
