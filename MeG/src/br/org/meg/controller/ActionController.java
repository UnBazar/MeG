package org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * Experiments of Mr. Pedro de Lyra
 */
public interface ActionController {
	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
