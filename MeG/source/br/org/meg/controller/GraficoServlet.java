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
import br.org.meg.model.Descricao;
import br.org.meg.model.Estado;
import br.org.meg.model.Quadro;
import br.org.meg.model.Secao;

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
	
	}
	
	/**
	 * Metodo que realiza o busca de dados para plotar grafico
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int descricao_id = 0;
		int setor_id = 0;
		int estado_id = 0;
		int anoInicial = 0;
		int anoFinal = 0;
		descricao_id = Integer.valueOf(request.getParameter("descricao"));
		setor_id = Integer.valueOf(request.getParameter("setor"));
		estado_id= Integer.valueOf(request.getParameter("estado"));
		anoInicial = Integer.valueOf(request.getParameter("anoInicial"));
		anoFinal = Integer.valueOf(request.getParameter("anoFinal"));
		QuadroDAO dao = new QuadroDAO();
		Descricao descricao = new Descricao(descricao_id);
		Secao secao = new Secao(setor_id);
		Estado estado = new Estado (estado_id);
		quadros = dao.obterLista(anoInicial, anoFinal, estado, secao, descricao);
		request.getSession().setAttribute("valores", listarValores());
		request.getSession().setAttribute("anos", listarAnos());
		request.getSession().setAttribute("tamanho", quadros.size());
		request.getSession().setAttribute("titulo", descricao.getNome());
		request.getSession().setAttribute("secao", secao.getNome());
		request.getSession().setAttribute("estado", estado.getNome());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafico.jsp");
		requestDispatcher.forward(request, response);
	}
	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return	uma lista de floats contendo os valores
	 */
	private List<Float> listarValores(){
		List<Float> valores = new ArrayList<Float>();
		for( Quadro q: quadros){
			valores.add(q.getValor());
		}
		return valores;
	}
	
	/**
	 * Lista os anos dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de Strings contendo os anos
	 */
	private List<String> listarAnos(){
		List<String> anos = new ArrayList<String>();
		for( Quadro q: quadros){
			anos.add(String.valueOf(q.getAno()));
		}
		return anos;
	}
}