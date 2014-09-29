<html>
	<head>
		<title>Cadastro</title>
	</head>
	<body>
		<form action="ControllerServlet" method="POST">
			<input type="hidden" name="logica" value="CadastroAdm">
			Nome <br />
			<input type="text" name="nome"> <br />
			Nome de usuário <br />
			<input type="text" name="nomeUsuario"> <br />
			Email <br />
			<input type="text" name="email"> <br />
			Senha <br />
			<input type="password" name="senha"> <br />
			Confirme a senha <br />
			<input type="password" name="confirmacao"> <br />
			<input type="submit" value="Enviar"> <br />
		</form>
	</body>
</html>