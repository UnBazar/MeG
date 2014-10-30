package org.meg.test;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.meg.controller.UploadArquivoServlet;

public class UploadArquivoServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UploadArquivoServlet uploadArquivo;

	@Before
	public void setUp() throws Exception {
		uploadArquivo = new UploadArquivoServlet();
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
	}

	@Test
	public void testExecuta() throws ServletException, IOException {
		uploadArquivo.doPost(request, response);
	}

}
