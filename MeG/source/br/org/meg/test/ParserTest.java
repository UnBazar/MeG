package br.org.meg.test;

import java.io.FileNotFoundException;

import org.junit.Test;

import br.org.meg.exception.UploadArquivoException;
import br.org.meg.parser.Parser;

public class ParserTest {

	private Parser parser;

	@Test
	public void testValidarQuantidadeDeLinhasShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.validarQuantidadeDeLinhas(4);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarQuantidadeDeLinhasShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 5, 2006, 2012);
		this.parser.validarQuantidadeDeLinhas(4);
	}

	@Test
	public void testValidarAnoShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.validarAno(2006, 2012);
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarAnoShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.validarAno(2006, 2011);
	}
	

	@Test
	public void testValidarSecaoShouldBeCorrect() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.validarSecao("A Agricultura, pecuária, produção florestal, pesca e aquicultura");
		this.parser.validarSecao("B Indústrias extrativas");
		this.parser.validarSecao("C Indústrias de transformação");
		this.parser.validarSecao("D Eletricidade e gás");
	}
	
	@Test(expected = UploadArquivoException.class)
	public void testValidarSecaoShouldThrowException() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.validarSecao("E Construção");
	}
	
	@Test
	public void testPersist() throws FileNotFoundException {
		this.parser = new Parser("/home/pedro/workspace/MeG/MeG/util/dados-teste/dados_teste.csv",
				27, 4, 2006, 2012);
		this.parser.persist();
	}

}
