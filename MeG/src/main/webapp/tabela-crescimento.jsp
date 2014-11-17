<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<!-- Optional theme -->
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<!-- Latest compiled and minified JavaScript -->
		<script src="js/tabela.js" type="text/javascript"></script>
		<!-- Style geral -->
		<link rel="stylesheet" href="css/style.css">
		<title>Ranking Crescimento</title>
		<script>
		//	function ajustarSalario() {
		//		var numeros = document.getElementsByClassName("dado");
		//		var descricao = ${descricao};
		//		alert(descricao.nome);
		//		for (var i = 0; i < numeros.length; i++) {
		//			if (descricao.nome == "Salário médio mensal (Salários mínimos)") 
		//				numeros[i].innerHTML = "R$" + ajustarCasasDecimais(numeros[i].innerHTML);
		//			else numeros[i].innerHTML = parseFloat(numeros[i].innerHTML).toFixed(0);
		//		}
		//	}
	
			function ajustarCasasDecimais(string) {
				var num = string.indexOf('.') + 2;
				if(num <= (string.length - 1)) {
					return string.substring(0, num + 1);
				}
				else {
					return string.substring(0, string.indexOf('.') + 2) + "0";
				}
			}
		</script>
	</head>
	<body onload="ajustarSalario()">
	<div class="container">
		<section id="cabecalho">
			<h1><a href="">MeG - Mercado em gráfico</a></h1>
			<ul id="menu">
				<li><a href="#">Sobre</a></li>
				<li><a href="#">Como usar</a></li>
				<li><a href="#">Link</a></li>
			</ul>
		</section><br>
		<h1 style="color: #000000">Ranking de ${descricao.nome} na área de ${setor} - ${anoInicial} - ${anoFinal} </h1>
		<table border="1" style="width:30%; border: 2px solid #3366FF">
			<tr>
				<th style="text-align:center">Ranking</th>
				<th style="text-align:center">Estado</th>
				<th style="text-align:center">${descricao.nome}</th>
			</tr>
			<c:forEach var="quadro" items="${listaCrescimento}" varStatus="id">
				<tr>
					<td>${id.count}º</td>
					<td>${quadro.estado.nome}</td>
					<td class="dado">
						${quadro.valor}%
					</td>	
				</tr>
			</c:forEach>
		</table>
		</div>
	</body>
</html>