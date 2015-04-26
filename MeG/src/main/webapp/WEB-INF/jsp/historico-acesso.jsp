<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<title>Historico</title>
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
			<br>
			<h1>Histórico de acessos</h1>
			<table border="1" class="tabelas">
				<c:forEach items="${histories}" var="history">
					<tr>
						<th>${history.name}</th>
						<td>${history.value}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<br>
</body>
<%@include file='shared/footer.html'%>
</html>