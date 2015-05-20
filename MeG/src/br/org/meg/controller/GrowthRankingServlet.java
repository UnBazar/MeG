package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.meg.dao.FrameDAO;
import org.meg.model.Description;
import org.meg.model.Frame;
import org.meg.model.Section;

@WebServlet("/growth-servlet")
public class GrowthRankingServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("GrowthRanking");
	
	private final String GROWTH_TABLE_VIEW = "growth-table.jsp";
	private static final long serialVersionUID = 1L;

	/**
	 * Generates two new lists of frames containing the growth of all states between
	 * two years sent in the request, then renders the page that displays the table with
	 * all state's growth.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Maps user's request from String format to its corresponding values in Integer format.
		HashMap<String, Integer> hash = getHash(request);
		List<Frame> firstList;
		List<Frame> secondList;
		List<Frame> growthList;
		FrameDAO dao = new FrameDAO();
		Section section = new Section();
		Description description = new Description();
		section.setId(hash.get("sector"));
		description.setId(hash.get("description"));
		
		// get list of all frames of initial and final year
		firstList = dao.getFramesList(hash.get("initialYear"), section, description);
		secondList = dao.getFramesList(hash.get("finalYear"), section, description);
		growthList = getGrowthList(firstList, secondList);
		
		RankingServlet ranking = new RankingServlet();
		ranking.selectionSort(growthList);
		
		request.setAttribute("growthList", growthList);
		request.setAttribute("initialYear", hash.get("initialYear"));
		request.setAttribute("finalYear", hash.get("finalYear"));
		request.setAttribute("description", description);
		request.setAttribute("section", section.getNome());
		request.getRequestDispatcher(GROWTH_TABLE_VIEW).forward(request, response);
		logger.info("Created growth ranking.");
	}
	
	/**
	 * This method calculates the average growth of a frame in each state 
	 * for a initial and a final year. It accepts as argument two lists of
	 * frames, which contains all state frames for the initial and final year, 
	 * respectively.
	 * 
	 * @param firstList list including all the first values of a specific state.
	 * @param secondList list including all the last values of a specific state.
	 * 
	 * @return a list of frames containing the average growth for each state.
	 */
	private ArrayList<Frame> getGrowthList(List<Frame> firstList, List<Frame> secondList) {
		ArrayList<Frame> growthList = new ArrayList<Frame>();
		Frame frame;
		float growth = 0.0f;
		final int numberOfStates = 27;
		for(int i = 0; i < numberOfStates; i++){
			growth = getAverageGrowth(firstList.get(i).getValue(), secondList.get(i).getValue());
			// Takes advantage of existing frame object and overwrites value with the corresponding growth.
			frame = firstList.get(i);
			frame.setValue(growth);
			growthList.add(frame);
		}
		return growthList;
	}

	/**
	 * Calculates the average growth of a frame based on its initial
	 * and final value in a period of time.
	 * 
	 * @param finalValue the last value of the growth in a specific state.
	 * @param initialValue the first value of the growth in a specific state.
	 * 
	 * @return the average growth of a frame in percentage.
	 */
	private float getAverageGrowth(float initialValue, float finalValue) {
		float average = ((finalValue / initialValue) - 1) * 100;
		return average;
	}
	
	/**
	 * Creates a hash that maps the attributes name sent in the request
	 * into its respective values in Integer format.
	 * 
	 * @return a hash mapping String keys into Integer values.
	 */
	private HashMap<String, Integer> getHash(HttpServletRequest request) {
		HashMap<String, Integer> hash = new HashMap<>();
		String[] attributesFrame = {"initialYear", "finalYear", "section", "description"};
		
		for(String iterator : attributesFrame) {
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
	}
}
