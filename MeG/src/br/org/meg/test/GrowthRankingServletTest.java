package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.GrowthRankingServlet;

public class GrowthRankingServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}

	@Test
	public void doPostTest() throws ServletException, IOException {
		when(request.getParameter("anoInicial")).thenReturn("2008");
		when(request.getParameter("anoFinal")).thenReturn("2012");
		when(request.getParameter("setor")).thenReturn("3");
		when(request.getParameter("descricao")).thenReturn("5");
		when(request.getRequestDispatcher("growth-table.jsp"))
				.thenReturn(mock(RequestDispatcher.class));
		GrowthRankingServlet servlet = new GrowthRankingServlet();
		servlet.doPost(request, response);
	}

}
