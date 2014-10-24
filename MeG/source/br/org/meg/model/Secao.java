package br.org.meg.model;

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
		this.id = id;
		switch (id){
			case 1:
				this.nome = "Agricultura, pecuária, produção florestal, pesca e aquicultura";
				break;
			case 2:
				this.nome = "Indústrias extrativas";
				break;
			case 3:
				this.nome = "Indústrias de transformação";
				break;
			case 4:
				this.nome = "Eletricidade e gás";
				break;
			case 5:
				this.nome = "Água, esgoto, atividades de gestão de resíduos e descontaminação";
				break;
			case 6:
				this.nome = "Construção";
				break;
			case 7:
				this.nome = "Comércio; reparação de veículos automotores e motocicletas";
				break;
			case 8:
				this.nome = "Transporte, armazenagem e correio";
				break;
			case 9:
				this.nome = "Alojamento e alimentação";
				break;
			case 10:
				this.nome = "Informação e comunicação";
				break;
			case 11:
				this.nome = "Atividades financeiras, de seguros e serviços relacionados";
				break;
			case 12:
				this.nome = "Atividades imobiliárias";
				break;
			case 13:
				this.nome = "Atividades profissionais, científicas e técnicas";
				break;
			case 14:
				this.nome = "Atividades administrativas e serviços complementares";
				break;
			case 15:
				this.nome = "Administração pública, defesa e seguridade social";
				break;
			case 16:
				this.nome = "Educação";
				break;
			case 17:
				this.nome = "Saúde humana e serviços sociais";
				break;
			case 18:
				this.nome = "Artes, cultura, esporte e recreação";
				break;
			case 19: 
				this.nome = "Outras atividades de serviços";
				break;
			case 20:
				this.nome = "Serviços domésticos";
				break;
			case 21: 
				this.nome = "Organismos internacionais e outras instituições extraterritoriais";
				break;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (nome == null){
			throw new IllegalArgumentException("Nome da seção inválido!");
		}
		this.nome = nome;
		switch (nome) {
			case "Agricultura, pecuária, produção florestal, pesca e aquicultura":
				this.id = 1;
				break;
			case "Indústrias extrativas":
				this.id = 2;
				break;
			case "Indústrias de transformação":
				this.id = 3;
				break;
			case "Eletricidade e gás":
				this.id = 4;
				break;
			case "Água, esgoto, atividades de gestão de resíduos e descontaminação":
				this.id = 5;
				break;
			case "Construção":
				this.id = 6;
				break;
			case "Comércio, reparação de veículos automotores e motocicletas":
				this.id = 7;
				break;
			case "Transporte, armazenagem e correio":
				this.id = 8;
				break;
			case "Alojamento e alimentação":
				this.id = 9;
				break;
			case "Informação e comunicação":
				this.id = 10;
				break;
			case "Atividades financeiras, de seguros e serviços relacionados":
				this.id = 11;
				break;
			case "Atividades imobiliárias":
				this.id = 12;
				break;
			case "Atividades profissionais, científicas e técnicas":
				this.id = 13;
				break;
			case "Atividades administrativas e serviços complementares":
				this.id = 14;
				break;
			case "Administração pública, defesa e seguridade social":
				this.id = 15;
				break;
			case "Educação":
				this.id = 16;
				break;
			case "Saúde humana e serviços sociais":
				this.id = 17;
				break;
			case "Artes, cultura, esporte e recreação":
				this.id = 18;
				break;
			case "Outras atividades de serviços": 
				this.id = 19;
				break;
			case "Serviços domésticos":
				this.id = 20;
				break;
			case "Organismos internacionais e outras instituições extraterritoriais": 
				this.id = 21;
				break;	
		}
	}	
}
