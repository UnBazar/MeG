package org.meg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.meg.dao.AdministratorDAO;
import org.meg.model.Administrator;

public class AdministradorDAOTest {

	private Administrator adm;
	private AdministratorDAO dao;
	
	@Before
	public void setUp() throws Exception {
		adm = new Administrator();
		dao = new AdministratorDAO();
	}

	@Test
	public void testAdicionar() {
		adm.setName("Pedro de Lyra");
		adm.setUserName("pedrodelyra10");
		adm.setEmail("plp_129@hotmail.com");
		adm.setPassword("mudar123");
		dao.addAdministrator(adm);
	}
	
	@Test
	public void testExisteNomeDeUsuario() {
		dao.existsNameOfAdministrator("Novousuario");
	}
	
	@Test
	public void testExisteNomeDeUsuarioShouldThrowException() {
		assertFalse(dao.existsNameOfAdministrator("usuarioInexistente"));
	}
	
	@Test
	public void testBuscaAdministrador(){
		dao.searchAdministrator("pedrodelyra10", "mudar123");
	}
	
	@Test
	public void testBuscaAdministradorShouldThrowException(){
		assertNull(dao.searchAdministrator("pedrodelyra10", "senhaInválida"));
	}

}