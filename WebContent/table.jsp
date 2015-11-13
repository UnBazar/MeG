<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
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

<script type="text/javascript">
	$(function() {
		$("#btnSave").click(function() {
			html2canvas($('#imprimir'), {
				onrendered : function(canvas) {
					theCanvas = canvas;
					var doc = new jsPDF();
					doc.addImage(canvas, 'PNG', 20, 30, 150, 0);
					doc.save('ranking.pdf');
				}
			});
		});
	});
</script>
</head>

<body onload="ajustarSalario()">
	<%@include file='shared/navbar.html'%>
	<div class="container">
		<div id="imprimir">
			<br>
			<h3>Ranking de ${descricao.content} no setor de ${secao} - ${ano} </h3>
			<table border="1" class="tabelas">
				<tr>
					<th>Ranking</th>
					<th>Estado</th>
					<th>${descricao.content}</th>
				</tr>
				<c:forEach var="quadro" items="${lista}" varStatus="id">
					<tr>
						<td>${id.count}º</td>
						<td>${quadro.state.name}</td>
						<td class="dado">
							<c:if test="${descricao.id == 4 || descricao.id == 5}">	
									R$ ${quadro.value} 
							</c:if> 
							<c:if test="${descricao.id != 4 && descricao.id != 5}">
									${quadro.value}
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<button class="btn btn-primary" id="btnSave">Salvar em PDF</button>
		<br>
	</div>
	<br>
</body>
<%@include file='shared/footer.html'%>
</html>