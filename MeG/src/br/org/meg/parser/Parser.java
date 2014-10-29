package org.meg.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.meg.dao.QuadroDAO;
import org.meg.exception.UploadArquivoException;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

public class Parser {
	private Scanner scanner;
	private int anoInicial;
	private int anoFinal;
	private int quantidadeEstados;
	private int quantidadeSecoes;
	private String arquivo;

	public Parser(String arquivo, int quantidadeEstados, int quantidadeSecoes,
			int anoInicial, int anoFinal) {

		if (anoFinal <= anoInicial || quantidadeEstados == 0
				|| quantidadeSecoes == 0) {
			throw new IllegalArgumentException(
					"Argumentos do parser inválidos!");
		}
		this.arquivo = arquivo;
		this.quantidadeEstados = quantidadeEstados;
		this.quantidadeSecoes = quantidadeSecoes;
		this.anoInicial = anoInicial;
		this.anoFinal = anoFinal;
	}

	@SuppressWarnings("resource")
	public void validarQuantidadeDeLinhas(int quantidadeDeSecoes)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.arquivo)).useDelimiter(";");
		int i = 0;
		this.lerCabecalho();

		do {
			i++;
			scanner.nextLine();
		} while (scanner.hasNext());

		if ((27 * this.quantidadeSecoes + 6) != i) {
			throw new UploadArquivoException(
					"Dados de entrada incompatíveis com o arquivo! (Quantidade de linhas)");
		}

		scanner.close();
	}

	@SuppressWarnings("resource")
	public void validarSecao(String secao) throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.arquivo)).useDelimiter(";");
		this.lerCabecalho();
		boolean contemSecao = false;
		String token;

		while (scanner.hasNext()) {
			token = scanner.next();
			if (token.length() > 2) {
				token = token.substring(1, token.length() - 1);
			}
			if (token.equals(secao)) {
				contemSecao = true;
				break;
			}
		}
		this.scanner.close();
		if (!contemSecao) {
			throw new UploadArquivoException(
					"Dados de entrada incompatíveis com o arquivo! (Seções)");
		}
	}

	@SuppressWarnings("resource")
	public void validarAno(int anoInicial, int anoFinal)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.arquivo)).useDelimiter(";");
		this.lerCabecalho();
		String[] tokens = scanner.nextLine().split(";");

		if (tokens.length != ((anoFinal - anoInicial + 1) * 5) + 2) {
			throw new UploadArquivoException(
					"Dados de entrada incompatíveis com o arquivo! (Ano)");
		}

		scanner.close();
	}

	@SuppressWarnings("resource")
	public void persist() throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.arquivo)).useDelimiter(";");
		ArrayList<Quadro> quadros = this.lerEstados();
		QuadroDAO dao = new QuadroDAO();
		for (Quadro quadro : quadros) {
			dao.adicionar(quadro);
		}
		scanner.close();
	}

	private ArrayList<Quadro> lerEstados() {
		ArrayList<Quadro> quadros = new ArrayList<>();
		Estado estado;
		Secao secao;
		String[] tokens;
		int tempo = this.anoFinal - this.anoInicial + 1;
		this.lerCabecalho();

		for (int j = 0; j < this.quantidadeEstados; j++) {
			for (int i = 0; i < this.quantidadeSecoes; i++) {
				tokens = scanner.nextLine().split(";");
				estado = new Estado();
				estado.setNome(tokens[0].substring(1, tokens[0].length() - 1));
				secao = new Secao();
				secao.setNome(tokens[1].substring(3, tokens[1].length() - 1));
				for (int k = 0; k < 5 * tempo; k++) {
					quadros.add(new Quadro());
					quadros.get(quadros.size() - 1).setEstado(estado);
					quadros.get(quadros.size() - 1).setSecao(secao);
					quadros.get(quadros.size() - 1).setAno(
							this.anoInicial + k % tempo);
					Descricao descricao = new Descricao();
					descricao.setId(1 + k / tempo);
					quadros.get(quadros.size() - 1).setDescricao(descricao);
					if (!tokens[2 + k].equals("-")
							&& !tokens[2 + k].equals("X")) {
						if (k < 28)
							quadros.get(quadros.size() - 1).setValor(
									Float.parseFloat(tokens[2 + k]));
						else
							quadros.get(quadros.size() - 1)
									.setValor(
											Float.parseFloat(corrigirVirgula(tokens[2 + k])));
					} else
						quadros.get(quadros.size() - 1).setValor(-1.0f);
				}
			}
		}

		return quadros;
	}

	private void lerCabecalho() {
		for (int i = 0; i < 5; i++) {
			scanner.nextLine();
		}
	}

	private String corrigirVirgula(String expressao) {
		char[] string = new char[expressao.length()];
		for (int i = 0; i < expressao.length() && expressao.charAt(i) != '\n'; i++) {
			if (expressao.charAt(i) == ',') {
				string[i] = '.';
			} else {
				string[i] = expressao.charAt(i);
			}
		}
		String expressaoCorrigida = new String(string);
		return expressaoCorrigida;
	}
}
