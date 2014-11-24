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
import org.meg.controller.ErroServlet;

public class ErroServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ErroServlet servlet;
	
	@Before
	public void setUp(){
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		this.servlet = new ErroServlet();
	}
	@Test
	public void doPostTest() throws ServletException, IOException{
		when(request.getRequestDispatcher("WEB-INF/jsp/tabela-erro.jsp")).thenReturn(mock(RequestDispatcher.class));
		servlet.doPost(request, response);
	}

}
