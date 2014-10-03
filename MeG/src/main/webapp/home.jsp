<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h2>Bem vindo, ${adm.nome}!</h2>
		<p align="right"><a href="logout">Logout</a></p><br />
		<form action="sistema" enctype="multipart/form-data" method="post">
			<input type="hidden" name="logica" value="UploadArquivo">
		    <label>De </label>
		    <select name="anoInicial">
		    	<option>2006</option>
		    	<option>2007</option>
		    	<option>2008</option>
		    	<option>2009</option>
		    	<option>2010</option>
		    	<option>2011</option>		    
		    </select>
		    <label>Até </label>
		    <select name="anoFinal">
		    	<option>2007</option>
		    	<option>2008</option>
		    	<option>2009</option>
		    	<option>2010</option>
		    	<option>2011</option>
		    	<option>2012</option>
		    </select>
		    <br />
		    <input type="checkbox" name="secao" value="A">Agricultura, pecuária, produção florestal, pesca e aquicultura<br>
			<input type="checkbox" name="secao" value="B">Indústrias extrativas <br>
		    <input type="checkbox" name="secao" value="C">Indústrias de transformação <br>
		    <input type="checkbox" name="secao" value="D">Eletricidade e gás <br>
		    <input type="checkbox" name="secao" value="E">Água, esgoto, atividades de gestão de resíduos e descontaminação <br>
		    <input type="checkbox" name="secao" value="F">Construção <br>
		    <input type="checkbox" name="secao" value="G">Comércio; reparação de veículos automotores e motocicletas <br>
		    <input type="checkbox" name="secao" value="H">Transporte, armazenagem e correio <br>
		    <input type="checkbox" name="secao" value="I">Alojamento e alimentação <br>
		    <input type="checkbox" name="secao" value="J">Informação e comunicação <br>
		    <input type="checkbox" name="secao" value="K">Atividades financeiras, de seguros e serviços relacionados <br>
		    <input type="checkbox" name="secao" value="L">Atividades imobiliárias <br>
		    <input type="checkbox" name="secao" value="M">Atividades profissionais, científicas e técnicas <br>
		    <input type="checkbox" name="secao" value="N">Atividades administrativas e serviços complementares <br>
		    <input type="checkbox" name="secao" value="O">Administração pública, defesa e seguridade social <br>
		    <input type="checkbox" name="secao" value="P">Educação <br>
		    <input type="checkbox" name="secao" value="Q">Saúde humana e serviços sociais <br>
		    <input type="checkbox" name="secao" value="R">Artes, cultura, esporte e recreação <br>
		    <input type="checkbox" name="secao" value="S">Outras atividades de serviços <br>
		    <input type="checkbox" name="secao" value="T">Serviços domésticos <br>
		    <input type="checkbox" name="secao" value="U">Organismos internacionais e outras instituições extraterritoriais <br>
		    
		    <p align="center">
		    	<label>Arquivo </label>
		    	<input type="file" name="arquivo" /><br />
		    </p>
		    
		    <p align="center"> <input type="submit" value="Enviar" /></p>
		    		    
		</form>
	</body>
</html>