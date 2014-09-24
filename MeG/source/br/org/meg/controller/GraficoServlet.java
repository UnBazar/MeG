package br.org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.model.Quadro;

/**
 * Servlet implementation class Login
 */
@WebServlet("/grafico")
public class GraficoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private List<Quadro> quadros = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraficoServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Float> valores = new ArrayList<Float>();
		for( Quadro q: quadros){
			valores.add(q.getValor());
		}
		request.getSession().setAttribute("valores", valores);
		RequestDispatcher rd = request.getRequestDispatcher("/grafico.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
