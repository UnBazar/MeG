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

import org.meg.dao.FrameDAO;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

/**
 * Servlet implementation class Login
 */
@WebServlet("/grafico")
public class GraphicServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphicServlet() {
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
		List<Frame> quadros = new ArrayList<>();
		int idDescricao = 0;
		int idSetor = 0;
		int idEstado = 0;
		int anoInicial = 0;
		int anoFinal = 0;
		String opcao;
		opcao = request.getParameter("grafico");
		idDescricao = Integer.valueOf(request.getParameter("descricao"));
		idSetor = Integer.valueOf(request.getParameter("setor"));
		idEstado= Integer.valueOf(request.getParameter("estado"));
		anoInicial = Integer.valueOf(request.getParameter("anoInicial"));
		anoFinal = Integer.valueOf(request.getParameter("anoFinal"));
		FrameDAO dao = new FrameDAO();
		Description descricao = new Description(idDescricao);
		Section secao = new Section(idSetor);
		State estado = new State (idEstado);
		quadros = dao.getFramesList(anoInicial, anoFinal, estado, secao, descricao);
		if(opcao.equalsIgnoreCase("geral")){
			request.getSession().setAttribute("valores", getValues(quadros));
		}else if(opcao.equalsIgnoreCase("do crescimento")){
			request.getSession().setAttribute("valores", listarCrescimento(quadros));
		}
		request.getSession().setAttribute("anos", listarAnos(quadros));
		request.getSession().setAttribute("tamanho", quadros.size());
		request.getSession().setAttribute("titulo", descricao.getNome());
		request.getSession().setAttribute("secao", secao.getNome());
		request.getSession().setAttribute("estado", estado.getNome());
		request.getSession().setAttribute("grafico", opcao);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafico.jsp");
		requestDispatcher.forward(request, response);
	}
	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return	uma lista de floats contendo os valores
	 */
	public List<Float> getValues(List<Frame> quadros){
		List<Float> valores = new ArrayList<Float>();
		for( Frame q: quadros){
			valores.add(q.getValue());
		}
		return valores;
	}
	/**
	 * Lista os valores dos quadros contidos na lista global 'quadros' mas fazendo o calculo do crescimento anual
	 * @param quadros
	 * @return uma lista de floats contendo os valores de crescimento
	 */
	private List<Float> listarCrescimento(List<Frame> quadros){
		List<Float> valores = new ArrayList<Float>();
		float valorInicial = 0,valorFinal = 0;
		for(int i = 0; i < quadros.size(); i++){
			if(i == 0){
				valorInicial= quadros.get(i).getValue();
				valorFinal = valorInicial;
			}
			else{
				valorInicial = valorFinal;
				valorFinal = quadros.get(i).getValue();
			}
			valores.add(calculaCrescimento(valorFinal,valorInicial));
		}
		
		return valores;
	}
	/**
	 * Lista os anos dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de Strings contendo os anos
	 */
	private List<String> listarAnos(List<Frame> quadros){
		List<String> anos = new ArrayList<String>();
		for( Frame q: quadros){
			anos.add(String.valueOf(q.getYear()));
		}
		return anos;
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
