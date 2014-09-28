package br.org.meg.model;

public class Descricao {
	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 4 || id < 0) {
			throw new IllegalArgumentException("ID da descrição inválido!");
		}
		this.id = id;
		switch(id) {
			case 0:
				this.setNome("Número de empresas e outras organizações (Unidades)");
				break;
			case 1:
				this.setNome("Pessoal ocupado total (Pessoas)");
				break;
			case 2:
				this.setNome("Pessoal ocupado assalariado (Pessoas)");
				break;
			case 3:
				this.setNome("Salários e outras remunerações (Mil Reais)");
				break;
			case 4:
				this.setNome("Salário médio mensal (Salários mínimos)");
				break;	
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		
	}

}
