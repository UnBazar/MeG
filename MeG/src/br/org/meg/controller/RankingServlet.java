package org.meg.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.FrameDAO;
import org.meg.dao.UtilDAO;
import org.meg.model.Description;
import org.meg.model.Frame;
import org.meg.model.Section;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		final int minimumWageId = 5;
		// maps user's request from String format to its corresponding values in Integer format
		HashMap<String, Integer> hash = getHash(request);
		List<Frame> list;
		FrameDAO dao = new FrameDAO();
		Section section = new Section();
		Description description = new Description();
		
		description.setId(hash.get("descricao"));
		section.setId(hash.get("setor"));
		list = dao.getFramesList(hash.get("ano"), section, description);
		selectionSort(list);
		
		// identifies if user requested to display a ranking of the average salaries
		if(description.getId() == minimumWageId){
			setSalary(list, hash.get("ano"));
		}
		
		request.setAttribute("lista", list);
		request.setAttribute("ano", hash.get("ano"));
		request.setAttribute("setor", section.getNome());
		request.setAttribute("descricao", description);
		request.getRequestDispatcher("tabela.jsp").forward(request, response);
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
	 * Seleciona o valor do salário mínimo de acordo com o ano especificado
	 * @param ano
	 * @return o valor do salário mínimo no ano especificado
	 */
	private float getMinimumWage(int ano) {
		UtilDAO dao = new UtilDAO();
		float salarioMinimo = dao.getSalarioMinimo(ano);
		return salarioMinimo;
	}
	
	/**
	 * Creates a hash that maps the attributes name sent in the request
	 * into its respective values in Integer format.
	 * 
	 * @return a hash mapping String keys into Integer values.
	 */
	private HashMap<String, Integer> getHash(HttpServletRequest request) {
		HashMap<String, Integer> hash = new HashMap<>();
		String[] attributesFrame = {"ano", "setor", "descricao"};
		
		for(String iterator : attributesFrame) {
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
	}
	
}