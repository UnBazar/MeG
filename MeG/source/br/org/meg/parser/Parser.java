package br.org.meg.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import br.org.meg.dao.QuadroDAO;
import br.org.meg.model.Descricao;
import br.org.meg.model.Estado;
import br.org.meg.model.Quadro;
import br.org.meg.model.Secao;

public class Parser {
	private Scanner scanner;
	
	public Parser(String arquivo) {
		try {	
			scanner = new Scanner(new FileReader(arquivo)).useDelimiter(";");
		} catch(FileNotFoundException exception) {
			System.err.println(exception);
			throw new RuntimeException("Arquivo não encontrado!");
		}		
	}
	
	public void parse() {
		String regiao;
		ArrayList<Quadro> quadros;
		QuadroDAO dao = new QuadroDAO();
		for (int i = 0; i < 5; i++) {
			regiao = lerRegiao();
			quadros = this.lerEstado(regiao);
			for (int j = 0; j < quadros.size(); j++) {
				dao.adicionar(quadros.get(j));
			}
		}
		scanner.close();
	}
	
	private String lerRegiao() {
		String regiao;
		do {
			regiao = this.scanner.next();
		} while (!regiao.contains("Norte") &&
				!regiao.contains("Nordeste") &&
				!regiao.contains("Sudeste") &&
				!regiao.contains("Sul") &&
				!regiao.contains("Centro-Oeste"));
		if (regiao.contains("Norte")) regiao = "Norte";
		if (regiao.contains("Nordeste")) regiao = "Nordeste";
		if (regiao.contains("Sudeste")) regiao = "Sudeste";
		if (regiao.contains("Sul")) regiao = "Sul";
		if (regiao.contains("Centro-Oeste")) regiao = "Centro-Oeste";
		return regiao;
	}
	
	private ArrayList<Quadro> lerEstado(String regiao) {
		ArrayList<Quadro> quadros = new ArrayList<>();
		Estado estado;
		Secao secao;
		String[] tokens;
		int numeroDeEstados = 0;
		switch(regiao) {
			case "Norte":
				numeroDeEstados = 7;
				break;
			case "Nordeste":
				numeroDeEstados = 9;
				break;
			case "Sudeste":
				numeroDeEstados = 4;
				break;
			case "Sul":
				numeroDeEstados = 3;
				break;
			case "Centro-Oeste":
				numeroDeEstados = 4;
				break;
		}
		
		for (int i = 0; i < 6; i++) {
			scanner.nextLine();
		}
		
		for (int j = 0; j < numeroDeEstados; j++) {
			for (int i = 0; i < 6; i++) { 
				tokens = scanner.nextLine().split(";");
				estado = new Estado();
				estado.setNome(corrigirNome(tokens[0]));
				secao = new Secao();
				secao.setNome(tokens[1].substring(3, tokens[1].length() - 1));
				for (int k = 0; k < 35; k++) {
						quadros.add(new Quadro());
						quadros.get(quadros.size() - 1).setEstado(estado);
						quadros.get(quadros.size() - 1).setSecao(secao);
						quadros.get(quadros.size() - 1).setAno(2006 + k%7);
						Descricao descricao = new Descricao();
						descricao.setId(1 + k/7);
						quadros.get(quadros.size() - 1).setDescricao(descricao);
						if (!tokens[2 + k].equals("-") && !tokens[2 + k].equals("X")) {
							if (k < 28) quadros.get(quadros.size() - 1).setValor(Float.parseFloat(tokens[2 + k]));
							else quadros.get(quadros.size() - 1).setValor(Float.parseFloat(corrigirVirgula(tokens[2 + k])));
						} else quadros.get(quadros.size() - 1).setValor(-1.0f);
						System.out.printf("Tipo: %s Estado: %s Secao: %s Ano: %d Valor: %.1f\n",
								quadros.get(quadros.size() - 1).getDescricao().getNome(), quadros.get(quadros.size() - 1).getEstado().getNome(), 
								quadros.get(quadros.size() - 1).getSecao().getNome(), 
								quadros.get(quadros.size() - 1).getAno(), quadros.get(quadros.size() - 1).getValor());
				}
			}
		}
		
		return quadros;
	}	
	
	private String corrigirVirgula(String expressao) {
		char[] string = new char[expressao.length()];
		for (int i = 0; i < expressao.length() && expressao.charAt(i) != '\n'; i++) {
			if (expressao.charAt(i) == ',') string[i] = '.';
			else string[i] = expressao.charAt(i);
		}
		String expressaoCorrigida = new String(string);
		return expressaoCorrigida;
	}
	
	private String corrigirNome(String estado) {
		String nomeCorrigido;
		int i;
		for (i = 1; i < estado.length(); i++) {
			if (estado.charAt(i) != ' ') break;
		}
		nomeCorrigido = estado.substring(i, estado.length() - 1);
		return nomeCorrigido;
	}
}
