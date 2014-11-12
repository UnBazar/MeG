package org.meg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.meg.dao.AdministradorDAO;
import org.meg.model.Administrador;

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
	public void testExisteNomeDeUsuario() {
		dao.existeNomeDeUsuario("Novousuario");
	}
	
	@Test
	public void testExisteNomeDeUsuarioShouldThrowException() {
		assertFalse(dao.existeNomeDeUsuario("usuarioInexistente"));
	}
	
	@Test
	public void testBuscaAdministrador(){
		dao.buscaAdministrador("pedrodelyra10", "mudar123");
	}
	
	@Test
	public void testBuscaAdministradorShouldThrowException(){
		assertNull(dao.buscaAdministrador("pedrodelyra10", "senhaInválida"));
	}

}