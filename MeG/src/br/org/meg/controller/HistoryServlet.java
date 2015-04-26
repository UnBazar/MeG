package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.UtilDAO;
import org.meg.model.History;

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
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Used to get numbers of access in functionalities
		UtilDAO utilDAO = new UtilDAO();
		// To get all Histories
		List<History> access = new ArrayList<History>();
		
		/*
		 *  Run all ids possible of histories. 
		 *  The initial value is one because first id in database is 1.
		 *  The loop goes to the value for the length of the features attribute  
		 */
		for(int count = 1; count <= History.functionalities.length; count++){
			access.add(utilDAO.getHistory(count));
		}
		// Put list of access in request
		request.setAttribute("histories", access);
		// Redirect to history view
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(HISTORY_ACESS_VIEW);
		requestDispatcher.forward(request, response);
	}

}