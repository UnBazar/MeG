package br.org.meg.model;

public class Descricao {
	private int id;
	private String nome;

	public Descricao(int id){
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 5 || id < 1) {
			throw new IllegalArgumentException("ID da descrição inválido!");
		}
		this.id = id;
		switch(id) {
			case 1:
				this.setNome("Número de empresas e outras organizações (Unidades)");
				break;
			case 2:
				this.setNome("Pessoal ocupado total (Pessoas)");
				break;
			case 3:
				this.setNome("Pessoal ocupado assalariado (Pessoas)");
				break;
			case 4:
				this.setNome("Salários e outras remunerações (Mil Reais)");
				break;
			case 5:
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
