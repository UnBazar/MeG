package org.meg.model;

public class Quadro {
	private int id;
	private Descricao descricao;
	private int ano;
	private float valor;
	private Estado estado;
	private Secao secao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao titulo) {
		this.descricao = titulo;
	}
	
}
