package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.FilterHistory;

public class HistoryFilterTest {

	private HttpServletResponse response;
	private HttpServletRequest request;
	private FilterHistory history;
	private FilterConfig filterConfig;
	
	@Before
	public void setUp() throws Exception {
		this.response = mock(HttpServletResponse.class);
		this.request = mock(HttpServletRequest.class);
		this.history = new FilterHistory();
		this.filterConfig = mock(FilterConfig.class);
	}
	
	@Test
	public void testDoFilterRanking() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/ranking");
		FilterChain chain = mock(FilterChain.class);
		this.history.doFilter(request, response, chain);
		this.history.destroy();
	}
	
	@Test
	public void testDoFilterCompare() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/compara");
		FilterChain chain = mock(FilterChain.class);
		this.history.doFilter(request, response, chain);
		this.history.destroy();
	}
	
	@Test
	public void testDoFilterProjection() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/projecao");
		FilterChain chain = mock(FilterChain.class);
		this.history.doFilter(request, response, chain);
		this.history.destroy();
	}
	
	@Test
	public void testDoFilterGraphic() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/grafico");
		FilterChain chain = mock(FilterChain.class);
		this.history.doFilter(request, response, chain);
		this.history.destroy();
	}
	
	@Test
	public void testInit() throws ServletException{
		this.history.init(filterConfig);
	}

}
