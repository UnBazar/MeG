package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.LoginAdministradorServlet;

public class LoginAdministradorServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession(true)).thenReturn(mock(HttpSession.class));
	}

	@Test
	public void testExecuta() throws ServletException, IOException {
		when(request.getParameter("nomeDeUsuario")).thenReturn("pedrodelyra10");
		when(request.getParameter("senha")).thenReturn("mudar123");
		LoginAdministradorServlet servlet = new LoginAdministradorServlet();
		servlet.doPost(request, response);
	}

}
