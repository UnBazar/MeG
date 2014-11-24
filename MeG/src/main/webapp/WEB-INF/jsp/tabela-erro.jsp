<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
	<%@include file='shared/head.html'%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="js/tabela.js" type="text/javascript"></script>
	<!-- Style geral -->
	<link rel="stylesheet" href="css/style.css">
	<title>Tabela de Erros</title>
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jsapi.js"></script>
	<script type="text/javascript" src="js/jspdf.js"></script>
	<script type="text/javascript" src="js/FileSaver.js"></script>
	<script type="text/javascript" src="js/FileSaver.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jspdf.plugin.addimage.js"></script>
	<script type="text/javascript" src="js/jspdf.plugin.png_support.js"></script>
	<script type="text/javascript" src="js/libs/png_support/png.js"></script>
	<script type="text/javascript" src="js/libs/png_support/zlib.js"></script>
	<script type="text/javascript" src="js/canvas2image.js"></script>
	<script type="text/javascript" src="js/html2canvas.js"></script>
	<script type="text/javascript" src="js/base64.js"></script>	
</head>
<body>
	<%@include file='shared/navbar.html'%>
	<div class="container">
		<div id="imprimir">
			<br>
			<h1 style="color: #000000">Tabela de Erros</h1>
			<table border="1" style="width: 30%; border: 2px solid #3366FF">
				<tr>
					<th style="text-align: center">Id</th>
					<th style="text-align: center">Descricao</th>
					<th style="text-align: center">Nome da Classe</th>
					<th style="text-align: center">Data</th>
					<th style="text-align: center">Status</th>
				</tr>
				<c:forEach var="erro" items="${lista}" varStatus="id">
					<tr>
						<td>${erro.id}</td>
						<td>${erro.descricao}</td>
						<td>${erro.nomeDaClasseReferente}</td>
						<td>${erro.data}</td>
						<td>${erro.status}</td>
						<td class="dado"></td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
	<br>
</body>
<%@include file='shared/footer.html'%>
</html>