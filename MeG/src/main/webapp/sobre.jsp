<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file='shared/head.html'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MeG - Sobre</title>
</head>
<body>
	<%@include file='shared/navbar.html'%>
	<!-- Page Heading/Breadcrumbs -->
	<section class="container">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Sobre <small>Mercado em Gráfico</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<!-- Intro Content -->
	<div class="row">
		<div class="col-md-6">
			<img src="img/Logo.png" alt="" width="550" height="340" />
		</div>
		<div class="col-md-6" id="sobre">
			<p>Mercado em Gráfico (MeG) é uma aplicação web inicialmente desenvolvida por
				alunos da disciplina de Métodos de Desenvolvimento de Software(MDS)
				e Gestão de Portifólios e Projetos de Software(GPP) da Universidade
				de Brasília (UnB) – FGA.</p>
			<p>O sistema consiste em uma aplicação web de código aberto, que tem como
				principal objetivo facilitar a procura de informações referentes ao
				mercado de trabalho no Brasil. O site utiliza como principal
				subsídio os dados abertos disponibilizados pelo IBGE. Os dados
				abertos são uma iniciativa do governo brasileiro que permite o uso
				de determinados dados, sem restrições de direitos autorais e
				patentes.</p>
			<p>A idéia do projeto é realizar um estudo, a partir dos dados
				disponíveis, da situação do mercado brasileiro nos últimos anos, tal
				como o crescimento do número de empresas, a média salarial,
				quantidade de pessoas assalariadas de determinados setores, entre
				outros temas, e a partir deste estudo, fornecer quadros comparativos
				entre os estados do país e projeções econômicas de uma região
				através de gráficos e tabelas.</p>
		</div>
	</div>
</section>
	<%@include file='shared/footer.html'%>
</body>
</html>