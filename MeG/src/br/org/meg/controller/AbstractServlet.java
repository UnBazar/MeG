package org.meg.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavaServlet
 */
public class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String controllerName = request.getParameter("controller");
		String controllerFullName = "org.meg.controller" + controllerName;
		
		try {
			Class<?> controllerClass = Class.forName(controllerFullName);
			ActionController controller = (ActionController) controllerClass.newInstance();
			
			// JSP path name that is supposed to be sent as a response to the user
			String responsePage = controller.execute(request, response);
			
			request.getRequestDispatcher(responsePage).forward(request, response);
		} catch(Exception e) {
			// TODO evolute exception handler
			e.printStackTrace();
		}
	}

}
