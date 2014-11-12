package org.meg.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.meg.controller.CadastroAdministradorServlet;
import org.meg.dao.ConnectionFactory;

public class CadastroAdministradorServletTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection connection;

	@Before
	public void setUp() throws Exception {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}
	
	public void createConnection(){
		this.connection = ConnectionFactory.getConnection();
	}
		
	@Test
	public void testExecuta() throws ServletException, IOException {
		when(request.getParameter("senha")).thenReturn("mudar123");
		when(request.getParameter("confirmacao")).thenReturn("mudar123");
		when(request.getParameter("nomeDeUsuario")).thenReturn("guilhermedelyra2014");
		when(request.getParameter("nome")).thenReturn("Guilherme de Lyra");
		when(request.getParameter("email")).thenReturn("guilhermedelyra@gmail.com");
		when(request.getRequestDispatcher("login-adm.jsp")).thenReturn(
				mock(RequestDispatcher.class));
		new CadastroAdministradorServlet().doPost(request, response);
	}
	
	@After
	public void excluirQuadro(){
		createConnection();
		String sql = "DELETE FROM Administrador WHERE nome_de_usuario = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, "guilhermedelyra2014");
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
