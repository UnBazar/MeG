<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meg | Gráfico</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<c:set var="quadros" value="${quadros}"/>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
function drawChart() {
  var valores = ${valores};
  var data = google.visualization.arrayToDataTable([
    ['Year', 'Sales'],
    ['2004',  valores[1]],
    ['2005',  valores[2]],
    ['2006',  valores[3]],
    ['2007',  valores[4]]
  ]);

  var options = {title: 'Company Performance'};

  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

  chart.draw(data, options);
};
</script>
</head>
<body>
	<div id="chart_div" style="width: 900px; height: 500px;"></div>
</body>
</html>