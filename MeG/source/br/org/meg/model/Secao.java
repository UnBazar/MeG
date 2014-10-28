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
		if (id < 1 || id > 21) throw new IllegalArgumentException("ID da seção inválido!");
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
				this.nome = "Comércio, reparação de veículos automotores e motocicletas";
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
