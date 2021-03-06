<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
	<%@include file='../../shared/head.html'%>
	<title>Relatório de Erros</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="js/tabela.js" type="text/javascript"></script>
	<!-- Style geral -->
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file='../../shared/navbar.html'%>
	<div class="container">
		<div id="imprimir">
			<br>
			<h1>Tabela de Erros</h1>
			<table border="1" class="tabelas">
				<tr>
					<th>Descricao</th>
					<th>Nome da Classe</th>
					<th>Data</th>
					<th>Status</th>
				</tr>
				<c:forEach var="erro" items="${lista}" varStatus="id">
					<tr>
						<td>${erro.description}</td>
						<td>${erro.referringClassName}</td>
						<td>${erro.date}</td>
						<td>${erro.status}</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
	<br>
</body>
<%@include file='../../shared/footer.html'%>
</html>