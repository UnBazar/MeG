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

import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Estado;
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
		request.getSession().setAttribute("tamanho", quadros.size());
		request.getSession().setAttribute("valores", listarValores());
		request.getSession().setAttribute("anos", listarAnos());
		request.getSession().setAttribute("titulo", quadros.get(0).getSecao().getNome());
		RequestDispatcher rd = request.getRequestDispatcher("/grafico.jsp");
		rd.forward(request, response);
	}
	
	private List<Float> listarValores(){
		List<Float> valores = new ArrayList<Float>();
		for( Quadro q: quadros){
			valores.add(q.getValor());
		}
		return valores;
	}
	
	private List<String> listarAnos(){
		List<String> anos = new ArrayList<String>();
		for( Quadro q: quadros){
			anos.add(String.valueOf(q.getAno()));
		}
		return anos;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int descricao_id = (int) request.getSession().getAttribute("descricao");
		int setor_id = (int) request.getSession().getAttribute("setor");
		int estado_id = (int) request.getSession().getAttribute("estado");
		QuadroDAO dao = new QuadroDAO();
	}
}