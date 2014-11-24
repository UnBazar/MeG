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
	public void testValidarQuantidadeDeLinhasShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url, 27, 4, 2006, 2012);
		this.parser.validarQuantidadeDeLinhas(4);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarQuantidadeDeLinhasShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 5, 2006, 2012);
		this.parser.validarQuantidadeDeLinhas(4);
	}

	@Test
	public void testValidarAnoShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validarAno(2006, 2012);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarAnoShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validarAno(2006, 2011);
	}
	

	@Test
	public void testValidarSecaoShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.validarSecao("A Agricultura, pecuária, produção florestal, pesca e aquicultura");
		this.parser.validarSecao("B Indústrias extrativas");
		this.parser.validarSecao("C Indústrias de transformação");
		this.parser.validarSecao("D Eletricidade e gás");
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarSecaoShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser(url,27, 4, 2006, 2012);
		this.parser.validarSecao("E Construção");
	}
	
	@Test
	public void testPersist() throws FileNotFoundException {
		this.parser = new Parser(url,
				27, 4, 2006, 2012);
		this.parser.persist();
	}

}
