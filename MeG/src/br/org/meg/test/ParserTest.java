package org.meg.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import org.meg.exception.UploadArquivoException;
import org.meg.parser.Parser;

public class ParserTest {

	private Parser parser;
	private String url;
	
	@Before
	public void setUp(){
		int SAMPLE_STATES_QUANTITY = 27;
		int SAMPLE_SECTIONS_QUANTITY = 4;
		int SAMPLE_INITIAL_YEAR = 2006;
		int SAMPLE_FINAL_YEAR = 2012;
		this.url = ParserTest.class.getProtectionDomain().getCodeSource().getLocation()+
				"util"+File.separator+"dados-teste"+File.separator+"dados_teste.csv";
		this.url = url.replaceAll("file:", "");
		this.url = url.replaceAll("target/classes/", "");
	}

	@Test
	public void testValidatesNumerOfLinesShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url, SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesLinesQuantity(SAMPLE_SECTIONS_QUANTITY);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidatesNumerOfLinesShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				SAMPLE_STATES_QUANTITY, 5, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesLinesQuantity(SAMPLE_SECTIONS_QUANTITY);
	}

	@Test
	public void testValidatesYearShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesYear(SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidateYearShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesYear(SAMPLE_INITIAL_YEAR, 2011);
	}
	

	@Test
	public void testValidatesSectionShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesSector("A Agricultura, pecuária, produção florestal, pesca e aquicultura");
		this.parser.validatesSector("B Indústrias extrativas");
		this.parser.validatesSector("C Indústrias de transformação");
		this.parser.validatesSector("D Eletricidade e gás");
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidatesSectionShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.validatesSector("E Construção");
	}
	
	@Test
	public void testPersist() throws FileNotFoundException {
		this.parser = new Parser(url,
				SAMPLE_STATES_QUANTITY, SAMPLE_SECTIONS_QUANTITY, SAMPLE_INITIAL_YEAR, SAMPLE_FINAL_YEAR);
		this.parser.persist();
	}

}
