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
		this.url = ParserTest.class.getProtectionDomain().getCodeSource().getLocation()+
				"util"+File.separator+"dados-teste"+File.separator+"dados_teste.csv";
		this.url = url.replaceAll("file:", "");
		this.url = url.replaceAll("target/classes/", "");
	}

	@Test
	public void testValidatesNumerOfLinesShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url, 27, 4, 2006, 2012);
		this.parser.validatesLinesQuantity(4);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidatesNumerOfLinesShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 5, 2006, 2012);
		this.parser.validatesLinesQuantity(4);
	}

	@Test
	public void testValidatesYearShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validatesYear(2006, 2012);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidateYearShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validatesYear(2006, 2011);
	}
	

	@Test
	public void testValidatesSectionShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validatesSector("A Agricultura, pecuária, produção florestal, pesca e aquicultura");
		this.parser.validatesSector("B Indústrias extrativas");
		this.parser.validatesSector("C Indústrias de transformação");
		this.parser.validatesSector("D Eletricidade e gás");
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidatesSectionShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,27, 4, 2006, 2012);
		this.parser.validatesSector("E Construção");
	}
	
	@Test
	public void testPersist() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.persist();
	}

}
