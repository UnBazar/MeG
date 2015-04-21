package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
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
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int anoInicial = Integer.parseInt(request.getParameter("anoInicial"));
		int anoFinal = Integer.parseInt(request.getParameter("anoFinal"));
		int setor_id = Integer.parseInt(request.getParameter("setor"));
		int descricao_id = Integer.parseInt(request.getParameter("descricao"));
		FrameDAO dao = new FrameDAO();
		Section secao = new Section();
		Description descricao = new Description();
		secao.setId(setor_id);
		descricao.setId(descricao_id);
		List<Frame> listaInicial = dao.obterLista(anoInicial, secao, descricao);
		List<Frame> listaFinal = dao.obterLista(anoFinal, secao, descricao);
		List<Frame> listaCrescimento = obterListaCrescimento(listaInicial,listaFinal);
		RankingServlet rnk = new RankingServlet();
		rnk.ordenacaoCrescente(listaCrescimento);
		request.setAttribute("listaCrescimento", listaCrescimento);
		request.setAttribute("anoInicial", anoInicial);
		request.setAttribute("anoFinal", anoFinal);
		request.setAttribute("descricao", descricao);
		request.setAttribute("setor", secao.getNome());
		request.getRequestDispatcher("tabela-crescimento.jsp").forward(request, response);
	}
	/**
	 * Método que pega Quadros de crescimento. Substituindo os valores por porcentagens
	 * @param listaInicial
	 * @param listaFinal
	 * @return uma lista de quadros em que o valor é o crescimento
	 */
	private ArrayList<Frame> obterListaCrescimento(List<Frame> listaInicial, List<Frame> listaFinal){
		ArrayList<Frame> listaCrescimento = new ArrayList<Frame>();
		for(int i = 0; i < listaInicial.size(); i++){
				float crescimento = calculaCrescimento(listaFinal.get(i).getValue(),listaInicial.get(i).getValue());
				/*
				 * Esse quadro podia ser listaInicial, o importante é pegar
				 * os outros atributos como Secao e Descricao
				 */
				Frame quadro = listaFinal.get(i);
				quadro.setValue(crescimento);
				listaCrescimento.add(quadro);
		}
		return listaCrescimento;
	}

	/**
	 * Método que calcula o crescimento em porcentagem
	 * @param valorFinal
	 * @param valorInicial
	 * @return o crescimento percentual de dois valores
	 */
	private float calculaCrescimento(float valorFinal, float valorInicial){
		float resultado = 0;
		resultado = ((valorFinal / valorInicial)-1) * 100;
		return resultado;
	}
	
	/**
	 * Realiza uma troca entre dois elementos da lista
	 * @param lista que possui os elementos que serão trocados
	 * @param index1 índice do primeiro elemento 
	 * @param index2 índice do segundo elemento
	 */
	private void troca(List<Frame> lista, int index1, int index2) {
		Frame buffer = lista.get(index1);
		lista.set(index1, lista.get(index2));
		lista.set(index2, buffer);
	}
}
