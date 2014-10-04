<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<div id="chart_div" style="width: 900px; height: 500px;"></div>
</body>
</html>