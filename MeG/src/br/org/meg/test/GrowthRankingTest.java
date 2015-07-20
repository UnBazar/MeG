package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.meg.controller.GrowthRanking;

@Ignore
public class GrowthRankingTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);

		when(request.getParameter("initialYear")).thenReturn("2008");
		when(request.getParameter("finalYear")).thenReturn("2012");
		when(request.getParameter("section")).thenReturn("3");
		when(request.getParameter("description")).thenReturn("5");
	}

	@Test
	public void test() {
		GrowthRanking growthRanking = new GrowthRanking();
		growthRanking.execute(request, response);
	}

}
