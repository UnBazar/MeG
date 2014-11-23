package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.UtilDAO;

/**
 * Servlet implementation class HistoricoServlet
 */
@WebServlet("/historico")
public class HistoricoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoricoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UtilDAO dao = new UtilDAO();
		int idRanking = 1;
		int idCompara = 2;
		int idProjecao = 3;
		int idGrafico = 4;
		List<Integer> acessos = new ArrayList<Integer>();
		int numeroAcessoRanking = dao.getHistorico(idRanking);
		int numeroAcessoCompara = dao.getHistorico(idCompara);
		int numeroAcessoProjecao = dao.getHistorico(idProjecao);
		int numeroAcessoGrafico = dao.getHistorico(idGrafico);
		acessos.add(numeroAcessoRanking);
		request.setAttribute("lista", acessos);
		request.setAttribute("ranking", numeroAcessoRanking);
		request.setAttribute("compara", numeroAcessoCompara);
		request.setAttribute("projecao", numeroAcessoProjecao);
		request.setAttribute("grafico", numeroAcessoGrafico);
		request.getRequestDispatcher("WEB-INF/jsp/historico-acesso.jsp").forward(request, response);
	}

}
