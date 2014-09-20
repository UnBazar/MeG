package br.org.meg.dao;

import java.sql.Connection;
import java.util.List;

import br.org.meg.model.Quadro;

public class QuadroDAO {
	private Connection connection;
	
	public QuadroDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adicionar(Quadro quadro) {
		
	}
	
	public void remover(Quadro quadro) {
		
	}
	
	public List<Quadro> pegaLista() {
		return null;
	}
}
