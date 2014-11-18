package org.meg.test;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.HistoricoFiltro;

public class HistoricoFiltroTest {

	private HttpServletResponse response;
	private HttpServletRequest request;
	private HistoricoFiltro historico;
	private FilterConfig filterConfig;
	
	@Before
	public void setUp() throws Exception {
		this.response = mock(HttpServletResponse.class);
		this.request = mock(HttpServletRequest.class);
		this.historico = new HistoricoFiltro();
		this.filterConfig = mock(FilterConfig.class);
	}
	
	@Test
	public void testDoFilterRanking() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/ranking");
		FilterChain chain = mock(FilterChain.class);
		this.historico.doFilter(request, response, chain);
		this.historico.destroy();
	}
	
	@Test
	public void testDoFilterCompara() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/compara");
		FilterChain chain = mock(FilterChain.class);
		this.historico.doFilter(request, response, chain);
		this.historico.destroy();
	}
	
	@Test
	public void testDoFilterProjecao() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/projecao");
		FilterChain chain = mock(FilterChain.class);
		this.historico.doFilter(request, response, chain);
		this.historico.destroy();
	}
	
	@Test
	public void testDoFilterGrafico() throws IOException, ServletException{
		when(this.request.getRequestURI()).thenReturn("/grafico");
		FilterChain chain = mock(FilterChain.class);
		this.historico.doFilter(request, response, chain);
		this.historico.destroy();
	}
	
	@Test
	public void testInit() throws ServletException{
		this.historico.init(filterConfig);
	}

}
