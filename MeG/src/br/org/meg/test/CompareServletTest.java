package org.meg.test;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import org.meg.controller.CompareServlet;

public class CompareServletTest {
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	@Before
	public void setUp() throws Exception {
		this.response = mock(HttpServletResponse.class);
		this.request = mock(HttpServletRequest.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
	}

	@Test
	public void testGeneralDoPost() throws ServletException, IOException {
		when(request.getSession().getAttribute("grafico")).thenReturn("geral");
		when(request.getSession().getAttribute("secao")).thenReturn("Agricultura, pecuária, produção florestal, pesca e aquicultura");
		when(request.getSession().getAttribute("titulo")).thenReturn("Pessoal ocupado total (Pessoas)");
		when(request.getParameter("estado")).thenReturn("1");
		List<String> years = new ArrayList<String>();
		years.add("2008");
		years.add("2012");
		when(request.getSession().getAttribute("anos")).thenReturn(years);
		when(request.getRequestDispatcher("compara.jsp")).thenReturn(mock(RequestDispatcher.class));
		CompareServlet servlet = new CompareServlet();
		servlet.doPost(request, response);
	}
	
	@Test
	public void testDoPostGrowth() throws ServletException, IOException {
		when(request.getSession().getAttribute("grafico")).thenReturn("do Crescimento");
		when(request.getSession().getAttribute("secao")).thenReturn("Agricultura, pecuária, produção florestal, pesca e aquicultura");
		when(request.getSession().getAttribute("titulo")).thenReturn("Pessoal ocupado total (Pessoas)");
		when(request.getParameter("estado")).thenReturn("1");
		List<String> years = new ArrayList<String>();
		years.add("2008");
		years.add("2012");
		when(request.getSession().getAttribute("anos")).thenReturn(years);
		when(request.getRequestDispatcher("compara.jsp")).thenReturn(mock(RequestDispatcher.class));
		CompareServlet servlet = new CompareServlet();
		servlet.doPost(request, response);
	}

}
