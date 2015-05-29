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
import org.apache.log4j.Logger;
import org.meg.model.Administrator;
import org.meg.parser.Parser;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("Upload");

	private final String ADMINISTRATOR_VIEW = "/WEB-INF/jsp/administrator.jsp";
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			try {
				// max size in bytes
				final int maxFileSize = 100000;
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(maxFileSize);
				// Responsible for abstracting file uploads processes
				ServletFileUpload upload = new ServletFileUpload(factory);
				// This is a list that stores a file or a form item that was received within a multipart/form-data POST request
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				// Variable responsible for checking if the itens sent contains an uploaded file
				boolean containsFile = false;
				for(FileItem item : items) {
					// Checks if file item instance represents an uploaded file
					if(!item.isFormField()) {
						containsFile = true;
						String url = createFilePath();
						int initialYear = Integer.parseInt(items.get(0).getString());
						int finalYear = Integer.parseInt(items.get(1).getString());
						int numberOfSections = 0;
						final int numberOfStates = 27;
						HttpSession sessao = request.getSession();
						Administrator administrator = (Administrator) sessao.getAttribute("administrador");
						String administratorName = new String(formatName(administrator));
						File uploadedFile = new File(url + administratorName + "_" + item.getName());
						item.write(uploadedFile);						
						numberOfSections = getNumberOfSections(items);
						Parser parser = new Parser(uploadedFile.getAbsolutePath(), numberOfStates, numberOfSections, 
								initialYear, finalYear);
						validatesFile(items, parser, initialYear, finalYear, numberOfSections);
						parser.persist();
						request.setAttribute("erro", false);
					}
				}
				if(containsFile) {
					throw new RuntimeException("Nenhum arquivo foi enviado!");
				}
			} catch(Exception e) {
				request.setAttribute("erro", true);
				logger.error("No file was sent.");
			} 
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMINISTRATOR_VIEW);
		requestDispatcher.forward(request, response);
		logger.info("Requested a file upload.");
	}		
	/**
	 * This method formats the administrator name that uploaded the file in a specific format:
	 * it changes any blank spaces into underlines. e.g: "super admin" becomes super_admin
	 * @param administrator
	 * @return array of characters that contains the administrator's name formatted 
	 */
	private char[] formatName(Administrator administrator) {
		char[] aux = new char[administrator.getName().length()];
		
		for (int i = 0; i < administrator.getName().length(); i++) {
			if (administrator.getName().charAt(i) == ' ') {
				aux[i] = '_';
			} else {
				aux[i] = administrator.getName().charAt(i);
			}
		}
		
		return aux;
	}
	
	/**
	 * Checks and counts the number of sections that were read in the form
	 * 
	 * @param items
	 * @return the number of fields with the type: Section read in the form
	 */
	private int getNumberOfSections(List<FileItem> items) {
		int numberOfSections = 0;
		for (int i = 2; i < items.size() - 1; i++) {
			if (items.get(i).getFieldName().equals("secao")) {
				numberOfSections++;
			}
		}
		return numberOfSections;
	}
	
	/**
	 * This method is responsible for evaluating if the file sent is in the correct format. 
	 * @param items
	 * @param parser
	 * @param initialYear
	 * @param finalYear
	 * @param numberOfSections 
	 * @throws FileNotFoundException
	 */
	private void validatesFile(List<FileItem> items, Parser parser, int initialYear, 
			int finalYear, int numberOfSections) throws FileNotFoundException {
		parser.validatesYear(initialYear, finalYear);
		for (int i = 2; i < items.size() - 1; i++) {
			if (items.get(i).getFieldName().equals("secao")) {
				parser.validatesSector(items.get(i).getString());
			}
		}
		parser.validatesLinesQuantity(numberOfSections);
	}
	
	/**
	 * This method creates the absolute path in which the uploaded file will be stored
	 * @param url
	 */
	private String createFilePath() {
		String url;
		url = FileUploadServlet.class.getProtectionDomain().getCodeSource().getLocation()+"";
		url = url.replaceAll("file:", "");
		url = url.replaceAll("WEB-INF/classes/br/org/meg/controller/UploadArquivo.class", "");
		return url;
	}
}
