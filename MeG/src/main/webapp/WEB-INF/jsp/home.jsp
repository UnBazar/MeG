<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
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
		    <input type="checkbox" name="secao" value="A Agricultura, pecuária, produção florestal, pesca e aquicultura">Agricultura, pecuária, produção florestal, pesca e aquicultura<br>
			<input type="checkbox" name="secao" value="B Indústrias extrativas">Indústrias extrativas <br>
		    <input type="checkbox" name="secao" value="C Indústrias de transformação">Indústrias de transformação <br>
		    <input type="checkbox" name="secao" value="D Eletricidade e gás">Eletricidade e gás <br>
		    <input type="checkbox" name="secao" value="E Água, esgoto, atividades de gestão de resíduos e descontaminação">Água, esgoto, atividades de gestão de resíduos e descontaminação <br>
		    <input type="checkbox" name="secao" value="F Construção">Construção <br>
		    <input type="checkbox" name="secao" value="G Comércio, reparação de veículos automotores e motocicletas">Comércio, reparação de veículos automotores e motocicletas <br>
		    <input type="checkbox" name="secao" value="H Transporte, armazenagem e correio">Transporte, armazenagem e correio <br>
		    <input type="checkbox" name="secao" value="I Alojamento e alimentação">Alojamento e alimentação <br>
		    <input type="checkbox" name="secao" value="J Informação e comunicação">Informação e comunicação <br>
		    <input type="checkbox" name="secao" value="K Atividades financeiras, de seguros e serviços relacionados">Atividades financeiras, de seguros e serviços relacionados <br>
		    <input type="checkbox" name="secao" value="L Atividades imobiliárias">Atividades imobiliárias <br>
		    <input type="checkbox" name="secao" value="M Atividades profissionais, científicas e técnicas">Atividades profissionais, científicas e técnicas <br>
		    <input type="checkbox" name="secao" value="N Atividades administrativas e serviços complementares">Atividades administrativas e serviços complementares <br>
		    <input type="checkbox" name="secao" value="O Administração pública, defesa e seguridade social">Administração pública, defesa e seguridade social <br>
		    <input type="checkbox" name="secao" value="P Educação">Educação <br>
		    <input type="checkbox" name="secao" value="Q Saúde humana e serviços sociais">Saúde humana e serviços sociais <br>
		    <input type="checkbox" name="secao" value="R Artes, cultura, esporte e recreação">Artes, cultura, esporte e recreação <br>
		    <input type="checkbox" name="secao" value="S Outras atividades de serviços">Outras atividades de serviços <br>
		    <input type="checkbox" name="secao" value="T Serviços domésticos">Serviços domésticos <br>
		    <input type="checkbox" name="secao" value="U Organismos internacionais e outras instituições extraterritoriais">Organismos internacionais e outras instituições extraterritoriais <br>
		    
		    <p align="center">
		    	<label>Arquivo </label>
		    	<input type="file" name="arquivo" /><br />
		    </p>
		    
		    <p align="center"> <input type="submit" value="Enviar" /></p>
		    		    
		</form>
	</body>
</html>