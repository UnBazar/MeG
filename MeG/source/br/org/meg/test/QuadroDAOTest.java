package br.org.meg.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Descricao;
import br.org.meg.model.Estado;
import br.org.meg.model.Secao;

public class QuadroDAOTest {

	private QuadroDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new QuadroDAO();
	}

	@Test
	public void testObterLista() {
		assertFalse(dao.obterLista(2008, 2012, new Estado(5), new Secao(3), new Descricao(3)).isEmpty());
	}
	
	@Test(expected =  NullPointerException.class)
	public void testObterListaExcecao(){
		dao.obterLista(1998, 2014, new Estado(5), new Secao(3), new Descricao(3));
	}
	
	@Test(expected = SQLException.class)
	public void testParametroInvalidoObterLista(){
		//Nao se achou casos em que se retorna essa Exception
	}
}
