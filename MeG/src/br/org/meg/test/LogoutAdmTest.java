package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import org.meg.controller.LogoutAdministradorServlet;

public class LogoutAdmTest {
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
	}

	@Test
	public void testService() {
		when(request.getRequestDispatcher("login-adm.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		LogoutAdministradorServlet servlet = new LogoutAdministradorServlet();
		servlet.service(request, response);
	}

}
