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

import org.meg.dao.FrameDAO;
import org.meg.model.Description;
import org.meg.model.Frame;
import org.meg.model.Section;

@WebServlet("/crescimento")
public class GrowthRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Generates two new lists of frames containing the growth of all states between
	 * two years sent in the request, then renders the page that displays the table with
	 * all state's growth.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// maps user's request from String format to its corresponding values in Integer format
		HashMap<String, Integer> hash = getHash(request);
		List<Frame> firstList;
		List<Frame> secondList;
		List<Frame> growthList;
		FrameDAO dao = new FrameDAO();
		Section section = new Section();
		Description description = new Description();
		section.setId(hash.get("setor"));
		description.setId(hash.get("descricao"));
		
		// get list of all frames of initial and final year
		firstList = dao.getFramesList(hash.get("anoInicial"), section, description);
		secondList = dao.getFramesList(hash.get("anoFinal"), section, description);
		growthList = getGrowthList(firstList, secondList);
		
		RankingServlet ranking = new RankingServlet();
		ranking.selectionSort(growthList);
		
		request.setAttribute("listaCrescimento", growthList);
		request.setAttribute("anoInicial", hash.get("anoInicial"));
		request.setAttribute("anoFinal", hash.get("anoFinal"));
		request.setAttribute("descricao", description);
		request.setAttribute("setor", section.getNome());
		request.getRequestDispatcher("tabela-crescimento.jsp").forward(request, response);
	}
	
	/**
	 * This method calculates the average growth of a frame in each state 
	 * for a initial and a final year. It accepts as argument two lists of
	 * frames, which contains all state frames for the initial and final year, 
	 * respectively.
	 * @param firstList
	 * @param secondList
	 * @return a list of frames containing the average growth for each state
	 */
	private ArrayList<Frame> getGrowthList(List<Frame> firstList, List<Frame> secondList){
		ArrayList<Frame> growthList = new ArrayList<Frame>();
		Frame frame;
		float growth = 0.0f;
		final int numberOfStates = 27;
		for(int i = 0; i < numberOfStates; i++){
			growth = getAverageGrowth(firstList.get(i).getValue(), secondList.get(i).getValue());
			// takes advantage of existing frame object and overwrites value with the corresponding growth
			frame = firstList.get(i);
			frame.setValue(growth);
			growthList.add(frame);
		}
		return growthList;
	}

	/**
	 * Calculates the average growth of a frame based on its initial
	 * and final value in a period of time
	 * @param finalValue
	 * @param initialValue
	 * @return the average growth of a frame in percentage
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
		String[] attributesFrame = {"anoInicial", "anoFinal", "setor", "descricao"};
		
		for(String iterator : attributesFrame) {
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
	}
	
}
