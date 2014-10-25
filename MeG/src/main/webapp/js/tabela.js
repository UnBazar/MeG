function ajustarSalario() {
	var numeros = document.getElementsByClassName("salarioMedio");
	for (var i = 0; i < numeros.length; i++) {
		numeros[i].innerHTML = ajustarCasasDecimais(numeros[i].innerHTML);
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