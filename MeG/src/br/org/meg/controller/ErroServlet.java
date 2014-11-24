package org.meg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.UtilDAO;
import org.meg.model.Erro;

/**
 * Servlet implementation class ErroServlet
 */
@WebServlet("/erro")
public class ErroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErroServlet() {
        super();
    }

	/** método post que manda uma tabela de erros para a tabela-erro.jsp
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilDAO dao = new UtilDAO();
		List<Erro> listaErros = dao.obterErros();
		request.setAttribute("lista", listaErros);		
		request.getRequestDispatcher("WEB-INF/jsp/tabela-erro.jsp").forward(request, response);
	}

}
