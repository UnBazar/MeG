<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
<title>Meg | Gráfico</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);

var valores, anos, tamanho = 0;
valores = ${valores};
anos = ${anos};
tamanho = ${tamanho};
function listarValoresporAnos(){
	var i = 1;
	var lista = new Array();
	lista[0] = ['Anos', '${titulo}'];
	for(i = 1; i <= tamanho; i++){
		lista[i] = [anos[i],  valores[i]];
	}
	return lista;
}

function drawChart() {
  var data = google.visualization.arrayToDataTable(listarValoresporAnos());
  var options = {title: "Setor: ${secao}"};

  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

  chart.draw(data, options);
};
</script>
</head>
<body>
<section class="container">
	<section id="cabecalho">
		<h1><a href="#">MeG - Mercado em gráfico</a></h1>
		<ul id="menu">
			<li><a href="#">Sobre</a></li>
			<li><a href="#">Como usar</a></li>
			<li><a href="#">Link</a></li>
		</ul>
	</section>
	<section id="chart_div" style="width: 900px; height: 500px;"></section>
</section>
</body>
</html>