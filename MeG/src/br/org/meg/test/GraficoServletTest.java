package org.meg.test;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import org.meg.controller.GraficoServlet;

public class GraficoServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
	}
	@Test
	public void test1() throws ServletException, IOException {
		when(request.getParameter("grafico")).thenReturn("geral");
		when(request.getParameter("descricao")).thenReturn("1");
		when(request.getParameter("setor")).thenReturn("1");
		when(request.getParameter("estado")).thenReturn("1");
		when(request.getParameter("anoInicial")).thenReturn("2007");
		when(request.getParameter("anoFinal")).thenReturn("2012");
		when(request.getRequestDispatcher("grafico.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		GraficoServlet servlet = new GraficoServlet();
		servlet.doPost(request, response);
	}
	@Test
	public void test2() throws ServletException, IOException {
		when(request.getParameter("grafico")).thenReturn("do crescimento");
		when(request.getParameter("descricao")).thenReturn("1");
		when(request.getParameter("setor")).thenReturn("1");
		when(request.getParameter("estado")).thenReturn("1");
		when(request.getParameter("anoInicial")).thenReturn("2007");
		when(request.getParameter("anoFinal")).thenReturn("2012");
		when(request.getRequestDispatcher("grafico.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		GraficoServlet servlet = new GraficoServlet();
		servlet.doPost(request, response);
	}
}
