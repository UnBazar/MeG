package br.org.meg.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.org.meg.model.Administrador;
import br.org.meg.parser.Parser;

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
						HttpSession sessao = request.getSession();
						Administrador adm = (Administrador) sessao.getAttribute("adm");
						File uploadedFile = new File("/home/pedro/Teste_Upload/" + adm.getNome() 
								+ "_" + item.getName());
						item.write(uploadedFile);
						int anoInicial, anoFinal, numeroDeSecoes = 0;
						anoInicial = Integer.parseInt(items.get(1).getString());
						anoFinal = Integer.parseInt(items.get(2).getString());
						for (int i = 3; i < items.size() - 1; i++) {
							if (items.get(i).getFieldName().equals("secao")) numeroDeSecoes++;
						}
						
						System.out.println(uploadedFile.getAbsolutePath());
						System.out.printf("Numero de seções: %d ano inicial: %d ano final: %d\n",
								numeroDeSecoes, anoInicial, anoFinal);
						
						Parser parser = new Parser(uploadedFile.getAbsolutePath(), 27, numeroDeSecoes, 
								anoInicial, anoFinal);
						
						parser.parse();
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