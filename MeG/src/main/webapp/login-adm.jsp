<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
<title>Login</title>
</head>
	<body>
		<section class="container" id="login">
		<div class="page-header">
			<h1>Login</h1><small>somente para administradores</small>
		</div>
		<form action="sistema" method="POST" id="toLogin">
			<input type="hidden" name="logica" value="LoginAdm" />
			<input type="text" name="nomeDeUsuario" placeholder="Login" /><br>
			<input type="password" name="senha" placeholder="Senha" /><br>
			<button type="submit" class="btn btn-primary" id="button-login">Entrar</button><br>
			<a href="cadastro-adm.jsp">Cadastre-se</a>
		</form>
		</section>
	</body>
</html>