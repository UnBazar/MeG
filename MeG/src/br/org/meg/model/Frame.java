package org.meg.model;

public class Frame {
	private int id;
	private Description descricao;
	private int ano;
	private float valor;
	private State estado;
	private Section secao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return ano;
	}

	public void setYear(int ano) {
		this.ano = ano;
	}

	public float getValue() {
		return valor;
	}

	public void setValue(float valor) {
		this.valor = valor;
	}

	public State getState() {
		return estado;
	}

	public void setState(State estado) {
		this.estado = estado;
	}

	public Section getSection() {
		return secao;
	}

	public void setSection(Section secao) {
		this.secao = secao;
	}

	public Description getDescription() {
		return descricao;
	}

	public void setDescription(Description titulo) {
		this.descricao = titulo;
	}
	
}
