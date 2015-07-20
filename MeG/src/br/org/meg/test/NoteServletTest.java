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
import org.meg.controller.NoteServlet;

public class NoteServletTest {
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	@Before
	public void setUp() throws Exception {
		this.response = mock(HttpServletResponse.class);
		this.request = mock(HttpServletRequest.class);
	}
	
	@Test
	public void testIndex() throws ServletException, IOException{
		when(request.getRequestDispatcher("home.jsp")).thenReturn(mock(RequestDispatcher.class));
		NoteServlet servletTest = new NoteServlet();
		servletTest.doGet(request, response);
	}

}
