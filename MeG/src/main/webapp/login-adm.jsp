<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file='shared/head.html'%>
<title>Login</title>
</head>
<body>
<%@include file='shared/navbar.html'%>
<!-- Page Content -->
<section class="container" id="login">
	<h1 class="page-header">
		Login <small> Apenas para administradores</small>
	</h1>
	<form action="login" method="POST">
		<input type="text" name="nomeDeUsuario" placeholder="Login" />
		<br/>
		<input type="password" name="senha" placeholder="Senha" />
		<br/>
		<button type="submit" class="btn btn-primary" id="button-login">Entrar</button>
		<br/> 
	</form>
</section>
<%@include file='shared/footer.html'%>
</body>
</html>