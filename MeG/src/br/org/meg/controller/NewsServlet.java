package org.meg.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import org.meg.dao.UtilDAO;
import org.meg.model.News;

/**
 * Servlet implementation class Noticia
 */
public class NewsServlet extends HttpServlet {
	
	private static NewsServlet noticiaServ = new NewsServlet();
	
	public NewsServlet() {

	}
	
	public static NewsServlet getInstance() {
		return noticiaServ;
	}
	
	public ArrayList<News> exibirNoticias(){
		UtilDAO utilDAO = new UtilDAO();
		ArrayList<News> noticias = utilDAO.prepararNoticia();
		return noticias;
	}

}
