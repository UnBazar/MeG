package br.org.meg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadArquivo implements Logica {

	public String executa(HttpServletRequest request, 
			HttpServletResponse response){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				System.out.println("uploadArquivo");
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(100000);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				
				for (FileItem item : items) {
					if (item.isFormField()) {
						if (item.getName().equals("anoInicial"));
							System.out.println("Chegou aqui!");
					}
				}
			} catch(Exception e) {
				System.err.println(e);
			}
		}
		return "index.jsp";
	}		
}