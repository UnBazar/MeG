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

@WebServlet ("/compara")
public class ComparaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ComparaServlet(){
		super();
	}
	/**
	 * Método que faz comparacao de dados entre dois estados
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Quadro> quadros = new ArrayList<>();
		Estado estado = new Estado();
		Secao secao = new Secao((String)request.getSession().getAttribute("secao"));
		Descricao descricao = new Descricao();
		descricao.setNome((String)request.getSession().getAttribute("titulo"));
		estado.setId( Integer.parseInt(request.getParameter("estado")));
		List<String> listadeAnos = (List<String>) request.getSession().getAttribute("anos");
		int anoInicial = Integer.parseInt(listadeAnos.get(0));
		int anoFinal = Integer.parseInt(listadeAnos.get(listadeAnos.size()-1));
		QuadroDAO dao = new QuadroDAO();
		quadros = dao.obterLista(anoInicial, anoFinal, estado, secao, descricao);
		request.getSession().setAttribute("valores2", listarValores(quadros));
		request.getSession().setAttribute("estado2", estado.getNome());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("compara.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return	uma lista de Strings contendo os valores
	 */
	private List<Float> listarValores(List<Quadro> quadros){
		List<Float> valores = new ArrayList<Float>();
		for( Quadro q: quadros){
			valores.add(q.getValor());
		}
		return valores;
	}
}
