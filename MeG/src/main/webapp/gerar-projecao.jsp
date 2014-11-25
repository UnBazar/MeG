<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file='shared/head.html'%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Style geral -->
<link rel="stylesheet" href="css/style.css">
<title>Gerar projecao</title>
</head>
<body>
<%@include file='shared/navbar.html'%>
	<section class="container">
			<div id="gerador">
				<h2>Gerar projeção</h2>
			<br>
			<form action="projecao" method="POST">
				<label>Tipo de pesquisa:</label>
				<select name="descricao">
					<option value="1">Número de empresas e outras organizações (Unidades)</option>
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
					<option value="7">Comércio; reparação de veículos automotores e motocicletas</option>
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
				<br/>
				<br/>
				<label>Estado:</label>
				<select name="estado">
					<option value="1">AC</option>
					<option value="2">AL</option>
					<option value="3">AM</option>
					<option value="4">AP</option>
					<option value="5">BA</option>
					<option value="6">CE</option>
					<option value="7">DF</option>
					<option value="8">ES</option>
					<option value="9">GO</option>
					<option value="10">MA</option>
					<option value="11">MG</option>
					<option value="12">MS</option>
					<option value="13">MT</option>
					<option value="14">PA</option>
					<option value="15">PB</option>
					<option value="16">PE</option>
					<option value="17">PI</option>
					<option value="18">PR</option>
					<option value="19">RJ</option>
					<option value="20">RN</option>
					<option value="21">RO</option>
					<option value="22">RR</option>
					<option value="23">RS</option>
					<option value="24">SC</option>
					<option value="25">SE</option>
					<option value="26">SP</option>
					<option value="27">TO</option>
				</select>
				<label id="anoFinal"> Até </label>
				<select name="anoFinal">
					<option value="2013" selected="selected">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
				</select>
				<br/><br/>
				<button type="submit" class="btn btn-primary" id="btn-submit">Montar Gráfico</button>
			</form>
			</div>
	</section>
</body>
</html>