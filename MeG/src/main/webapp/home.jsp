<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file='shared/head.html'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MeG - Home</title>
<!-- Script to Activate the Carousel -->
<script>
	$('.carousel').carousel({
		interval : 15000
	//changes the speed
	});
</script>
</head>
<body>
	<%@include file='shared/navbar.html'%>
	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill" style="background-image: url(${defaultNote.imageURL});"></div>
				<div class="carousel-caption">
					<h2>
						${defaultNote.message}
					</h2>
				</div>
			</div>
			<c:forEach items="${observations}" var="notice">
				<div class="item">
				<div class="fill" style="background-image: url(${notice.imageURL});"></div>
				<div class="carousel-caption">
					<h2>
						${notice.message}
					</h2>
				</div>
				</div>
			</c:forEach>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>
	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Bem Vindo ao Mercado em Gráfico</h1>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-headphones"></i> Gerar Gráfico
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para gerar um gráfico de diferentes setores da
							economia</p>
						<a href="grafico" class="btn btn-default">GO!</a>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-table"></i> Gerar Tabela de Ranking
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para gerar uma tabela de diferentes setores da
							economia</p>
						<a href="gerar-tabela.jsp" class="btn btn-default">GO!</a>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-fire"></i> Gerar Projeção
						</h4>
					</div>
					<div class="panel-body">
						<p>Clique aqui para gera uma projeção de diferentes setores da
							economia</p>
						<a href="gerar-projecao.jsp" class="btn btn-default">GO!</a>
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

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 15000
		//changes the speed
		});
	</script>
</body>
</html>