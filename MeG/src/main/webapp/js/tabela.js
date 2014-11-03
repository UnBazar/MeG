function ajustarSalario() {
	var numeros = document.getElementsByClassName("dado");
	var descricao = ${descricao};
	alert(descricao.nome);
	for (var i = 0; i < numeros.length; i++) {
		if (descricao.nome == "Salário médio mensal (Salários mínimos)") 
			numeros[i].innerHTML = "R$" + ajustarCasasDecimais(numeros[i].innerHTML);
		else numeros[i].innerHTML = parseFloat(numeros[i].innerHTML).toFixed(0);
	}
}

function ajustarCasasDecimais(string) {
	var num = string.indexOf('.') + 2;
	if(num <= (string.length - 1)) {
		return string.substring(0, num + 1);
	}
	else {
		return string.substring(0, string.indexOf('.') + 2) + "0";
	}
}