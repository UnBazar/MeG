package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.QuadroDAO;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

@WebServlet ("/compara")
public class ComparaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ComparaServlet(){
		super();
	}
	/**
	 * Método que faz comparacao de dados entre dois estados
	 */
	@SuppressWarnings("unchecked")
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
		String opcao = (String) request.getSession().getAttribute("grafico");
		QuadroDAO dao = new QuadroDAO();
		quadros = dao.obterLista(anoInicial, anoFinal, estado, secao, descricao);
		
		GraficoServlet grafico = new GraficoServlet();
		if(opcao.equalsIgnoreCase("geral")){
			request.getSession().setAttribute("valores2", grafico.listarValores(quadros));
		}
		else if(opcao.equalsIgnoreCase("do crescimento")){
			request.getSession().setAttribute("valores2", listarCrescimento(quadros));
		}
		request.getSession().setAttribute("estado2", estado.getNome());
		RequestDispatcher rd = request.getRequestDispatcher("compara.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Lista os valores dos quadros contidos na lista global 'quadros' mas fazendo o calculo do crescimento anual
	 * @param quadros
	 * @return uma lista de floats contendo os valores de crescimento
	 */
	private List<Float> listarCrescimento(List<Quadro> quadros){
		List<Float> valores = new ArrayList<Float>();
		float valorInicial = 0,valorFinal = 0;
		for(int i = 0; i < quadros.size(); i++){
			if(i == 0){
				valorInicial= quadros.get(i).getValor();
				valorFinal = valorInicial;
			}
			else{
				valorInicial = valorFinal;
				valorFinal = quadros.get(i).getValor();
			}
			valores.add(calculaCrescimento(valorFinal,valorInicial));
		}
		
		return valores;
	}
	/**
	 * Calcula o valor do crescimento percentual anual
	 * @param valorFinal
	 * @param valorInicial
	 * @return float com o valor do crescimento
	 */
	private float calculaCrescimento(float valorFinal, float valorInicial){
		float crescimento = ((valorFinal/valorInicial)-1)* 100;
		return crescimento;
	}
}
