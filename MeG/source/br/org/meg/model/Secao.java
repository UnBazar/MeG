package br.org.meg.model;

public class Secao {
	private int id;
	private String nome;
	
	public Secao() {
	
	}
	
	public Secao(String nome){
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
}
