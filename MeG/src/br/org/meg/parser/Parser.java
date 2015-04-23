package org.meg.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.meg.dao.FrameDAO;
import org.meg.exception.UploadArquivoException;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

public class Parser {
	private Scanner scanner;
	private int initialYear;
	private int finalYear;
	private int statesQuantity;
	private int sectionsQuantity;
	private String filePath;

	public Parser(String filePath, int statesQuantity, int sectionsQuantity,
			int initialYear, int finalYear) {

		/*
		 *  checks if final year is less than or equal initial year or quantity
		 *  of states or sections are less than or equal zero
		 */
		if (finalYear <= initialYear || statesQuantity <= 0
				|| sectionsQuantity <= 0) {
			throw new IllegalArgumentException("Parser arguments invalid!");
		}
		
		this.filePath = filePath;
		this.statesQuantity = statesQuantity;
		this.sectionsQuantity = sectionsQuantity;
		this.initialYear = initialYear;
		this.finalYear = finalYear;
	}

	@SuppressWarnings("resource")
	public void validatesLinesQuantity(int sectionsQuantity)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		int i = 0;
		this.readFileHeader();

		do {
			i++;
			scanner.nextLine();
		} while (scanner.hasNext());

		if ((27 * this.sectionsQuantity + 6) != i) {
			throw new UploadArquivoException(
					"Dados de entrada incompatíveis com o arquivo! (Quantidade de linhas)");
		}

		scanner.close();
	}

	@SuppressWarnings("resource")
	public void validatesSection(String secao) throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		this.readFileHeader();
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
	public void validatesYear(int anoInicial, int anoFinal)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		this.readFileHeader();
		String[] tokens = scanner.nextLine().split(";");

		if (tokens.length != ((anoFinal - anoInicial + 1) * 5) + 2) {
			throw new UploadArquivoException(
					"Dados de entrada incompatíveis com o arquivo! (Ano)");
		}

		scanner.close();
	}

	@SuppressWarnings("resource")
	public void persist() throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		ArrayList<Frame> quadros = this.readStates();
		FrameDAO dao = new FrameDAO();
		for (Frame quadro : quadros) {
			dao.addFrame(quadro);
		}
		scanner.close();
	}

	private ArrayList<Frame> readStates() {
		ArrayList<Frame> quadros = new ArrayList<>();
		State estado;
		Section secao;
		String[] tokens;
		int tempo = this.finalYear - this.initialYear + 1;
		this.readFileHeader();

		for (int j = 0; j < this.statesQuantity; j++) {
			for (int i = 0; i < this.sectionsQuantity; i++) {
				tokens = scanner.nextLine().split(";");
				estado = new State();
				estado.setNome(tokens[0].substring(1, tokens[0].length() - 1));
				secao = new Section();
				secao.setNome(tokens[1].substring(3, tokens[1].length() - 1));
				for (int k = 0; k < 5 * tempo; k++) {
					quadros.add(new Frame());
					quadros.get(quadros.size() - 1).setState(estado);
					quadros.get(quadros.size() - 1).setSection(secao);
					quadros.get(quadros.size() - 1).setYear(
							this.initialYear + k % tempo);
					Description descricao = new Description();
					descricao.setId(1 + k / tempo);
					quadros.get(quadros.size() - 1).setDescription(descricao);
					if (!tokens[2 + k].equals("-")
							&& !tokens[2 + k].equals("X")) {
						if (k < 28)
							quadros.get(quadros.size() - 1).setValue(
									Float.parseFloat(tokens[2 + k]));
						else
							quadros.get(quadros.size() - 1)
									.setValue(
											Float.parseFloat(correctCommasOnFloatingPointValues(tokens[2 + k])));
					} else
						quadros.get(quadros.size() - 1).setValue(-1.0f);
				}
			}
		}

		return quadros;
	}

	private void readFileHeader() {
		final int linesQuantityOfHeader = 5;
		for (int i = 0; i < linesQuantityOfHeader; i++) {
			scanner.nextLine();
		}
	}

	private String correctCommasOnFloatingPointValues(String expressao) {
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
