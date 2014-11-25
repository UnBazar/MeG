package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.ProjecaoServlet;

public class ProjecaoTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}

	@Test
	public void test() throws ServletException, IOException {
		for (int estado = 1; estado <= 27; estado++) {
			for (int descricao = 1; descricao <= 5; descricao++) {
				for (int secao = 1; secao <= 21; secao++) {
					when(request.getParameter("estado")).thenReturn(Integer.toString(estado));
					when(request.getParameter("descricao")).thenReturn(Integer.toString(descricao));
					when(request.getParameter("setor")).thenReturn(Integer.toString(secao));
					when(request.getParameter("anoFinal")).thenReturn("2012");
					when(request.getSession()).thenReturn(mock(HttpSession.class));
					when(request.getRequestDispatcher("grafico.jsp")).thenReturn(mock(RequestDispatcher.class));
					ProjecaoServlet servlet = new ProjecaoServlet();
					servlet.doPost(request, response);
				}
			}
		}
		System.out.println(ProjecaoServlet.b.divide(new BigDecimal(2835.0), 4, RoundingMode.UP));
		System.out.println(ProjecaoServlet.numeroDeAcertos);
		System.out.println(ProjecaoServlet.quaseAcertos);
	}

}
