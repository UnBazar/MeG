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
import org.meg.controller.AdministratorLoginServlet;
import org.meg.model.Administrator;

public class LoginAdministradorServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession(true)).thenReturn(mock(HttpSession.class));
		when(request.getSession()).thenReturn(mock(HttpSession.class));
	}
	
	@Test
	public void testRedirectToLogin() throws ServletException, IOException{
		when(request.getSession().getAttribute("administrador")).thenReturn(null);
		when(request.getRequestDispatcher("login-adm.jsp"))
				.thenReturn(mock(RequestDispatcher.class));
		AdministratorLoginServlet servlet = new AdministratorLoginServlet();
		servlet.doGet(request, response);
	}
	
	@Test
	public void testRedirectToAdministratorView() throws ServletException, IOException{
		Administrator administrator = new Administrator();
		when(request.getSession().getAttribute("administrador")).thenReturn(administrator);
		when(request.getRequestDispatcher("WEB-INF/jsp/administrador.jsp"))
				.thenReturn(mock(RequestDispatcher.class));
		AdministratorLoginServlet servlet = new AdministratorLoginServlet();
		servlet.doGet(request, response);
	}

	@Test
	public void testLoginSucess() throws ServletException, IOException {
		when(request.getParameter("nomeDeUsuario")).thenReturn("pedrodelyra10");
		when(request.getParameter("senha")).thenReturn("mudar123");
		when(request.getRequestDispatcher("WEB-INF/jsp/administrador.jsp"))
				.thenReturn(mock(RequestDispatcher.class));
		AdministratorLoginServlet servlet = new AdministratorLoginServlet();
		servlet.doPost(request, response);
	}

	@Test
	public void testLoginFailed() throws ServletException, IOException {
		when(request.getParameter("nomeDeUsuario")).thenReturn(
				"usuarioInexistente");
		when(request.getParameter("senha")).thenReturn("mudar123");
		when(request.getRequestDispatcher("login-adm.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		AdministratorLoginServlet servlet = new AdministratorLoginServlet();
		servlet.doPost(request, response);
	}
}
