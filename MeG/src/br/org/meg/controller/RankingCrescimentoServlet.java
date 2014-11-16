package org.meg.controller;

import java.io.IOException;
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
		int secao_id = Integer.parseInt(request.getParameter("secao"));
		int descricao_id = Integer.parseInt(request.getParameter("descricao"));
		QuadroDAO dao = new QuadroDAO();
		Secao secao = new Secao();
		Descricao descricao = new Descricao();
		secao.setId(secao_id);
		descricao.setId(descricao_id);
		List<Quadro> listaInicial = dao.obterLista(anoInicial, secao, descricao);
	}

}
