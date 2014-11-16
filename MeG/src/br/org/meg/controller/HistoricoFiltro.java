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
	 * Default constructor.
	 */
	public HistoricoFiltro() {
		// TODO Auto-generated constructor stub
	}

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
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().contains("ranking")) {
			dao.adicionaHistorico(1);
		}

		else if (req.getRequestURI().contains("/compara")) {
			dao.adicionaHistorico(2);
		}

		else if (req.getRequestURI().contains("/projecao")) {
			dao.adicionaHistorico(3);
		}

		else if (req.getRequestURI().contains("/grafico")) {
			dao.adicionaHistorico(4);
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