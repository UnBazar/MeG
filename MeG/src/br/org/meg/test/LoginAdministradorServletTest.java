package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
	public void testExecutaSuccess() throws ServletException, IOException {
		when(request.getParameter("nomeDeUsuario")).thenReturn("pedrodelyra10");
		when(request.getParameter("senha")).thenReturn("mudar123");
		when(request.getRequestDispatcher("WEB-INF/jsp/administrador.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		LoginAdministradorServlet servlet = new LoginAdministradorServlet();
		servlet.doPost(request, response);
	}
	
	@Test
	public void testExecutaFail() throws ServletException, IOException {
		when(request.getParameter("nomeDeUsuario")).thenReturn("usuarioInexistente");
		when(request.getParameter("senha")).thenReturn("mudar123");
		when(request.getRequestDispatcher("login-adm.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		LoginAdministradorServlet servlet = new LoginAdministradorServlet();
		servlet.doPost(request, response);
	}

}
