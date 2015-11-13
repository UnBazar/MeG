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
import org.meg.controller.EnumAttribute;
import org.meg.controller.RankingServlet;

public class RankingServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}
	
	@Test
	public void doGetTest() throws ServletException, IOException {
		when(request.getRequestDispatcher("generate-table.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		RankingServlet servlet = new RankingServlet();
		servlet.doGet(request, response);
}

	@Test
	public void doPostTest() throws ServletException, IOException {
			when(request.getParameter(EnumAttribute.YEAR.toString())).thenReturn("2012");
			when(request.getParameter(EnumAttribute.SECTION.toString())).thenReturn("3");
			when(request.getParameter(EnumAttribute.DESCRIPTION.toString())).thenReturn("5");
			when(request.getRequestDispatcher("table.jsp")).thenReturn(
					mock(RequestDispatcher.class));
			RankingServlet servlet = new RankingServlet();
			servlet.doPost(request, response);
	}
}
