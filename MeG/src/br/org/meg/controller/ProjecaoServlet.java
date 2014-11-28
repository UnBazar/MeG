package org.meg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

/**
 * Servlet implementation class Login
 */
@WebServlet("/projecao")
public class ProjecaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int numeroDeAcertos = 0;
	
	public static int quaseAcertos = 0;

	public static BigDecimal b = new BigDecimal(0.0f);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjecaoServlet() {
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
		List<Quadro> quadros = new ArrayList<>();
		int idDescricao = 0;
		int idSetor = 0;
		int idEstado = 0;
		int anoFinal = 0;
		idDescricao = Integer.valueOf(request.getParameter("descricao"));
		idSetor = Integer.valueOf(request.getParameter("setor"));
		idEstado = Integer.valueOf(request.getParameter("estado"));
		anoFinal = Integer.valueOf(request.getParameter("anoFinal"));
		QuadroDAO dao = new QuadroDAO();
		Descricao descricao = new Descricao(idDescricao);
		Secao secao = new Secao(idSetor);
		Estado estado = new Estado(idEstado);
		quadros = dao.obterLista(2006, 2012, estado, secao, descricao);
		float dadoReal = quadros.get(quadros.size() - 1).getValor();
		quadros.remove(quadros.size() - 1);
		float projecao = criarProjecao(quadros);
		if (Math.abs(projecao) > 0.0) b = b.add(new BigDecimal(Math.abs(dadoReal - projecao)).divide(new BigDecimal(Math.abs(projecao)), 4, RoundingMode.UP));
		if ((dadoReal - projecao) == 0) numeroDeAcertos++;
		if (Math.abs(dadoReal - projecao) <= Math.abs(dadoReal / 10.0)) quaseAcertos++;
		
		quadros.remove(quadros.size() - 1);
		Quadro quadro = new Quadro();
		quadro.setAno(2012);
		quadro.setDescricao(quadros.get(quadros.size() - 1).getDescricao());
		quadro.setEstado(quadros.get(quadros.size() - 1).getEstado());
		quadro.setSecao(quadros.get(quadros.size() - 1).getSecao());
		quadro.setValor(dadoReal);
		quadros.add(quadro);
		
		for (int i = 2012; i < anoFinal; i++) {
			criarProjecao(quadros);
		}
		
		request.getSession().setAttribute("valores", listarValores(quadros));
		request.getSession().setAttribute("anos", listarAnos(quadros));
		request.getSession().setAttribute("tamanho", quadros.size());
		request.getSession().setAttribute("titulo", descricao.getNome());
		request.getSession().setAttribute("secao", secao.getNome());
		request.getSession().setAttribute("estado", estado.getNome());
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("projecao.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de floats contendo os valores
	 */
	private List<Float> listarValores(List<Quadro> quadros) {
		List<Float> valores = new ArrayList<Float>();
		for (Quadro q : quadros) {
			valores.add(q.getValor());
		}
		return valores;
	}

	/**
	 * Lista os anos dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de Strings contendo os anos
	 */
	private List<String> listarAnos(List<Quadro> quadros) {
		List<String> anos = new ArrayList<String>();
		for (Quadro q : quadros) {
			anos.add(String.valueOf(q.getAno()));
		}
		return anos;
	}

	private float criarProjecao(List<Quadro> quadros) {
		float mediasMoveis[] = new float[quadros.size() + 1];
		float projecoes[] = new float[9];
		float maiorProjecao;
		int topo = 0;
		Quadro projecao = new Quadro();
		projecao.setAno(quadros.get(quadros.size() - 1).getAno() + 1);
		projecao.setDescricao(quadros.get(quadros.size() - 1).getDescricao());
		projecao.setEstado(quadros.get(quadros.size() - 1).getEstado());
		projecao.setSecao(quadros.get(quadros.size() - 1).getSecao());
		if (!this.verificarFuncaoCrescente(quadros) && !this.verificarFuncaoDecrescente(quadros)) {
			for (float alfa = 0.1f; alfa < 1.0f; alfa += 0.1) {
				mediasMoveis[0] = quadros.get(0).getValor();
				for (int i = 0; i < quadros.size(); i++) {
					mediasMoveis[i + 1] = mediasMoveis[i] 
							+ alfa * (quadros.get(i).getValor() - mediasMoveis[i]);
				}
				projecoes[topo++] = mediasMoveis[quadros.size()];
			}
			maiorProjecao = projecoes[projecoes.length - 1];
			projecao.setValor((int) Math.ceil(maiorProjecao));
		} else {
			projecao.setValor((int) Math.ceil(quadros.get(quadros.size() - 1).getValor() 
					+ this.mediazinhaCrescimento(quadros)));
		}
		quadros.add(projecao);
		return quadros.get(quadros.size() - 1).getValor();
	}
	
	private boolean verificarFuncaoCrescente(List<Quadro> quadros) {
		for (int i = quadros.size() - 2; i < quadros.size(); i++) {
			if (quadros.get(i).getValor() < quadros.get(i - 1).getValor()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean verificarFuncaoDecrescente(List<Quadro> quadros) {
		for (int i = quadros.size() - 2; i < quadros.size(); i++) {
			if (quadros.get(i).getValor() > quadros.get(i - 1).getValor()) {
				return false;
			}
		}
		return true;
	}
	
	private float mediazinhaCrescimento(List<Quadro> quadros) {
		float media = 0.0f;
		for (int i = 1; i < quadros.size(); i++) {
			media += quadros.get(i).getValor() - quadros.get(i - 1).getValor();
		}
		media = 0.3f * media/quadros.size();
		return media;
	}
	
}