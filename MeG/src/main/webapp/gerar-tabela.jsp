<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

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
		<title>Tabela</title>
	</head>

	<body>
		<div class="container">
		<section id="cabecalho">
			<h1><a href="">MeG - Mercado em gráfico</a></h1>
				<ul id="menu">
					<li><a href="#">Sobre</a></li>
					<li><a href="#">Como usar</a></li>
					<li><a href="#">Link</a></li>
			</ul>
		</section>
		<br>		
		<section id="gerador">
			<h2>Gerar ranking</h2><br>
			<form action="ranking" method="post">
				<label id="ano">Ano:</label>			
				<select name="ano">
					<option value="2007" selected="selected">2007</option>
					<option value="2008">2008</option>
					<option value="2009">2009</option>
					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>			
				</select>
				<label> Descricao</label>
				<select name="descricao">
					<option value="1" >Número de empresas e outras organizações (Unidades)</option>
					<option value="2">Pessoal ocupado total (Pessoas)</option>
					<option value="3">Pessoal ocupado assalariado (Pessoas)</option>
					<option value="4">Salários e outras remunerações (Mil Reais)</option>
					<option value="5">Salário médio mensal (Salários mínimos)</option>
				</select>
				<label id="setor">Setor:</label>
				<select name="setor">
					<option value="1">Agricultura, pecuária, produção florestal, pesca e aquicultura</option>
					<option value="2">Indústrias extrativas</option>
					<option value="3">Indústrias de transformação</option>
					<option value="4">Eletricidade e gás</option>
					<option value="5">Água, esgoto, atividades de gestão de resíduos e descontaminação</option>
					<option value="6">Construção</option>
					<option value="7">Comércio, reparação de veículos automotores e motocicletas</option>
					<option value="8">Transporte, armazenagem e correio</option>
					<option value="9">Alojamento e alimentação</option>
					<option value="10">Informação e comunicação</option>
					<option value="11">Atividades financeiras, de seguros e serviços relacionados</option>
					<option value="12">Atividades imobiliárias</option>
					<option value="13">Atividades profissionais, científicas e técnicas</option>
					<option value="14">Atividades administrativas e serviços complementares</option>
					<option value="15">Administração pública, defesa e seguridade social</option>
					<option value="16">Educação</option>
					<option value="17">Saúde humana e serviços sociais</option>
					<option value="18">Artes, cultura, esporte e recreação</option>
					<option value="19">Outras atividades de serviços</option>
					<option value="20">Serviços domésticos</option>
					<option value="21">Organismos internacionais e outras instituições extraterritoriais</option>
				</select>
				<br><br>
				<button type="submit" class="btn btn-primary" id="btn-submit">Gerar Tabela</button>
			</form>
		</section>
		</div>
	</body>
</html>