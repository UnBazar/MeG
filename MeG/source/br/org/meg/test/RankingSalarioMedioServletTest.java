package br.org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import br.org.meg.controller.RankingSalarioMedioServlet;

public class RankingSalarioMedioServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}

	@Test
	public void doPostTest() throws ServletException, IOException {
		for (int i = 0; i <= 6; i++) {
			when(request.getParameter("ano")).thenReturn(String.valueOf(2006 + i));
			when(request.getParameter("setor")).thenReturn("3");
			when(request.getRequestDispatcher("tabela.jsp")).thenReturn(
					mock(RequestDispatcher.class));
			RankingSalarioMedioServlet servlet = new RankingSalarioMedioServlet();
			servlet.doPost(request, response);
		}
	}

}
