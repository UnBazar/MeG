package br.org.meg.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.org.meg.exception.UploadArquivoException;
import br.org.meg.model.Administrador;
import br.org.meg.parser.Parser;

public class UploadArquivo implements Logica {
	
	private String url;
	
	public UploadArquivo(){
		this.url = UploadArquivo.class.getProtectionDomain().getCodeSource().getLocation()+
				"util"+File.separator+"dados"+File.separator;
		this.url = url.replaceAll("file:", "");
		this.url = url.replaceAll("target/classes/", "");
	}
	
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(100000);
				
				ServletFileUpload upload = new ServletFileUpload(factory);

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				
				for (FileItem item : items) {
				
					if (!item.isFormField()) {
						int anoInicial;
						int anoFinal;
						int numeroDeSecoes = 0;
						
						HttpSession sessao = request.getSession();
						Administrador adm = (Administrador) sessao.getAttribute("adm");
						char[] aux = new char[adm.getNome().length()];
						
						for (int i = 0; i < adm.getNome().length(); i++) {
							if (adm.getNome().charAt(i) == ' ') {
								aux[i] = '_';
							} else {
								aux[i] = adm.getNome().charAt(i);
							}
						}
						
						String nomeAdm = new String(aux);
						
						System.out.println(nomeAdm);
						
						File uploadedFile = new File(this.url + nomeAdm + "_" + item.getName());
						
						item.write(uploadedFile);						
						
						anoInicial = Integer.parseInt(items.get(1).getString());
						anoFinal = Integer.parseInt(items.get(2).getString());
						
						for (int i = 3; i < items.size() - 1; i++) {
							if (items.get(i).getFieldName().equals("secao")) {
								numeroDeSecoes++;
								items.get(i).getString();
							}
						}
						
						Parser parser = new Parser(uploadedFile.getAbsolutePath(), 27, numeroDeSecoes, 
								anoInicial, anoFinal);

						parser.validarAno(anoInicial, anoFinal);
						
						for (int i = 3; i < items.size() - 1; i++) {
							if (items.get(i).getFieldName().equals("secao")) {
								parser.validarSecao(items.get(i).getString());
							}
						}
						
						parser.validarQuantidadeDeLinhas(numeroDeSecoes);
						parser.persist();
					}
				}
			} catch(Exception e) {
				throw new UploadArquivoException(e);
			} 
		}
		return "index.jsp";
	}		
}