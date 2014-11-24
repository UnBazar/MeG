package org.meg.model;

import org.meg.dao.UtilDAO;
import org.meg.exception.QuebraSistemaException;

public class Secao {
	private int id;
	private String nome;
	
	public Secao() {
		
	}
	
	public Secao(int id) {
		this.setId(id);
	}
	
	public Secao(String nome){
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		if (id < 1 || id > 21) {
			throw new QuebraSistemaException("Um id invalido de Secao foi inserido!");
		}
		UtilDAO dao = new UtilDAO();
		this.id = id;
		nome = dao.getNomeSecao(id);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (nome == null){
			throw new IllegalArgumentException("Nome da seção inválido!");
		}
		UtilDAO dao = new UtilDAO();
		this.nome = nome;
		id = dao.getIdSecao(nome);
	}	
}
