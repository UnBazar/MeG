package org.meg.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.meg.model.Administrador;
import org.meg.parser.Parser;

@WebServlet("/upload")
public class UploadArquivoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(100000);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				boolean contemArquivo = false;
				for (FileItem item : items) {
					if (!item.isFormField()) {
						contemArquivo = true;
						String url = criaCaminhoDoArquivo();
						int anoInicial = Integer.parseInt(items.get(0).getString());
						int anoFinal = Integer.parseInt(items.get(1).getString());;
						int numeroDeSecoes = 0;
						HttpSession sessao = request.getSession();
						Administrador administrador = (Administrador) sessao.getAttribute("administrador");
						String nomeAdm = new String(formatarNomeUsuario(administrador));
						File uploadedFile = new File(url + nomeAdm + "_" + item.getName());
						item.write(uploadedFile);						
						numeroDeSecoes = getNumeroDeSecoes(items);
						Parser parser = new Parser(uploadedFile.getAbsolutePath(), 27, numeroDeSecoes, 
								anoInicial, anoFinal);
						validaArquivo(items, parser, anoInicial, anoFinal, numeroDeSecoes);
						parser.persist();
						request.setAttribute("erro", false);
					}
				}
				if (contemArquivo) {
					throw new RuntimeException("Nenhum arquivo foi enviado!");
				}
			} catch(Exception e) {
				request.setAttribute("erro", true);
			} 
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/administrador.jsp");
		requestDispatcher.forward(request, response);
	}		
	/**
	 * O método substitui os espaços em branco do nome de usuário por underlines
	 * @param administrador
	 * @return vetor de caracteres que contêm o nome do administrador formatado 
	 */
	private char[] formatarNomeUsuario(Administrador administrador) {
		char[] aux = new char[administrador.getNome().length()];
		
		for (int i = 0; i < administrador.getNome().length(); i++) {
			if (administrador.getNome().charAt(i) == ' ') {
				aux[i] = '_';
			} else {
				aux[i] = administrador.getNome().charAt(i);
			}
		}
		return aux;
	}
	
	/**
	 * Conta o número de seções lidas no formulário
	 * @param items
	 * @return o número de campos do tipo seção lidos no formulário
	 */
	private int getNumeroDeSecoes(List<FileItem> items) {
		int numeroDeSecoes = 0;
		for (int i = 2; i < items.size() - 1; i++) {
			if (items.get(i).getFieldName().equals("secao")) {
				numeroDeSecoes++;
			}
		}
		return numeroDeSecoes;
	}
	
	/**
	 * Este método realiza a validação do arquivo chamando os métodos de validação da classe Parser
	 * @param items
	 * @param parser
	 * @param anoInicial
	 * @param anoFinal
	 * @param numeroDeSecoes 
	 * @throws FileNotFoundException
	 */
	private void validaArquivo(List<FileItem> items, Parser parser, int anoInicial, 
			int anoFinal, int numeroDeSecoes) throws FileNotFoundException {
		parser.validarAno(anoInicial, anoFinal);
		for (int i = 2; i < items.size() - 1; i++) {
			if (items.get(i).getFieldName().equals("secao")) {
				parser.validarSecao(items.get(i).getString());
			}
		}
		parser.validarQuantidadeDeLinhas(numeroDeSecoes);
	}
	
	/**
	 * Este método cria o caminho onde o arquivo será salvo a partir da url do arquivo UploadArquivo.class
	 * @param url
	 */
	private String criaCaminhoDoArquivo() {
		String url;
		url = UploadArquivoServlet.class.getProtectionDomain().getCodeSource().getLocation()+"";
		url = url.replaceAll("file:", "");
		url = url.replaceAll("WEB-INF/classes/br/org/meg/controller/UploadArquivo.class", "");
		return url;
	}
}