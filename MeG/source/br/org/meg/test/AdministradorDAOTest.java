package br.org.meg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.exception.DAOException;
import br.org.meg.model.Administrador;

public class AdministradorDAOTest {

	private Administrador adm;
	private AdministradorDAO dao;
	
	@Before
	public void setUp() throws Exception {
		adm = new Administrador();
		dao = new AdministradorDAO();
	}

	@Test
	public void testAdicionar() {
		adm.setNome("Pedro de Lyra");
		adm.setNomeDeUsuario("pedrodelyra10");
		adm.setEmail("plp_129@hotmail.com");
		adm.setSenha("mudar123");
		dao.adicionar(adm);
	}
	
	@Test 
	public void testValidaLoginTrue() {
		AdministradorDAO dao = new AdministradorDAO();
		boolean expResult = true;
		boolean result = dao.validaLogin("pedrodelyra", "mudar123");
		assertEquals(expResult, result);
	}
	
	@Test
	public void testValidaLoginFalse(){
		AdministradorDAO dao = new AdministradorDAO();
		boolean expResult = false;
		boolean result = dao.validaLogin("Nome Inválido", "Senha Inválida");
		assertEquals(expResult, result);
	}
	
	@Test
	public void testExisteNomeDeUsuario() {
		dao.existeNomeDeUsuario("pedrodelyra");
	}
	
	@Test(expected = DAOException.class)
	public void testExisteNomeDeUsuarioShouldThrowException() {
		dao.existeNomeDeUsuario("usuarioInexistente");
	}
	
	@Test
	public void testBuscaAdministrador(){
		dao.buscaAdm("pedrodelyra", "mudar123");
	}
	@Test(expected = DAOException.class)
	public void testBuscaAdministradorShouldThrowException(){
		dao.buscaAdm("pedrodelyra", "senhainvalida");
	}

}