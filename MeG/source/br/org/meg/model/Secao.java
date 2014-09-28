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
		if (nome.equals(null)) throw new IllegalArgumentException("Nome da seção inválido!");
		this.nome = nome;
		switch (nome) {
			case "Agricultura, pecuária, produção florestal, pesca e aquicultura":
				this.setId(1);
				break;
			case "Indústrias extrativas":
				this.setId(2);
				break;
			case "Indústrias de transformação":
				this.setId(3);
				break;
			case "Eletricidade e gás":
				this.setId(4);
				break;
			case "Construção":
				this.setId(5);
				break;
			case "Alojamento e alimentação":
				this.setId(6);
		}
	}	
}
