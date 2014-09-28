<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meg</title>
</head>
<body>
	<form action="grafico">
		<label>Tipo de pesquisa</label>
		<select name="tipo">
			<option>Número de empresas e outras organizações (Unidades)</option>
			<option>Pessoal ocupado total (Pessoas)</option>
			<option>Pessoal ocupado assalariado (Pessoas)</option>
			<option>Salários e outras remunerações (Mil Reais)</option>
			<option>Salário médio mensal (Salários mínimos)</option>
		</select>
		<label>Setor</label>
		<select name="setor">
			<option>Agricultura, pecuária, produção florestal, pesca e aquicultura</option>
			<option>Indústrias extrativas</option>
			<option>Indústrias de transformação</option>
			<option>Eletricidade e gás</option>
			<option>Água, esgoto, atividades de gestão de resíduos e descontaminação</option>
			<option>Construção</option>
			<option>Comércio; reparação de veículos automotores e motocicletas</option>
			<option>Transporte, armazenagem e correio</option>
			<option>Alojamento e alimentação</option>
			<option>Informação e comunicação</option>
			<option>Atividades financeiras, de seguros e serviços relacionados</option>
			<option>Atividades imobiliárias</option>
			<option>Atividades profissionais, científicas e técnicas</option>
			<option>Atividades administrativas e serviços complementares</option>
			<option>Administração pública, defesa e seguridade social</option>
			<option>Educação</option>
			<option>Saúde humana e serviços sociais</option>
			<option>Artes, cultura, esporte e recreação</option>
			<option>Outras atividades de serviços</option>
			<option>Serviços domésticos</option>
			<option>Organismos internacionais e outras instituições extraterritoriais</option>
			
		</select>
		<br/>
		<br/>
		<label>Estado</label>
		<select name="estado">
			<option>AC</option>
			<option>AL</option>
			<option>AP</option>
			<option>AM</option>
			<option>BA</option>
			<option>CE</option>
			<option>DF</option>
			<option>ES</option>
			<option>GO</option>
			<option>MA</option>
			<option>MT</option>
			<option>MA</option>
			<option>MG</option>
			<option>PR</option>
			<option>PB</option>
			<option>PA</option>
			<option>PE</option>
			<option>PI</option>
			<option>RJ</option>
			<option>RN</option>
			<option>RS</option>
			<option>RO</option>
			<option>RR</option>
			<option>SC</option>
			<option>SE</option>
			<option>SP</option>
			<option>TO</option>
		</select>
		<label>De</label>
		<select>
			<option>2006</option>
			<option>2007</option>
			<option>2008</option>
			<option>2009</option>
			<option>2010</option>
			<option>2011</option>
			<option>2012</option>
			
		</select>
		<label>até</label>
		<select>
			<option>2007</option>
			<option>2008</option>
			<option>2009</option>
			<option>2010</option>
			<option>2011</option>
			<option>2012</option>
		</select>
		<br/><br/>
		<button type="submit">Montar Gráfico</button>
	</form>
</body>
</html>