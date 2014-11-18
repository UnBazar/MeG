package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.QuadroDAO;
import org.meg.model.Descricao;
import org.meg.model.Quadro;
import org.meg.model.Secao;

@WebServlet("/crescimento")
public class RankingCrescimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int anoInicial = Integer.parseInt(request.getParameter("anoInicial"));
		int anoFinal = Integer.parseInt(request.getParameter("anoFinal"));
		int setor_id = Integer.parseInt(request.getParameter("setor"));
		int descricao_id = Integer.parseInt(request.getParameter("descricao"));
		QuadroDAO dao = new QuadroDAO();
		Secao secao = new Secao();
		Descricao descricao = new Descricao();
		secao.setId(setor_id);
		descricao.setId(descricao_id);
		List<Quadro> listaInicial = dao.obterLista(anoInicial, secao, descricao);
		List<Quadro> listaFinal = dao.obterLista(anoFinal, secao, descricao);
		List<Quadro> listaCrescimento = obterListaCrescimento(listaInicial,listaFinal);
		this.ordenacaoCrescente(listaCrescimento);
		request.setAttribute("listaCrescimento", listaCrescimento);
		request.setAttribute("anoInicial", anoInicial);
		request.setAttribute("anoFinal", anoFinal);
		request.setAttribute("descricao", descricao);
		request.setAttribute("setor", secao.getNome());
		request.getRequestDispatcher("tabela-crescimento.jsp").forward(request, response);
	}
	/**
	 * Método que pega uma lista com o crescimento
	 * @param listaInicial
	 * @param listaFinal
	 * @return uma lista de quadros em que o valor é o crescimento
	 */
	private ArrayList<Quadro> obterListaCrescimento(List<Quadro> listaInicial, 
			List<Quadro> listaFinal){
		ArrayList<Quadro> listaCrescimento = new ArrayList<Quadro>();
		for(int i = 0; i < listaInicial.size(); i++){
			if(i < listaFinal.size()){
				float crescimento = calculaCrescimento(listaFinal.get(i).getValor(),listaInicial.get(i).getValor());
				Quadro lista = listaFinal.get(i);
				lista.setValor(crescimento);
				listaCrescimento.add(lista);
			}
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
	 * Método responsável por ordenar a lista de salário médio em ordem crescente
	 * @param lista de salários médios
	 */
	private void ordenacaoCrescente(List<Quadro> lista) {
		int tamanho = lista.size();
		int maiorElemento;
		for (int i = 0; i < tamanho - 1; i++) {
			maiorElemento = i;
			for (int j = i + 1; j < lista.size(); j++) {
				if (lista.get(maiorElemento).getValor() < lista.get(j).getValor()) {
					maiorElemento = j;
				}
			}
			troca(lista, i, maiorElemento);
		}
	}
	
	/**
	 * Realiza uma troca entre dois elementos da lista
	 * @param lista que possui os elementos que serão trocados
	 * @param index1 índice do primeiro elemento 
	 * @param index2 índice do segundo elemento
	 */
	private void troca(List<Quadro> lista, int index1, int index2) {
		Quadro buffer = lista.get(index1);
		lista.set(index1, lista.get(index2));
		lista.set(index2, buffer);
	}
}
