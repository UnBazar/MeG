<!DOCTYPE html>
<html>
<head>
<%@ include file='shared/head.html' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
<title>Meg</title>
</head>
<body>
<%@include file='shared/navbar.html'%>
	<div class="container">
	<section id="gerador">
		<h2>Gerar gráfico</h2>
		<br>
		<form action="grafico" method="POST">
			<input type="radio" name="grafico" value="geral" checked> Gráfico geral
			<input type="radio" name="grafico" value="do crescimento"> Gráfico do crescimento
			<br/>
			<br/>
			<label>Tipo de pesquisa:</label>
			<select name="description">
				<c:forEach items="${descriptions}" var="description">
					<option value="${description.id}">${description.content}</option>
				</c:forEach>
			</select>
			<label id="setor">Setor:</label>
			<select name="section">
				<c:forEach items="${sections}" var="section">
					<option value="${section.id}">${section.nome}</option>
				</c:forEach>
			</select>
			<br/>
			<br/>
			<label>Estado:</label>
			<select name="state">
				<c:forEach items="${states}" var="state">
					<option value="${state.id}">${state.sigla}</option>
				</c:forEach>
			</select>
			<label id="anoInicial">De</label>
			<select name="initialYear">
				<option value="2006" selected="selected">2006</option>
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2009</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
				
			</select>
			<label id="anoFinal">até </label>
			<select name="finalYear">
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2009</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012" selected="selected">2012</option>
			</select>
			<br/>
			<br/>
			<button type="submit" class="btn btn-primary" id="btn-submit">Montar Gráfico</button>
		</form>
	</section>
	</div>
	<%@include file='shared/footer.html'%>
</body>
</html>