<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h2>Bem vindo, ${ad}!</h2>
		<p align="right"><a href="LogoutAdm">Logout</a></p><br />
		<form enctype="multipart/form-data" method="post" action="UploadArquivo">
		    <p align="center">
		    	<label>Nome:</label>
		    	<input type="text" name="nome" />
		    	<input type="file" name="arquivo" /><br />
		    </p>
		    <p align="center"> <input type="submit" value="Enviar" /></p>
		</form>
	</body>
</html>