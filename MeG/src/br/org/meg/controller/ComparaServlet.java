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
import javax.servlet.http.HttpSession;

import org.meg.dao.QuadroDAO;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

/**
 * It's an Servlet class, your function is called in /compara
 * This class compares two states in same section and years
 */
@WebServlet ("/compara")
public class ComparaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final String TYPE_NORMAL_CHOICE = "geral";
	private final String TYPE_PERCENTAGE_CHOICE = "do crescimento";
	private final String ATTRIBUTE_NAME_SECOND_LIST_DATA = "valores2";
	private final String VIEW_NAME = "compara.jsp";
	
	public ComparaServlet(){
		super();
	}
	
	public List<Quadro> getSelectedScenes(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		// Instantiate an State from an id passed by parameter
		int stateId = Integer.parseInt(request.getParameter("estado"));
		Estado state = new Estado(stateId);
		// Put second state name in session
		session.setAttribute("secondStateName", state.getNome());
		// Get the name of section of scene stored in session object
		String sectionName = (String) session.getAttribute("secao"); 
		// Instantiate an Section from your name
		Secao section = new Secao(sectionName);
		// Get title of scene stored in session
		String title = (String)session.getAttribute("titulo");
		// Instantiate an Section from your title
		Descricao descricao = new Descricao(title);
		/*
		 * Get all years in certly interval set init and final year in two variables
		 */
		@SuppressWarnings("unchecked")
		
		List<String> years = (List<String>) session.getAttribute("anos");
		int initalYear = Integer.parseInt(years.get(0));
		int finalYear = Integer.parseInt(years.get(years.size()-1));
		
		// Instantiate DAO to get all scenes with the following parametters
		QuadroDAO SceneDAO = new QuadroDAO();
		List<Quadro> scenes = SceneDAO.getListOfScene(initalYear, finalYear, state, section, descricao);
		return scenes;
	}
	
	/**
	 * Main method, used to generate informations in graphic
	 * @param request is the current Request that have parametters 
	 * 		  stored in session
	 * @param response is an new response to generate
	 * 
	 * @exception IOException if an input or output error is detected 
	 * 			  when the servlet handles the request
	 * @exception ServletException if the request for the POST could
	 * 			  not be handled
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Get session of request
		HttpSession session = request.getSession();
		
		// Load scenes from session and request
		List<Quadro> scenes = getSelectedScenes(request);
		
		/* 
		 * Type of chart data, can be:
		 * - Normal, raw data
		 * - Percentage increase
		 */
		String choiceTypeOfData = (String) session.getAttribute("grafico");
		
		// Instantiate method to use an method
		GraphicServlet graphic = new GraphicServlet();
		/*
		 *  If normal type is choice, set an list with all correspondent values
		 *  If percentage type is choice, set list with increase of correspondent values
		 */
		if (choiceTypeOfData.equalsIgnoreCase(TYPE_NORMAL_CHOICE)) {
			session.setAttribute(ATTRIBUTE_NAME_SECOND_LIST_DATA, graphic.getValues(scenes));
		} else if (choiceTypeOfData.equalsIgnoreCase(TYPE_PERCENTAGE_CHOICE)) {
			session.setAttribute(ATTRIBUTE_NAME_SECOND_LIST_DATA, getIncrease(scenes));
		}
		RequestDispatcher rd = request.getRequestDispatcher(VIEW_NAME);
		rd.forward(request, response);
	}
	
	/**
	 * Lista os valores dos quadros contidos na lista global 'quadros' mas fazendo o calculo do crescimento anual
	 * @param quadros
	 * @return uma lista de floats contendo os valores de crescimento
	 */
	private List<Float> getIncrease(List<Quadro> quadros){
		List<Float> valores = new ArrayList<Float>();
		float valorInicial = 0,valorFinal = 0;
		for(int i = 0; i < quadros.size(); i++){
			if(i == 0){
				valorInicial= quadros.get(i).getValor();
				valorFinal = valorInicial;
			}
			else{
				valorInicial = valorFinal;
				valorFinal = quadros.get(i).getValor();
			}
			valores.add(calculaCrescimento(valorFinal,valorInicial));
		}
		
		return valores;
	}
	/**
	 * Calcula o valor do crescimento percentual anual
	 * @param valorFinal
	 * @param valorInicial
	 * @return float com o valor do crescimento
	 */
	private float calculaCrescimento(float valorFinal, float valorInicial){
		float crescimento = ((valorFinal/valorInicial)-1)* 100;
		return crescimento;
	}
}
