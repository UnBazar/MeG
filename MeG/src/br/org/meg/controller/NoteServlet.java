package org.meg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.UtilDAO;
import org.meg.model.Note;

/**
 * Set notices in initial page
 */
@WebServlet(urlPatterns = {"/index.html"})
public class NoteServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Set some observations extracted of system to view in index page
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DAO used to getNotices
		UtilDAO utilDAO = new UtilDAO();
		// Get randomic notes
		List<Note> observations = utilDAO.getNotes();
		// Set an initial note
		Note defaultNote = new Note();
		defaultNote = observations.get(0);
		// Remove the list of note to not duplicate
		observations.remove(0);
		// Set in request
		request.setAttribute("notes", observations);
		request.setAttribute("defaultNote", defaultNote);
		// Redirect to home.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(request, response);
	}

}
