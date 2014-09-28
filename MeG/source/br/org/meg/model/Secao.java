package br.org.meg.model;

public class Secao {
	private int id;
	private String nome;
	
	public Secao(int id) {
		if (id > 4 || id < 0){
			throw new IllegalArgumentException("ID da descrição inválido!");
		}
		this.id = id;
		switch (id){
		case 0:
			this.setNome("Agricultura, pecuária, produção florestal, pesca e aquicultura");
			break;
		case 1:
			this.setNome("Indústrias extrativas");
			break;
		case 2:
			this.setNome("Indústrias de transformação");
			break;
		case 3:
			this.setNome("Eletricidade e gás");
			break;
		case 4:
			this.setNome("Água, esgoto, atividades de gestão de resíduos e descontaminação");
			break;
		case 5:
			this.setNome("Construção");
			break;
		case 6:
			this.setNome("Comércio; reparação de veículos automotores e motocicletas");
			break;
		case 7:
			this.setNome("Transporte, armazenagem e correio");
			break;
		case 8:
			this.setNome("Alojamento e alimentação");
			break;
		case 9:
			this.setNome("Informação e comunicação");
			break;
		case 10:
			this.setNome("Atividades financeiras, de seguros e serviços relacionados");
			break;
		case 11:
			this.setNome("Atividades imobiliárias");
			break;
		case 12:
			this.setNome("Atividades profissionais, científicas e técnicas");
			break;
		case 13:
			this.setNome("Atividades administrativas e serviços complementares");
			break;
		case 14:
			this.setNome("Administração pública, defesa e seguridade social");
			break;
		case 15:
			this.setNome("Educação");
			break;
		case 16:
			this.setNome("Saúde humana e serviços sociais");
			break;
		case 17:
			this.setNome("Artes, cultura, esporte e recreação");
			break;
		case 18: 
			this.setNome("Outras atividades de serviços");
			break;
		case 19:
			this.setNome("Serviços domésticos");
			break;
		case 20: 
			this.setNome("Organismos internacionais e outras instituições extraterritoriais");
			break;
		}
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
