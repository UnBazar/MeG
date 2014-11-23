<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file='shared/head.html'%>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
	<%@include file='shared/navbar.html'%>
	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row"></div>
		<div class="page-header">
			<h1>Login</h1>
			<small>somente para administradores</small>
		</div>
		<form action="login" method="POST" id="toLogin">
			<input type="text" name="nomeDeUsuario" placeholder="Login" /><br>
			<input type="password" name="senha" placeholder="Senha" /><br>
			<button type="submit" class="btn btn-primary" id="button-login">Entrar</button>
			<br> <a href="cadastro-adm.jsp">Cadastre-se</a>
		</form>
	</div>
	<form action="login" method="POST" id="toLogin">
		<input type="text" name="nomeDeUsuario" placeholder="Login" /><br>
		<input type="password" name="senha" placeholder="Senha" /><br>
		<button type="submit" class="btn btn-primary" id="button-login">Entrar</button>
		<br>
	</form>
	</section>
</body>
</html>