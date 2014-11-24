package org.meg.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.meg.dao.UtilDAO;

/**
 * Servlet Filter implementation class HistoricoFiltro
 */
public class HistoricoFiltro implements Filter {

	UtilDAO dao = new UtilDAO();

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		int idRanking = 1;
		int idCompara = 2;
		int idProjecao = 3;
		int idGrafico = 4;
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().contains("/ranking")) {
			dao.adicionaHistorico(idRanking);
		}
		else if (req.getRequestURI().contains("/compara")) {
			dao.adicionaHistorico(idCompara);
		}
		else if (req.getRequestURI().contains("/projecao")) {
			dao.adicionaHistorico(idProjecao);
		}
		else if (req.getRequestURI().contains("/grafico")) {
			dao.adicionaHistorico(idGrafico);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}