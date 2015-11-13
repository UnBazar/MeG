package org.meg.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.meg.dao.EnumTable;
import org.meg.dao.FrameDAO;
import org.meg.dao.GenericModelDAO;
import org.meg.dao.UtilDAO;
import org.meg.model.Description;
import org.meg.model.Frame;
import org.meg.model.Section;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("Ranking");
	
	private static final long serialVersionUID = 1L;
	
	private final String TABLE_VIEW = "table.jsp";
	private final String GENERATE_TABLE_VIEW = "generate-table.jsp";

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
		// Redirect to an form
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(GENERATE_TABLE_VIEW);
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Generate an table of ranking
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		final int minimumWageId = 5;
		// Maps user's request from String format to its corresponding values in Integer format
		HashMap<String, Integer> requestParameters = getHash(request);
		List<Frame> list;
		FrameDAO dao = new FrameDAO();
		Section section = new Section();
		Description description = new Description();
		
		description.setId(requestParameters.get(EnumAttribute.DESCRIPTION.toString()));
		section.setId(requestParameters.get(EnumAttribute.SECTION.toString()));
		int year = requestParameters.get(EnumAttribute.YEAR.toString());
		
		list = dao.getFramesList(year, section, description);
		selectionSort(list);
		
		// Identifies if user requested to display a ranking of the average salaries
		if(description.getId() == minimumWageId){
			setSalary(list, requestParameters.get("year"));
		}
		
		request.setAttribute("lista", list);
		request.setAttribute("ano", requestParameters.get("year"));
		request.setAttribute("setor", section.getName());
		request.setAttribute("descricao", description);
		request.getRequestDispatcher(TABLE_VIEW).forward(request, response);
		logger.info("Ranking created.");
		
	}
	
	/**
	 * Order the frame's list of average salary in descending order.
	 * @param frames
	 */
	public void selectionSort(List<Frame> frames) {
		int listSize = frames.size();
		int biggestElement = 0;
		for (int i = 0; i < listSize - 1; i++) {
			biggestElement = i;
			for (int j = i + 1; j < frames.size(); j++) {
				if (frames.get(biggestElement).getValue() < frames.get(j).getValue()) {
					biggestElement = j;
				}
			}
			swap(frames, i, biggestElement);
		}
	}
	
	/**
	 * Swaps two itens from the list sent as argument based on the indexes sent as argument.
	 * @param frames
	 * @param firstElementIndex
	 * @param secondElementIndex
	 */
	private void swap(List<Frame> frames, int firstElementIndex, int secondElementIndex) {
		Frame buffer = frames.get(firstElementIndex);
		frames.set(firstElementIndex, frames.get(secondElementIndex));
		frames.set(secondElementIndex, buffer);
	}
	
	/**
	 * This method converts the data from number of minimum wage per month into reais
	 * based on the minimum wage's value in the year sent as argument.
	 * @param frames
	 * @param year
	 */
	private void setSalary(List<Frame> frames, int year) {
		float minimumWageFactor = 0.0f;
		float minimumWage = getMinimumWage(year);
		for(Frame iterator : frames) {
			minimumWageFactor = iterator.getValue();
			iterator.setValue(minimumWage * minimumWageFactor);
		}
	}
	
	/**
	 * Selects the value of minimum wage according to a specific year
	 * @param year
	 * @return the minimum wage in the specified year
	 */
	private float getMinimumWage(int year) {
		UtilDAO dao = new UtilDAO();
		float minimumWage = dao.getMinimumWage(year);
		return minimumWage;
	}
	
	/**
	 * Creates a hash that maps the attributes name sent in the request
	 * into its respective values in Integer format.
	 * 
	 * @return a hash mapping String keys into Integer values.
	 */
	private HashMap<String, Integer> getHash(HttpServletRequest request) {
		HashMap<String, Integer> hash = new HashMap<>();
		String[] attributesFrame = {EnumAttribute.YEAR.toString(), 
				EnumAttribute.SECTION.toString(), 
				EnumAttribute.DESCRIPTION.toString()};
		
		for(String iterator : attributesFrame) {
			logger.info("Request parameter received -> key: " + iterator +
					"value: " + hash.get(iterator));
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
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