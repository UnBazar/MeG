<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
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
		<title>Meg | Gráfico</title>
		<link rel="stylesheet" href="css/style.css">
		
		<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/jsapi.js"></script>
		<script type="text/javascript" src="js/jspdf.js"></script>
		<script type="text/javascript" src="js/FileSaver.js"></script>
		<script type="text/javascript" src="js/FileSaver.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jspdf.plugin.addimage.js"></script>
		<script type="text/javascript" src="js/jspdf.plugin.png_support.js"></script>
		<script type="text/javascript" src="js/libs/png_support/png.js"></script>
		<script type="text/javascript" src="js/libs/png_support/zlib.js"></script>
		<script type="text/javascript">
	
			google.load("visualization", "1", {packages:["corechart"]});
			google.setOnLoadCallback(drawChart);
			
			var valores = ${valores};
			var valores2 = ${valores2};
			var anos = ${anos};
			var tamanho = ${tamanho};
			var chartImage;
			
			function listarValoresporAnos(){
				var i = 1;
				var lista = new Array();
				lista[0] = ['Anos', '${estado}', '${estado2}'];
				for(i = 1; i <= tamanho; i++){
					lista[i] = [anos[i],  valores[i], valores2[i]];
				}
				return lista;
			}
			
			function drawChart() {
			  var data = google.visualization.arrayToDataTable(listarValoresporAnos());
			  var options = {title: "Setor: ${secao} / ${titulo}"};
			  
			  var chart_div = document.getElementById("chart_div");
			  var chart = new google.visualization.LineChart(chart_div);
			  
			  // Wait for the chart to finish drawing before calling the getImageURI() method
			  google.visualization.events.addListener(chart, 'ready', function(){
				  chart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
				  chartImage = chart.getImageURI();
				  });
			  chart.draw(data, options);
			}
			
			function generatePDF() {
				var doc = new jsPDF();
				
				doc.setFontSize(30);
				doc.text(35,25,"Grafico");
				doc.addImage(chartImage, 'PNG', 15, 140, 180, 250);
				doc.save('file.pdf');
			}
		</script>
	</head>
	<body>
		<!-- Import JavaScripts -->
		<div class="container">
			<section id="cabecalho">
				<h1>
					<a href=''>MeG - Mercado em gráfico</a>
				</h1>
				<ul id="menu">
					<li><a href="#">Sobre</a></li>
					<li><a href="#">Como usar</a></li>
					<li><a href="#">Link</a></li>
				</ul>
			</section>
			<div id="chart_div" style="width: 900px; height: 500px;"></div>
			<button onclick="javascript:generatePDF()" class="btn btn-primary" id="btn-submit">Export PDF</button>
		</div>
	</body>
</html>