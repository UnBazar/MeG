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
 * Responsable for plot an custom graphic
 */
@WebServlet("/grafico")
public class GraphicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
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
		// Get description id by parameter
		int idDescription = Integer.valueOf(request.getParameter("descricao"));
		// Instantiate Description with idDescription
		Description description = new Description(idDescription);
		// Get setor id by parameter
		int idSetor = Integer.valueOf(request.getParameter("setor"));
		// Instantiate Section with idSector
		Section section = new Section(idSetor);
		// Get state id by parameter
		int idState = Integer.valueOf(request.getParameter("estado"));
		// Instantiate State with idState
		State state = new State(idState);
		// Two choice of custom graphic
		int initialYear = Integer.valueOf(request.getParameter("anoInicial"));
		int finalYear = Integer.valueOf(request.getParameter("anoFinal"));
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
		session.setAttribute("anos", listarAnos(frames));
		session.setAttribute("tamanho", frames.size());
		session.setAttribute("titulo", description.getNome());
		session.setAttribute("secao", section.getNome());
		session.setAttribute("estado", state.getNome());
		session.setAttribute("grafico", option);
		// Redirect to grafico.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafico.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return {@link List}	of Float that contain values extracted from frames
	 */
	public List<Float> getValues(List<Frame> quadros) {
		List<Float> valores = new ArrayList<Float>();
		for( Frame q: quadros) {
			valores.add(q.getValue());
		}
		return valores;
	}
	
	/**
	 * List values of frames in percentage of growth anual
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
	 * Lista os anos dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de Strings contendo os anos
	 */
	private List<String> listarAnos(List<Frame> quadros){
		List<String> anos = new ArrayList<String>();
		for( Frame q: quadros){
			anos.add(String.valueOf(q.getYear()));
		}
		return anos;
	}
	
	/**
	 * Calcula o valor do crescimento percentual anual
	 * @param valorFinal
	 * @param valorInicial
	 * @return float com o valor do crescimento
	 */
	private float calculateGrowth(float valorFinal, float valorInicial){
		float crescimento = ((valorFinal/valorInicial)-1)* 100;
		return crescimento;
	}
}
