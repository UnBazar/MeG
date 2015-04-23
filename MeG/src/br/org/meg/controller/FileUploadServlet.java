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
import org.meg.model.Administrator;
import org.meg.parser.Parser;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				int maxFileSize = 100000; // max size in bytes
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(maxFileSize);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				boolean containsFile = false;
				for (FileItem item : items) {
					if (!item.isFormField()) {
						containsFile = true;
						String url = createsFilePath();
						int initialYear = Integer.parseInt(items.get(0).getString());
						int finalYear = Integer.parseInt(items.get(1).getString());;
						int numberOfSections = 0;
						HttpSession sessao = request.getSession();
						Administrator administrator = (Administrator) sessao.getAttribute("administrador");
						String administratorName = new String(formatName(administrator));
						File uploadedFile = new File(url + administratorName + "_" + item.getName());
						item.write(uploadedFile);						
						numberOfSections = getNumberOfSections(items);
						Parser parser = new Parser(uploadedFile.getAbsolutePath(), 27, numberOfSections, 
								initialYear, finalYear);
						validatesFile(items, parser, initialYear, finalYear, numberOfSections);
						parser.persist();
						request.setAttribute("erro", false);
					}
				}
				if (containsFile) {
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
	private char[] formatName(Administrator administrador) {
		char[] aux = new char[administrador.getName().length()];
		
		for (int i = 0; i < administrador.getName().length(); i++) {
			if (administrador.getName().charAt(i) == ' ') {
				aux[i] = '_';
			} else {
				aux[i] = administrador.getName().charAt(i);
			}
		}
		return aux;
	}
	
	/**
	 * Conta o número de seções lidas no formulário
	 * @param items
	 * @return o número de campos do tipo seção lidos no formulário
	 */
	private int getNumberOfSections(List<FileItem> items) {
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
	private void validatesFile(List<FileItem> items, Parser parser, int anoInicial, 
			int anoFinal, int numeroDeSecoes) throws FileNotFoundException {
		parser.validatesYear(anoInicial, anoFinal);
		for (int i = 2; i < items.size() - 1; i++) {
			if (items.get(i).getFieldName().equals("secao")) {
				parser.validatesSector(items.get(i).getString());
			}
		}
		parser.validatesLinesQuantity(numeroDeSecoes);
	}
	
	/**
	 * Este método cria o caminho onde o arquivo será salvo a partir da url do arquivo UploadArquivo.class
	 * @param url
	 */
	private String createsFilePath() {
		String url;
		url = FileUploadServlet.class.getProtectionDomain().getCodeSource().getLocation()+"";
		url = url.replaceAll("file:", "");
		url = url.replaceAll("WEB-INF/classes/br/org/meg/controller/UploadArquivo.class", "");
		return url;
	}
}
