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
		boolean expResult = true;
		boolean result = dao.validaLogin("pedrodelyra10", "mudar123");
		assertEquals(expResult, result);
	}
	
	@Test
	public void testValidaLoginFalse(){
		boolean expResult = false;
		boolean result = dao.validaLogin("Nome Inválido", "Senha Inválida");
		assertEquals(expResult, result);
	}
	
	@Test
	public void testExisteNomeDeUsuario() {
		dao.existeNomeDeUsuario("Novousuario");
	}
	
	@Test(expected = DAOException.class)
	public void testExisteNomeDeUsuarioShouldThrowException() {
		dao.existeNomeDeUsuario("usuarioInexistente");
	}
	
	@Test
	public void testBuscaAdministrador(){
		dao.buscaAdm("pedrodelyra10", "mudar123");
	}
	@Test(expected = DAOException.class)
	public void testBuscaAdministradorShouldThrowException(){
		dao.buscaAdm("pedrodelyra10", "senhainvalida");
	}

}