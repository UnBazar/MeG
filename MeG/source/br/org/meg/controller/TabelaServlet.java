package br.org.meg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Descricao;
import br.org.meg.model.Secao;

public class TabelaServlet implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) {
		int ano = Integer.parseInt(request.getParameter("ano"));
		String setor = request.getParameter("setor");
		
		QuadroDAO dao = new QuadroDAO();
		Secao secao = new Secao();
		Descricao descricao = new Descricao();
		secao.setNome(setor);
		descricao.setNome("Salário médio mensal (Salários mínimos)");
		dao.obterLista(ano, secao, descricao);
		return "tabela.jsp";
	}
	
	

}
