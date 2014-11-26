<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file='shared/head.html'%>
<meta charset="utf-8">
<title>MeG - Home</title>
</head>
<body>
	<%@include file='shared/navbar.html'%>
	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Bem Vindo ao Mercado em Gráfico</h1>
				<p align="right"><a href="logout">Logout</a></p><br />
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-headphones"></i> Upload Arquivo
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para fazer o upload de um arquivo</p>
						<form action="uploadArquivo" method="POST">
							<button type="submit" class="btn btn-primary" id="btn-submit">GO!</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-table"></i> Gerar Tabela do Histórico de acessos
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para gerar uma tabela com o histórico de acessos</p>
						<form action="historico" method="POST">
							<button type="submit" class="btn btn-primary" id="btn-submit">GO!</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-fire"></i> Tabela de Erros
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para gerar uma tabela com o hitórico de erros</p>
						<form action="erro" method="POST">
							<button type="submit" class="btn btn-primary" id="btn-submit">GO!</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<%@include file='shared/footer.html'%>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>