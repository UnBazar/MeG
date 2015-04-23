package org.meg.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.meg.dao.UtilDAO;

/**
 * Record visits in an main action, the follow paths are counted:
 * <ul>
 * 	<li>/ranking</li>
 * 	<li>/compara</li>
 *  <li>/projecao</li>
 *  <li>/grafico</li>
 * </ul>
 */
@WebFilter("/*")
public class FilterHistory implements Filter {
	// All action mappings that need counted in historic
	private final String[] ACTIONS = {"/ranking","/compara", "/projecao", "/grafico"};

	/**
	 * Method filter all requests, register visits only for specific actions
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		// Converts ServletRequest in HttpServletRequest to use getRequestURI
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// DAO used to register visit 
		UtilDAO utilDAO = new UtilDAO();
		/*
		 *  All paths loaded by request action, include files and static path of
		 *  html, css and javascript
		 */
		String path = httpServletRequest.getRequestURI();
		// See if relevant action to be registered was called
		for(String action : ACTIONS){
			if(path.contains(action)){
				utilDAO.addHistory(action.substring(1));
			}
		}
		// Continue with request
		chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
