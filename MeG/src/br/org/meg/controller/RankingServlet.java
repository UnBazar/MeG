package org.meg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.QuadroDAO;
import org.meg.dao.UtilDAO;
import org.meg.model.Descricao;
import org.meg.model.Quadro;
import org.meg.model.Secao;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int ano = Integer.parseInt(request.getParameter("ano"));
		int setor_id = Integer.parseInt(request.getParameter("setor"));
		int descricao_id = Integer.parseInt(request.getParameter("descricao"));
		QuadroDAO dao = new QuadroDAO();
		Secao secao = new Secao();
		Descricao descricao = new Descricao();
		descricao.setId(descricao_id);
		secao.setId(setor_id);
		List<Quadro> lista = dao.obterLista(ano, secao, descricao);
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
	public void ordenacaoCrescente(List<Quadro> lista) {
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
	 * Realiza um swap entre dois elementos da lista
	 * @param lista que possui os elementos que serão trocados
	 * @param index1 índice do primeiro elemento 
	 * @param index2 índice do segundo elemento
	 */
	private void troca(List<Quadro> lista, int index1, int index2) {
		Quadro buffer = lista.get(index1);
		lista.set(index1, lista.get(index2));
		lista.set(index2, buffer);
	}
	
	/**
	 * Converte os salários médios da lista de número de salários mínimos para reais
	 * @param lista de salários médios
	 * @param ano
	 */
	private void alterarSalario(List<Quadro> lista, int ano) {
		int tamanho = lista.size();
		float numeroDeSalarios;
		float salarioMinimo = getSalarioMinimo(ano);
		for (int i = 0; i < tamanho; i++) {
			numeroDeSalarios = lista.get(i).getValor();
			lista.get(i).setValor(salarioMinimo * numeroDeSalarios);
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