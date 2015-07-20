<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Ranking Crescimento</title>
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
	function ajustarCasasDecimais(string) {
		var num = string.indexOf('.') + 2;
		if (num <= (string.length - 1)) {
			return string.substring(0, num + 1);
		} else {
			return string.substring(0, string.indexOf('.') + 2) + "0";
		}
	}
</script>
</head>
<body onload="ajustarSalario()">
	<%@include file='shared/navbar.html'%>
	<div class="container">
		<br>
		<h3>Ranking de crescimento em ${description.content} na área
			de ${section} de ${initialYear} a ${finalYear}</h3>
		<table border="1" class="tabelas">
			<tr>
				<th>Ranking</th>
				<th>Estado</th>
				<th>${description.content}</th>
			</tr>
			<c:forEach var="frame" items="${growthList}" varStatus="id">
				<tr>
					<td>${id.count}º</td>
					<td>${frame.state.name}</td>
					<td class="dado">${frame.value}%</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@include file='shared/footer.html'%>
</html>