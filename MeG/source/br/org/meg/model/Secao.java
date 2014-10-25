package br.org.meg.model;

import br.org.meg.dao.UtilDAO;

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
		if (id < 1 || id > 21){
			throw new IllegalArgumentException("ID da seção inválido!");
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
