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
        gerarExemplos();
    }

	//Inserindo exemplos
    public void gerarExemplos(){
    	Quadro q1 = new Quadro();
    	Quadro q2 = new Quadro();
    	Quadro q3 = new Quadro();
    	Quadro q4 = new Quadro();
    	Quadro q5 = new Quadro();
    	q1.setAno(2007);
    	q2.setAno(2008);
    	q3.setAno(2009);
    	q4.setAno(2010);	
    	q5.setAno(2011);
    	q1.setSecao(new Secao("Agricultura, pecuária, produção florestal, pesca e aquicultura"));
    	q2.setSecao(new Secao("Agricultura, pecuária, produção florestal, pesca e aquicultura"));
    	q3.setSecao(new Secao("Agricultura, pecuária, produção florestal, pesca e aquicultura"));
    	q4.setSecao(new Secao("Agricultura, pecuária, produção florestal, pesca e aquicultura"));
    	q5.setSecao(new Secao("Agricultura, pecuária, produção florestal, pesca e aquicultura"));
    	q1.setValor(36911);
    	q2.setValor(51990);
    	q3.setValor(84861);
    	q4.setValor(97355);
    	q5.setValor(100414);
    	q1.setEstado(new Estado());
    	quadros.add(q1);
    	quadros.add(q2);
    	quadros.add(q3);
    	quadros.add(q4);
    	quadros.add(q5);
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
	
	}
}
