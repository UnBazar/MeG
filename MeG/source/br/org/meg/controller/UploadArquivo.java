package br.org.meg.controller;

import java.io.File;
import java.util.Date;
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
				System.out.println("lala");
				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				System.out.println(items.get(0).getFieldName());
				System.out.println(items.get(0).getName());
				for (FileItem item : items) {
					System.out.println(item.getName() + item.getFieldName());
					if (!item.isFormField()) {
						File uploadedFile = new File("/home/pedro/Teste_Upload/" + new Date().getTime() + 
								"_" + item.getName());
						item.write(uploadedFile);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}
		}
		return "index.jsp";
	}		
}