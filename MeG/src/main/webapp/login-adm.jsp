<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<form action="sistema" method="POST">
			<input type="hidden" name="logica" value="LoginAdm">
			<p align="center">Login <input type="text" name="nomeDeUsuario"></p>
			<p align="center">Senha <input type="password" name="senha"></p>
			<p align="center"><input type="submit" value="Entrar"> </p>
			<p align="center"><a href="cadastro-adm.jsp">Cadastre-se</a></p>
		</form>
	</body>
</html>