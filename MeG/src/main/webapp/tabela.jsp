<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

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
		<title>Ranking</title>
		<script>
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
	<%@include file='shared/navbar.html'%>
		<div class="container">
		<h1 style="color: #000000">Ranking de ${descricao.nome} na área de ${setor} - ${ano} </h1>
		<table border="1" style="width:30%; border: 2px solid #3366FF">
			<tr>
				<th style="text-align:center">Ranking</th>
				<th style="text-align:center">Estado</th>
				<th style="text-align:center">${descricao.nome}</th>
			</tr>
			<c:forEach var="quadro" items="${lista}" varStatus="id">
				<tr>
					<td>${id.count}º</td>
					<td>${quadro.estado.nome}</td>
					<td class="dado">
						<c:if test="${descricao.id == 4 || descricao.id == 5}">	
							R$ ${quadro.valor} 
						</c:if>
						<c:if test="${descricao.id != 4 || descricao.id != 5}">
							${quadro.valor}
						</c:if>
					</td>	
				</tr>
			</c:forEach>
		</table>
		</div>
	</body>
		<%@include file='shared/footer.html'%>
</html>