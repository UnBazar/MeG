package br.org.meg.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import br.org.meg.controller.LoginAdm;

public class LoginAdmTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession(true)).thenReturn(mock(HttpSession.class));
	}

	@Test
	public void testExecuta() {
		when(request.getParameter("nomeDeUsuario")).thenReturn("pedrodelyra");
		when(request.getParameter("senha")).thenReturn("mudar123");
		LoginAdm servlet = new LoginAdm();
		assertEquals("/WEB-INF/jsp/home.jsp", servlet.executa(request, response));
	}

}
