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

					// Convert and download as image 
					//      Canvas2Image.saveAsPNG(canvas); 
					//      $("#img-out").append(canvas);
					// Clean up 
					//document.body.removeChild(canvas);

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
	<div class="container">
		<section id="cabecalho">
			<h1>
				<a href="">MeG - Mercado em gráfico</a>
			</h1>
			<ul id="menu">
				<li><a href="#">Sobre</a></li>
				<li><a href="#">Como usar</a></li>
				<li><a href="#">Link</a></li>
			</ul>
		</section>
		<div id="imprimir">
			<br>
			<h1 style="color: #000000">Ranking de ${descricao.nome} na área
				de ${setor} - ${ano}</h1>
			<table border="1" style="width: 30%; border: 2px solid #3366FF">
				<tr>
					<th style="text-align: center">Ranking</th>
					<th style="text-align: center">Estado</th>
					<th style="text-align: center">${descricao.nome}</th>
				</tr>
				<c:forEach var="quadro" items="${lista}" varStatus="id">
					<tr>
						<td>${id.count}º</td>
						<td>${quadro.estado.nome}</td>
						<td class="dado"><c:if
								test="${descricao.id == 4 || descricao.id == 5}">	
									R$ ${quadro.valor} 
								</c:if> <c:if test="${descricao.id != 4 || descricao.id != 5}">
									${quadro.valor}
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