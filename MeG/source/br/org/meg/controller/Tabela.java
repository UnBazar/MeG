package br.org.meg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Descricao;
import br.org.meg.model.Quadro;
import br.org.meg.model.Secao;

public class Tabela implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) {
		int ano = Integer.parseInt(request.getParameter("ano"));
		int setor_id = Integer.parseInt(request.getParameter("setor"));
		
		QuadroDAO dao = new QuadroDAO();
		Secao secao = new Secao();
		Descricao descricao = new Descricao();
		secao.setId(setor_id);
		descricao.setNome("Salário médio mensal (Salários mínimos)");
		List<Quadro> lista = dao.obterLista(ano, secao, descricao);
		this.selectionSort(lista);
		this.alterarSalario(lista);
		request.setAttribute("lista", lista);
		request.setAttribute("ano", ano);
		return "tabela.jsp";
	}
	
	private void selectionSort(List<Quadro> lista) {
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
	
	private void troca(List<Quadro> lista, int index1, int index2) {
		Quadro buffer = lista.get(index1);
		lista.set(index1, lista.get(index2));
		lista.set(index2, buffer);
	}
	
	private void alterarSalario(List<Quadro> lista) {
		int tamanho = lista.size();
		float valor;
		for (int i = 0; i < tamanho; i++) {
			valor = lista.get(i).getValor();
			lista.get(i).setValor(823.0f * valor);
		}
	}
}

