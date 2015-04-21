package org.meg.test;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.NewsServlet;

public class NoticiaServletTest {

	private NewsServlet noticia = new NewsServlet();
	
	@Before
	public void setUp() throws Exception {
		this.noticia = new NewsServlet();
	}
	
	@Test
	public void testGetInstance() {
		noticia.getInstance();
	}
	
	@Test
	public void testExibirNoticias() {
		noticia.exibirNoticias();
	}

}
