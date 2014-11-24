package org.meg.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import org.meg.dao.UtilDAO;
import org.meg.model.Noticia;

/**
 * Servlet implementation class Noticia
 */
public class NoticiaServlet extends HttpServlet {
	
	private static NoticiaServlet noticiaServ = new NoticiaServlet();
	
	public NoticiaServlet() {}
	
	public static NoticiaServlet getInstance() {
		return noticiaServ;
	}
	
	public ArrayList<Noticia> exibirNoticias(){
		UtilDAO utilDAO = new UtilDAO();
		ArrayList<Noticia> noticias = utilDAO.prepararNoticia();
		return noticias;
	}

}
