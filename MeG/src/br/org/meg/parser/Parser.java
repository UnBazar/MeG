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

	/*
	 * This method is responsible for the validating of the number of 
	 * lines of a file based on the number of sectors to be read. It is
	 * used primarily during a file upload. It identifies if the number 
	 * of lines matches the actual number of lines of the file, if not, 
	 * it throws a FileUploadException.
	 */
	@SuppressWarnings("resource")
	public void validatesLinesQuantity(int sectorsQuantity)
			throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		int i = 0;
		final int numberOfStates = 27;
		final int numberOfExtraLines = 6;
		this.readFileHeader();

		do {
			i++;
			scanner.nextLine();
		} while (scanner.hasNext());

		// checks if the whole file was read correctly
		if ((numberOfStates * this.sectionsQuantity + numberOfExtraLines) != i) {
			throw new UploadArquivoException(
					"Input data does not match the required file format! (Invalid number of lines)");
		}

		scanner.close();
	}
	
	/*
	 * This method is responsible for the validating of the sector sent as argument. It is
	 * used primarily during a file upload. It identifies if a sector exists in the file, 
	 * if not, it throws a FileUploadException.
	 */
	@SuppressWarnings("resource")
	public void validatesSector(String sector) throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		this.readFileHeader();
		boolean containsSector = false;
		final int minimumTokenLength = 2;
		String token;

		while(scanner.hasNext()) {
			token = scanner.next();
			// checks if the token read is not an empty token
			if(token.length() > minimumTokenLength) {
				// removes "" characters from the token read (Example: "token" becomes token)
				token = token.substring(1, token.length() - 1);
			}
			
			if(token.equals(sector)) {
				containsSector = true;
				break;
			}
		}
		this.scanner.close();
		if(!containsSector) {
			throw new UploadArquivoException(
					"Input data does not match the required file format! (Sector not found)");
		}
	}

	/*
	 * 
	 */
	@SuppressWarnings("resource")
	public void validatesYear(int initialYear, int finalYear)
			throws FileNotFoundException {
		final int numberOfSectors = 5;
		final int extraColumns = 2;
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		this.readFileHeader();
		String[] tokens = scanner.nextLine().split(";");

		if (tokens.length != ((finalYear - initialYear + 1) * numberOfSectors) + extraColumns) {
			throw new UploadArquivoException(
					"Input data does not match the required file format! (Year)");
		}

		scanner.close();
	}

	@SuppressWarnings("resource")
	public void persist() throws FileNotFoundException {
		scanner = new Scanner(new FileReader(this.filePath)).useDelimiter(";");
		ArrayList<Frame> frames = this.readStates();
		FrameDAO dao = new FrameDAO();
		for (Frame frame : frames) {
			dao.addFrame(frame);
		}
		scanner.close();
	}

	private ArrayList<Frame> readStates() {
		ArrayList<Frame> quadros = new ArrayList<>();
		State state;
		Section section;
		String[] tokens;
		int timeInterval = this.finalYear - this.initialYear + 1;
		this.readFileHeader();

		for (int j = 0; j < this.statesQuantity; j++) {
			for (int i = 0; i < this.sectionsQuantity; i++) {
				tokens = scanner.nextLine().split(";");
				state = new State();
				state.setNome(tokens[0].substring(1, tokens[0].length() - 1));
				section = new Section();
				section.setNome(tokens[1].substring(3, tokens[1].length() - 1));
				for (int k = 0; k < 5 * timeInterval; k++) {
					quadros.add(new Frame());
					quadros.get(quadros.size() - 1).setState(state);
					quadros.get(quadros.size() - 1).setSection(section);
					quadros.get(quadros.size() - 1).setYear(
							this.initialYear + k % timeInterval);
					Description descricao = new Description();
					descricao.setId(1 + k / timeInterval);
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

	private String correctCommasOnFloatingPointValues(String expression) {
		char[] string = new char[expression.length()];
		for (int i = 0; i < expression.length() && expression.charAt(i) != '\n'; i++) {
			if (expression.charAt(i) == ',') {
				string[i] = '.';
			} else {
				string[i] = expression.charAt(i);
			}
		}
		String correctedExpression = new String(string);
		return correctedExpression;
	}
}
