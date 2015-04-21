package org.meg.controller;

import java.io.IOException;
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
		int ano = Integer.parseInt(request.getParameter("ano"));
		int setor_id = Integer.parseInt(request.getParameter("setor"));
		int descricao_id = Integer.parseInt(request.getParameter("descricao"));
		FrameDAO dao = new FrameDAO();
		Section secao = new Section();
		Description descricao = new Description();
		descricao.setId(descricao_id);
		secao.setId(setor_id);
		List<Frame> lista = dao.obterLista(ano, secao, descricao);
		this.ordenacaoCrescente(lista);
		if(descricao.getId() == 5){
			this.alterarSalario(lista, ano);
		}
		request.setAttribute("lista", lista);
		request.setAttribute("ano", ano);
		request.setAttribute("setor", secao.getNome());
		request.setAttribute("descricao", descricao);
		request.getRequestDispatcher("tabela.jsp").forward(request, response);
	}
	
	/**
	 * Método responsável por ordenar a lista de salário médio em ordem crescente
	 * @param lista de salários médios
	 */
	public void ordenacaoCrescente(List<Frame> lista) {
		int tamanho = lista.size();
		int maiorElemento;
		for (int i = 0; i < tamanho - 1; i++) {
			maiorElemento = i;
			for (int j = i + 1; j < lista.size(); j++) {
				if (lista.get(maiorElemento).getValue() < lista.get(j).getValue()) {
					maiorElemento = j;
				}
			}
			troca(lista, i, maiorElemento);
		}
	}
	
	/**
	 * Realiza um swap entre dois elementos da lista
	 * @param lista que possui os elementos que serão trocados
	 * @param index1 índice do primeiro elemento 
	 * @param index2 índice do segundo elemento
	 */
	private void troca(List<Frame> lista, int index1, int index2) {
		Frame buffer = lista.get(index1);
		lista.set(index1, lista.get(index2));
		lista.set(index2, buffer);
	}
	
	/**
	 * Converte os salários médios da lista de número de salários mínimos para reais
	 * @param lista de salários médios
	 * @param ano
	 */
	private void alterarSalario(List<Frame> lista, int ano) {
		int tamanho = lista.size();
		float numeroDeSalarios;
		float salarioMinimo = getSalarioMinimo(ano);
		for (int i = 0; i < tamanho; i++) {
			numeroDeSalarios = lista.get(i).getValue();
			lista.get(i).setValue(salarioMinimo * numeroDeSalarios);
		}
	}
	
	/**
	 * Seleciona o valor do salário mínimo de acordo com o ano especificado
	 * @param ano
	 * @return o valor do salário mínimo no ano especificado
	 */
	private float getSalarioMinimo(int ano) {
		UtilDAO dao = new UtilDAO();
		float salarioMinimo = dao.getSalarioMinimo(ano);
		return salarioMinimo;
	}
}