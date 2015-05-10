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
import org.meg.controller.ProjectionServlet;

public class ProjecaoTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}
	
	@Test
	public void testDoGet() throws ServletException, IOException{
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		when(request.getRequestDispatcher("gerar-projecao.jsp")).thenReturn(mock(RequestDispatcher.class));
		ProjectionServlet servlet = new ProjectionServlet();
		servlet.doGet(request, response);
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		for (int estado = 1; estado <= 27; estado++) {
			for (int descricao = 1; descricao <= 5; descricao++) {
				for (int secao = 1; secao <= 21; secao++) {
					when(request.getParameter("state")).thenReturn(Integer.toString(estado));
					when(request.getParameter("description")).thenReturn(Integer.toString(descricao));
					when(request.getParameter("section")).thenReturn(Integer.toString(secao));
					when(request.getParameter("finalYear")).thenReturn("2013");
					when(request.getSession()).thenReturn(mock(HttpSession.class));
					when(request.getRequestDispatcher("projecao.jsp"))
									.thenReturn(mock(RequestDispatcher.class));
					ProjectionServlet servlet = new ProjectionServlet();
					servlet.doPost(request, response);
				}
			}
		}
	}

}
