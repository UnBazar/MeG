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

import org.meg.dao.FrameDAO;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

/**
 * It's a Servlet class, your function is called in /compara
 * This class compares two states in same section and years
 */
@WebServlet ("/compara")
public class CompareServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final String TYPE_NORMAL_CHOICE = "geral";
	private final String TYPE_PERCENTAGE_CHOICE = "do crescimento";
	private final String ATTRIBUTE_NAME_SECOND_LIST_DATA = "valores2";
	private final String VIEW_NAME = "compara.jsp";
	
	public CompareServlet(){
		super();
	}
	
	/**
	 * Create an list with all scenes requested 
	 * 
	 * @param request have parameters in session and passed by parameters
	 * @return List<Quadro> with scenes corresponding 
	 */
	public List<Frame> getSelectedScenes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// Instantiate an State from an id passed by parameter
		int stateId = Integer.parseInt(request.getParameter("estado"));
		State state = new State(stateId);
		// Put second state name in session
		session.setAttribute("secondStateName", state.getNome());
		// Get the name of section of scene stored in session object
		String sectionName = (String) session.getAttribute("secao"); 
		// Instantiate an Section from your name
		Section section = new Section(sectionName);
		// Get title of scene stored in session
		String title = (String)session.getAttribute("titulo");
		// Instantiate an Section from your title
		Description descricao = new Description(title);
		/*
		 * Get all years in specific interval set initialS and final year in two variables
		 */
		@SuppressWarnings("unchecked")
		
		List<String> years = (List<String>) session.getAttribute("anos");
		int initalYear = Integer.parseInt(years.get(0));
		int finalYear = Integer.parseInt(years.get(years.size()-1));
		
		// Instantiate DAO to get all frames with the following parametters
		FrameDAO SceneDAO = new FrameDAO();
		List<Frame> scenes = SceneDAO.getFramesList(initalYear, finalYear, state, section, descricao);
		return scenes;
	}
	
	/**
	 * Main method, used to generate informations in graphic
	 * 
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
		List<Frame> scenes = getSelectedScenes(request);
		assert(scenes == null);
		/* 
		 * Type of chart data, can be:
		 * - Normal, raw data
		 * - Percentage increase
		 */
		String choiceTypeOfData = (String) session.getAttribute("grafico");
		assert(choiceTypeOfData==null);
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
	 * List values with increase betwen two years
	 * 
	 * @param scenes to assess growth
	 * @return List<Float> with all values calculated
	 */
	private List<Float> getIncrease(List<Frame> scenes) {
		// List for values of scenes
		List<Float> values = new ArrayList<Float>();
		float initialValue = 0, finalValue = 0;
		for (int i = 0; i < scenes.size(); i++) {
			if(i == 0) {
				initialValue= scenes.get(i).getValue();
				finalValue = initialValue;
			} else {
				initialValue = finalValue;
				finalValue = scenes.get(i).getValue();
			}
			values.add(calculateIncrease(finalValue, initialValue));
		}
		return values;
	}
	
	/**
	 * Get increase percentage annual
	 * 
	 * @param finalValue is value generate next year
	 * @param initialValue is value of current year
	 * @return float with value of increase
	 */
	private float calculateIncrease(float finalValue, float initialValue){
		float increase = ((finalValue/initialValue)-1)* 100;
		return increase;
	}
}