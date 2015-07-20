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
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
<title>Tabela</title>

</head>
<body>
	<%@include file='shared/navbar.html'%>
	<div class="container">
		<section id="gerador">
			<h2>Gerar ranking</h2>
			<br>
			<form action="ranking" method="post">
				<label> Tipo de pesquisa: </label>		
				<select name="description"> 
					<c:forEach items="${descriptions}" var="description">
						<option value="${description.id}">${description.content}</option>
					</c:forEach>
				</select>
				<label id="setor">Setor:</label> 
				<select name="section">
					<c:forEach items="${sections}" var="section">
						<option value="${section.id}">${section.name}</option>
					</c:forEach>
				</select>
				<br>
				<br>
				<label id="ano">Ano:</label> 
				<select name="year">
					<option value="2007" selected="selected">2007</option>
					<option value="2008">2008</option>
					<option value="2009">2009</option>
					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
				</select> 
				<br> <br>
				<button type="submit" class="btn btn-primary" id="btn-submit">Gerar Tabela</button>
			</form>
		</section>
	</div>
	<%@include file='shared/footer.html'%>
</body>
</html>