<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file='shared/head.html'%>
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
  var options = {title: "Estado: ${estado} / Setor: ${secao}"};

  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

  chart.draw(data, options);
};
</script>
</head>
<body>
<%@include file='shared/navbar.html'%>
<div class="container">
	<div id="chart_div" style="width: 900px; height: 500px;"></div>
	<form action="compara" method="post">
	<label>Estado</label>
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
		<button type="submit" class="btn btn-primary" id="btn-submit">Comparar Gráfico</button>
		</form>
</div>
	<%@include file='shared/footer.html'%>
</body>
</html>