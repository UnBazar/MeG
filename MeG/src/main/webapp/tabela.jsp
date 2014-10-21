<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Ranking</title>
		<link rel="stylesheet" href="/css/tabela.css">
	</head>
	<body>
		<h1>Ranking de salário médio na área da educação - </h1>
				<table>
			<tr>
				<th>Ranking</th>
				<th>Estado</th>
				<th>Salario médio</th>
			</tr>
			<c:forEach var="quadro" items="${lista}" varStatus="id">
				<tr>
					<td>${id.count}º</td>
					<td>${quadro.estado.nome}</td>
					<td>R$${quadro.valor}</td>	
				</tr>
			</c:forEach>
		</table>
	</body>
</html>