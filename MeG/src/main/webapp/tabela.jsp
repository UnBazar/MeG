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
		<title>Ranking</title>
		
		<script type="text/javascript">

		    function gerarRankingPDF() {
		        $(function () {
		            var specialElementHandlers = {
		                '#editor': function (element,renderer) {
		                    return true;
		                }
		            };
		         $('#cmd').click(function () {
		                var doc = new jsPDF();
		                doc.fromHTML($('#container').html(), 15, 15, {
		                    'width': 170,'elementHandlers': specialElementHandlers
		                });
		                doc.save('ranking.pdf');
		            });  
		        }); 
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
			</section>
			<br>
			<h1 style="color: #000000">Ranking de salário médio na área de ${setor} - ${ano} </h1>
			<table border="1" style="width:30%; border: 2px solid #3366FF">
				<tr>
					<th style="text-align:center">Ranking</th>
					<th style="text-align:center">Estado</th>
					<th style="text-align:center">Salario médio</th>
				</tr>
				<c:forEach var="quadro" items="${lista}" varStatus="id">
					<tr>
						<td>${id.count}º</td>
						<td>${quadro.estado.nome}</td>
						<td class="salarioMedio">${quadro.valor}</td>	
					</tr>
				</c:forEach>
			</table>
			<button onclick="javascript:gerarRankingPDF()"></button>
		</div>
	</body>
</html>