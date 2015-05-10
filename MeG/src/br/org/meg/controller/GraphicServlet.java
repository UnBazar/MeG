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

import org.meg.dao.EnumTable;
import org.meg.dao.FrameDAO;
import org.meg.dao.GenericModelDAO;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

/**
 * Responsable for plot an custom graphic
 */
@WebServlet("/grafico")
public class GraphicServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// Weight to show values in percent
	private static final float FACTOR_PERCENTAGE = 100;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphicServlet() {
        super();
    }
    
	/**
	 * Redirect to the page that contain form that will generate graphic
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// List options of fields programatically
		session.setAttribute("descriptions", listModel(EnumTable.DESCRIPTION));
		session.setAttribute("sections", listModel(EnumTable.SECTION));
		session.setAttribute("states", listModel(EnumTable.STATE));
		// Redirect to an form
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("gerar-grafico.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Plot graphic, can be:
	 * <list>
	 * 	<ul>Normal data
	 * 	<ul>Percentage data, calculate from normal
	 * </list>
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// List that will contain requested frames
		List<Frame> frames = new ArrayList<>();
		/* 
		 * Get option of graphic, this can be:
		 * -Normal data
		 * -Growth in percentage, calculate from normal data
		 */
		String option = request.getParameter("grafico");
		// Never be null
		assert(option == null);
		// Get description id by parameter
		int idDescription = Integer.valueOf(request.getParameter("description"));
		// Instantiate Description with idDescription
		Description description = new Description(idDescription);
		// Get setor id by parameter
		int idSetor = Integer.valueOf(request.getParameter("section"));
		// Instantiate Section with idSector
		Section section = new Section(idSetor);
		// Get state id by parameter
		int idState = Integer.valueOf(request.getParameter("state"));
		// Instantiate State with idState
		State state = new State(idState);
		// Two choice of custom graphic
		int initialYear = Integer.valueOf(request.getParameter("initialYear"));
		int finalYear = Integer.valueOf(request.getParameter("finalYear"));
		// years don't be equals
		if(initialYear == finalYear){
			throw new RuntimeException("Years don't be equals");
		}else{
			// Continue
		}
		// DAO used to get frames
		FrameDAO frameDAO = new FrameDAO();
		frames = frameDAO.getFramesList(initialYear, finalYear, state, section, description);
		// Get session
		HttpSession session = request.getSession();
		// Select type of graphic from option
		if(option.equalsIgnoreCase("geral")) {
			session.setAttribute("valores", getValues(frames));
		} else if(option.equalsIgnoreCase("do crescimento")) {
			session.setAttribute("valores", listGrowth(frames));
		}
		// Set all atributes to plot graphic
		session.setAttribute("anos", listYears(frames));
		session.setAttribute("tamanho", frames.size());
		session.setAttribute("titulo", description.getContent());
		session.setAttribute("secao", section.getNome());
		session.setAttribute("estado", state.getNome());
		session.setAttribute("grafico", option);
		// Redirect to grafico.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafico.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * List all values in frames
	 * 
	 * @return {@link List}	of Float that contain values extracted from frames
	 */
	public List<Float> getValues(List<Frame> frames) {
		assert(frames == null);
		List<Float> values = new ArrayList<Float>();
		for( Frame frame: frames) {
			values.add(frame.getValue());
		}
		return values;
	}
	
	/**
	 * List values of frames in percentage of growth anual
	 * 
	 * @param frames is normal data listed by year
	 * @return an {@link List} of Floats, with values in percentage
	 */
	private List<Float> listGrowth(List<Frame> frames) {
		// That contains values calculated
		List<Float> values = new ArrayList<Float>();
		// Each iteration of repetition bellow set this variables
		float initValue = frames.get(0).getValue();;
		float finalValue = initValue;
		// Runs list
		for(int i = 1; i < frames.size(); i++) {
			initValue = finalValue;
			finalValue = frames.get(i).getValue();
			values.add(calculateGrowth(finalValue, initValue));
		}
		return values;
	}
	
	/**
	 * List of years intervals of frames
	 * 
	 * @return {@link List} of {@link String} representing years
	 */
	private List<String> listYears(List<Frame> frames) {
		List<String> years = new ArrayList<String>();
		for( Frame frame: frames){
			years.add(String.valueOf(frame.getYear()));
		}
		return years;
	}
	
	/**
	 * Calculate value of growth in percentage anual
	 * 
	 * @param finalValue first value
	 * @param initValue  end value
	 * @return float with calculate growth
	 */
	private float calculateGrowth(float initValue, float finalValue){
		float growth = ((finalValue/initValue)-1) * FACTOR_PERCENTAGE;
		return growth;
	}	
	/**
	 * List all models in an table
	 * 
	 * @param typeOfModel type that will be listed
	 * 
	 * @return list found
	 */
	private List<Object> listModel(EnumTable typeOfModel) {
		GenericModelDAO DAO = new GenericModelDAO(typeOfModel);
		List<Object> list = DAO.listAll();
		return list;
	}
}
