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
import org.meg.controller.ProjectionServlet;

public class ProjectionTest {
	
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
		when(request.getRequestDispatcher("generate-projection.jsp")).thenReturn(mock(RequestDispatcher.class));
		ProjectionServlet servlet = new ProjectionServlet();
		servlet.doGet(request, response);
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		final int numberOfStates = 27;
		final int numberOfDescriptions = 5;
		final int numberOfSections = 21;
		
		for (int state = 1; state <= numberOfStates; state++) {
			for (int description = 1; description <= numberOfDescriptions; description++) {
				for (int section = 1; section <= numberOfSections; section++) {
					when(request.getParameter("state")).thenReturn(Integer.toString(state));
					when(request.getParameter("description")).thenReturn(Integer.toString(description));
					when(request.getParameter("section")).thenReturn(Integer.toString(section));
					when(request.getParameter("finalYear")).thenReturn("2013");
					when(request.getSession()).thenReturn(mock(HttpSession.class));
					when(request.getRequestDispatcher("projection.jsp"))
									.thenReturn(mock(RequestDispatcher.class));
					ProjectionServlet servlet = new ProjectionServlet();
					servlet.doPost(request, response);
				}
			}
		}
	}

}
