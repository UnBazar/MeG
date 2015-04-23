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
 * It's a Servlet class Its function is called in /historico.
 * This class saves the number of access of every functionality 
 */
@WebServlet("/historico")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String HISTORY_ACESS_VIEW =  "WEB-INF/jsp/historico-acesso.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/** Method post which modifies the table and redirects to the view page 
	 * Getting the history through the database.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UtilDAO dao = new UtilDAO();
		
		int idRanking = 1;
		int idCompara = 2;
		int idProjection = 3;
		int idGraphic = 4;
		
		List<Integer> access = new ArrayList<Integer>();
		
		int numberAccessRanking = dao.getHistory(idRanking);
		int numberAccessCompara = dao.getHistory(idCompara);
		int numberAccessProjection = dao.getHistory(idProjection);
		int numberAccessGraphic = dao.getHistory(idGraphic);
		
		access.add(numberAccessRanking);
		
		request.setAttribute("lista", access);
		request.setAttribute("ranking", numberAccessRanking);
		request.setAttribute("compara", numberAccessCompara);
		request.setAttribute("projecao", numberAccessProjection);
		request.setAttribute("grafico", numberAccessGraphic);
		request.getRequestDispatcher(HISTORY_ACESS_VIEW).forward(request, response);
	}

}
