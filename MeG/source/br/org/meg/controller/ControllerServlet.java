package br.org.meg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sistema")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parametro = request.getParameter("logica");
		String nomeDaClasse = "br.org.meg.controller." + parametro;
		
		try {
			Class<?> classe = Class.forName(nomeDaClasse);
			Logica logica = (Logica) classe.newInstance();
			
			String pagina = logica.executa(request, response);
			request.getRequestDispatcher(pagina).forward(request, response);
			
		} catch (ClassNotFoundException exception) {
			System.err.println(exception);
			throw new RuntimeException("Classe não encontrada!");
		} catch (IllegalAccessException exception) {
			System.err.println(exception);
			throw new RuntimeException("Classe não acessível!");
		} catch (InstantiationException exception) {
			System.err.println(exception);
			throw new RuntimeException("Erro ao instanciar a classe!");
		}
	}
	
}
