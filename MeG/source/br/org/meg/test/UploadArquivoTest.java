package br.org.meg.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.junit.Before;
import org.junit.Test;

import br.org.meg.controller.UploadArquivo;
import br.org.meg.model.Administrador;

public class UploadArquivoTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UploadArquivo uploadArquivo;
	private Administrador administrador;
	private List<FileItem> items;

	@Before
	public void setUp() throws Exception {
		uploadArquivo = new UploadArquivo();
		administrador = new Administrador();
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
	}

	@Test
	public void testExecuta() {
		assertEquals("index.jsp", uploadArquivo.executa(request, response));
	}
	
	@Test
	public void testFormatarNomeUsuario(){
		administrador.setNome("nome de administrador");
		assertEquals("nome_de_administrador", uploadArquivo.formatarNomeUsuario(administrador));
	}
	
	@Test
	public void testGetNumeroDeSecoes(){
		uploadArquivo.getNumeroDeSecoes(items);
	}
	
	@Test
	public void testValidaArquivo(){
		
	}
	

}
