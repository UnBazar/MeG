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

import org.meg.dao.QuadroDAO;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

@WebServlet("/compara")
public class CompareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CompareServlet(){
		super();
	}
	/**
	 * Compares growth rates between two different states
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Quadro> frames = new ArrayList<>();
		Estado state = new Estado();
		Secao section = new Secao((String) request.getSession().getAttribute("secao"));
		Descricao description = new Descricao();
		List<String> yearList = (List<String>) request.getSession().getAttribute("anos");
		int initialYear = Integer.parseInt(yearList.get(0));
		int finalYear = Integer.parseInt(yearList.get(yearList.size()-1));
		
		description.setNome((String) request.getSession().getAttribute("titulo"));
		state.setId(Integer.parseInt(request.getParameter("estado")));
		String option = (String) request.getSession().getAttribute("grafico");
		QuadroDAO dao = new QuadroDAO();
		frames = dao.obterLista(initialYear, finalYear, state, section, description);
		
		GraficoServlet graphic = new GraficoServlet();
		
		if(option.equalsIgnoreCase("geral")){
			request.getSession().setAttribute("valores2", graphic.listarValores(frames));
		}
		else if(option.equalsIgnoreCase("do crescimento")){
			request.getSession().setAttribute("valores2", listSectorGrowth(frames));
		}
		request.getSession().setAttribute("estado2", state.getNome());
		RequestDispatcher rd = request.getRequestDispatcher("compara.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * List the growth through the years of a market's sector
	 * @param frames
	 * @return a list storing sector's growth through years
	 */
	private List<Float> listSectorGrowth(List<Quadro> frames){
		List<Float> values = new ArrayList<Float>();
		float initialValue = 0, finalValue = 0;
		for(int i = 0; i < frames.size(); i++){
			if(i == 0) {
				initialValue = frames.get(i).getValor();
				finalValue = initialValue;
			} else {
				initialValue = finalValue;
				finalValue = frames.get(i).getValor();
			}
			values.add(calculateGrowth(initialValue, finalValue));
		}
		
		return values;
	}
	/**
	 * Calculates the annual growth of a particular sector of the market in percentage.
	 * @param initialValue
	 * @param finalValue
	 * @return growth's rate in percentage
	 */
	private float calculateGrowth(float initialValue, float finalValue) {
		/* calculates increase of values between the initial year and final year
		 *  and multiplies by 100 to convert to percentage notation
		 */
		float increase = ((finalValue / initialValue) - 1) * 100f;
		return increase;
	}
}

