package org.meg.test;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.NoticiaServlet;

public class NoticiaServletTest {

	private NoticiaServlet noticia = new NoticiaServlet();
	
	@Before
	public void setUp() throws Exception {
		this.noticia = new NoticiaServlet();
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
