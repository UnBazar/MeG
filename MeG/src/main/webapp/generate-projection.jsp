<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file='shared/head.html'%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
<title>Gerar projecao</title>
</head>
<body>
<%@include file='shared/navbar.html'%>
	<section class="container">
			<div id="gerador">
				<h2>Gerar projeção</h2>
				<br>
				<form action="projecao" method="POST">
					<label>Tipo de pesquisa:</label>
					<select name="description">
						<c:forEach items="${descriptions}" var="description">
							<option value="${description.id}">${description.content}</option>
						</c:forEach>
					</select>
					<label id="secao">Setor:</label>
					<select name="section">
						<c:forEach items="${sections}" var="section">
							<option value="${section.id}">${section.name}</option>
						</c:forEach>
					</select>
					<br/>
					<br/>
					<label>Estado:</label>
					<select name="state">
						<c:forEach items="${states}" var="state">
							<option value="${state.id}">${state.stateAbbreviation}</option>
						</c:forEach>
					</select>
					<label id="anoFinal"> Até </label>
					<select name="finalYear">
						<option value="2013" selected="selected">2013</option>
						<option value="2014">2014</option>
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
					</select>
					<br/><br/>
					<button type="submit" class="btn btn-primary" id="btn-submit">Montar Gráfico</button>
				</form>
			</div>
	</section>
	<%@include file='shared/footer.html'%>
</body>
</html>