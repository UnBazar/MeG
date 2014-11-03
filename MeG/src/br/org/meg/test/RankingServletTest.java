package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.RankingServlet;
import org.meg.model.Quadro;

public class RankingServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}

	@Test
	public void doPostTest() throws ServletException, IOException {
			when(request.getParameter("ano")).thenReturn("2012");
			when(request.getParameter("setor")).thenReturn("3");
			when(request.getParameter("descricao")).thenReturn("5");
			when(request.getRequestDispatcher("tabela.jsp")).thenReturn(
					mock(RequestDispatcher.class));
			RankingServlet servlet = new RankingServlet();
			servlet.doPost(request, response);
	}
}
