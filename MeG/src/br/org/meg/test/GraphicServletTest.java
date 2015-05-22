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
import org.meg.controller.GraphicServlet;

public class GraphicServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getParameter("description")).thenReturn("1");
		when(request.getParameter("section")).thenReturn("1");
		when(request.getParameter("state")).thenReturn("1");
		when(request.getRequestDispatcher("graphic.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		when(request.getSession()).thenReturn(mock(HttpSession.class));
	}
	@Test
	public void test1() throws ServletException, IOException {
		when(request.getParameter("grafico")).thenReturn("geral");
		when(request.getParameter("initialYear")).thenReturn("2007");
		when(request.getParameter("finalYear")).thenReturn("2012");
		GraphicServlet servlet = new GraphicServlet();
		servlet.doPost(request, response);
	}
	@Test
	public void test2() throws ServletException, IOException {
		when(request.getParameter("grafico")).thenReturn("do crescimento");
		when(request.getParameter("initialYear")).thenReturn("2007");
		when(request.getParameter("finalYear")).thenReturn("2012");
		GraphicServlet servlet = new GraphicServlet();
		servlet.doPost(request, response);
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testInvalidOptionException() throws ServletException, IOException{
		when(request.getParameter("grafico")).thenReturn("INVALID");
		when(request.getParameter("initialYear")).thenReturn("2007");
		when(request.getParameter("finalYear")).thenReturn("2012");
		GraphicServlet servlet = new GraphicServlet();
		servlet.doPost(request, response);
		when(request.getParameter("initialYear")).thenReturn("2012");
		servlet.doPost(request, response);
	}
	
	@Test(expected=RuntimeException.class)
	public void testEqualsDateException() throws ServletException, IOException{
		when(request.getParameter("grafico")).thenReturn("INVALID");
		when(request.getParameter("initialYear")).thenReturn("2012");
		when(request.getParameter("finalYear")).thenReturn("2012");
		GraphicServlet servlet = new GraphicServlet();
		servlet.doPost(request, response);
	}
	
	@Test
	public void testDoGet() throws ServletException, IOException{
		when(request.getRequestDispatcher("generate-graphic.jsp")).thenReturn(mock(RequestDispatcher.class));
		GraphicServlet servlet = new GraphicServlet();
		servlet.doGet(request, response);
	}
}
