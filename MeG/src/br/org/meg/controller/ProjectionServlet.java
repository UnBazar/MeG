package org.meg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.FrameDAO;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

/**
 * Servlet implementation class Login
 */
@WebServlet("/projecao")
public class ProjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int numeroDeAcertos = 0;
	
	public static int quaseAcertos = 0;

	public static BigDecimal b = new BigDecimal(0.0f);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * Metodo que realiza o busca de dados para plotar grafico
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int initialProjectionYear = 2012, initialYear = 2006, finalYear = 2012;
		List<Frame> frames = new ArrayList<>();
		
		// create a hash that maps attributes of a frame into ids
		HashMap<String, Integer> idsHash = getHash(request);
		
		FrameDAO dao = new FrameDAO();
		
		Description description = new Description(idsHash.get("descricao"));
		Section section = new Section(idsHash.get("secao"));
		State state = new State(idsHash.get("estado"));
		frames = dao.getFramesList(initialYear, finalYear, state, section, description);
		
		float dadoReal = getLastFrame(frames).getValue();
		frames.remove(frames.size() - 1);
		float projecao = criarProjecao(frames);
		if (Math.abs(projecao) > 0.0) b = b.add(new BigDecimal(Math.abs(dadoReal - projecao)).divide(new BigDecimal(Math.abs(projecao)), 4, RoundingMode.UP));
		if ((dadoReal - projecao) == 0) numeroDeAcertos++;
		if (Math.abs(dadoReal - projecao) <= Math.abs(dadoReal / 10.0)) quaseAcertos++;
		
		frames.remove(frames.size() - 1);
		Frame quadro = new Frame();
		quadro.setYear(2012);
		quadro.setDescription(frames.get(frames.size() - 1).getDescription());
		quadro.setState(frames.get(frames.size() - 1).getState());
		quadro.setSection(frames.get(frames.size() - 1).getSection());
		quadro.setValue(dadoReal);
		frames.add(quadro);
		
		for (int i = initialProjectionYear; i < 212; i++) {
			criarProjecao(frames);
		}
		
		request.getSession().setAttribute("valores", getValuesList(frames));
		request.getSession().setAttribute("anos", getYearsList(frames));
		request.getSession().setAttribute("tamanho", frames.size());
		request.getSession().setAttribute("titulo", description.getNome());
		request.getSession().setAttribute("secao", section.getNome());
		request.getSession().setAttribute("estado", state.getNome());
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("projecao.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Each frame is associated with a value. This method return a list of values
	 * corresponding to the list of frames sent as argument.
	 * 
	 * @return a list of values corresponding to the frames sent as argument
	 */
	private List<Float> getValuesList(List<Frame> frames) {
		List<Float> valuesList = new ArrayList<Float>();
		for (Frame q : frames) {
			valuesList.add(q.getValue());
		}
		return valuesList;
	}

	/**
	 * Each frame is associated with a year. This method return a list
	 * of years (String type) corresponding to the list of frames sent as argument.
	 * 
	 * @return a list of years corresponding to the frames sent as argument.
	 */
	private List<String> getYearsList(List<Frame> frames) {
		List<String> yearsList = new ArrayList<String>();
		for (Frame frame : frames) {
			yearsList.add(String.valueOf(frame.getYear()));
		}
		return yearsList;
	}

	private float criarProjecao(List<Frame> quadros) {
		float mediasMoveis[] = new float[quadros.size() + 1];
		float projecoes[] = new float[9];
		float maiorProjecao;
		int topo = 0;
		Frame projecao = new Frame();
		projecao.setYear(quadros.get(quadros.size() - 1).getYear() + 1);
		projecao.setDescription(quadros.get(quadros.size() - 1).getDescription());
		projecao.setState(quadros.get(quadros.size() - 1).getState());
		projecao.setSection(quadros.get(quadros.size() - 1).getSection());
		if (!this.verificarFuncaoCrescente(quadros) && !this.verificarFuncaoDecrescente(quadros)) {
			for (float alfa = 0.1f; alfa < 1.0f; alfa += 0.1) {
				mediasMoveis[0] = quadros.get(0).getValue();
				for (int i = 0; i < quadros.size(); i++) {
					mediasMoveis[i + 1] = mediasMoveis[i] 
							+ alfa * (quadros.get(i).getValue() - mediasMoveis[i]);
				}
				projecoes[topo++] = mediasMoveis[quadros.size()];
			}
			maiorProjecao = projecoes[projecoes.length - 1];
			projecao.setValue((int) Math.ceil(maiorProjecao));
		} else {
			projecao.setValue((int) Math.ceil(quadros.get(quadros.size() - 1).getValue() 
					+ this.mediazinhaCrescimento(quadros)));
		}
		quadros.add(projecao);
		return quadros.get(quadros.size() - 1).getValue();
	}
	
	private boolean verificarFuncaoCrescente(List<Frame> quadros) {
		for (int i = quadros.size() - 2; i < quadros.size(); i++) {
			if (quadros.get(i).getValue() < quadros.get(i - 1).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean verificarFuncaoDecrescente(List<Frame> quadros) {
		for (int i = quadros.size() - 2; i < quadros.size(); i++) {
			if (quadros.get(i).getValue() > quadros.get(i - 1).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	private float mediazinhaCrescimento(List<Frame> quadros) {
		float media = 0.0f;
		for (int i = 1; i < quadros.size(); i++) {
			media += quadros.get(i).getValue() - quadros.get(i - 1).getValue();
		}
		media = 0.3f * media/quadros.size();
		return media;
	}
	
	private HashMap<String, Integer> getHash(HttpServletRequest request) {
		HashMap<String, Integer> hash = new HashMap<>();
		String[] attributesFrame = {"descricao", "setor", "estado", "anoFinal"};
		
		for(String iterator : attributesFrame) {
			hash.put(iterator, Integer.valueOf(request.getParameter(iterator)));
		}
		
		return hash;
	}
	
	private Frame getLastFrame(List<Frame> framesList) {
		Frame lastElement = framesList.get(framesList.size() - 1);
		return lastElement;
	}
}
