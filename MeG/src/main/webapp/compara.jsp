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
		<title>Meg | Gráfico</title>
		<link rel="stylesheet" href="css/style.css">
		
		<!-- Import JavaScripts -->
		<script type="text/javascript" src="js/jsapi.js"></script>
		<script type="text/javascript" src="js/jspdf.js"></script>
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/jspdf.plugin.standard_fonts_metrics.js"></script>
	    <script type="text/javascript" src="js/jspdf.plugin.split_text_to_size.js"></script>
	    <script type="text/javascript" src="js/jspdf.plugin.from_html.js"></script>
		
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
				  chartImage = chart_div.innerHTML;
			  });
			  
			  chart.draw(data, options);
			};
			
			function generatePDF() {
				var imageData = chartImage;
				var doc = new jsPDF();
				
				doc.setFontSize(30);
				doc.text(35,25,"Gráfico");
				doc.addImage(imageData,'JPEG',15,40,180,160);
				doc.output('dataurl');
				doc.save('file.pdf');
			};
			
		</script>
	</head>
	<body>
		<div class="container">
			<section id="cabecalho">
				<h1><a href=''>MeG - Mercado em gráfico</a></h1>
				<ul id="menu">
					<li><a href="#">Sobre</a></li>
					<li><a href="#">Como usar</a></li>
					<li><a href="#">Link</a></li>
				</ul>
			</section>
			<div id="chart_div" style="width: 900px; height: 500px;"></div>
			<button onclick="generatePDF()">Export PDF</button>
		</div>
	</body>
</html>