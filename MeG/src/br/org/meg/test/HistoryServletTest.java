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
import org.meg.controller.HistoryServlet;

public class HistoryServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HistoryServlet servlet;
	
	@Before
	public void setUp() throws Exception{
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		this.servlet = new HistoryServlet();
	}
	
	@Test
	public void doPostTest() throws ServletException, IOException{
		when(request.getRequestDispatcher("WEB-INF/jsp/access-history.jsp")).thenReturn(mock(RequestDispatcher.class));
		servlet.doPost(request, response);
	}

}
