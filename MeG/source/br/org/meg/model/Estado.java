package br.org.meg.model;

public class Estado {
	private int id;
	private String nome;
	private String sigla;
	
	public Estado() {
	
	}
	
	public Estado(int estado_id) {
		switch (estado_id) {
			case 1:
				setNome("Acre");
				break;
			case 2:
				setNome("Alagoas");
				break;
			case 3:
				setNome("Amazonas");
				break;
			case 4:
				setNome("Amapá");
				break;
			case 5:
				setNome("Bahia");
				break;
			case 6:
				setNome("Ceará");
				break;
			case 7:
				setNome("Distrito Federal");
				break;
			case 8:
				setNome("Espírito Santo");
				break;
			case 9:
				setNome("Goiás");
				break;
			case 10:
				setNome("Maranhão");
				break;
			case 11:
				setNome("Minas Gerais");
				break;
			case 12:
				setNome("Mato Grosso do Sul");
				break;
			case 13:
				setNome("Mato Grosso");
				break;
			case 14:
				setNome("Pará");
				break;
			case 15:
				setNome("Paraíba");
				break;
			case 16:
				setNome("Pernambuco");
				break;
			case 17:
				setNome("Piauí");
				break;
			case 18:
				setNome("Paraná");
				break;
			case 19:
				setNome("Rio de Janeiro");
				break;
			case 20:
				setNome("Rio Grande do Norte");
				break;
			case 21:
				setNome("Rondônia");
				break;
			case 22:
				setNome("Roraima");
				break;
			case 23:
				setNome("Rio Grande do Sul");
				break;
			case 24:
				setNome("Santa Catarina");
				break;
			case 25:
				setNome("Sergipe");
				break;
			case 26:
				setNome("São Paulo");
				break;
			case 27:
				setNome("Tocantins");
				break;
			}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 1 || id > 27) throw new IllegalArgumentException("ID do estado inválido!");
		this.id = id;
		switch (id) {
			case 1:
				setSigla("AC");
				this.nome = "Acre";
				break;
			case 2:
				setSigla("AL");
				this.nome = "Alagoas";
				break;
			case 3:
				setSigla("AM");
				this.nome = "Amazonas";
				break;
			case 4:
				setSigla("AP");
				this.nome = "Amapá";
				break;
			case 5:
				setSigla("BA");
				this.nome = "Bahia";
				break;
			case 6:
				setSigla("CE");
				this.nome = "Ceará";
				break;
			case 7:
				setSigla("DF");
				this.nome = "Distrito Federal";
				break;
			case 8:
				setSigla("ES");
				this.nome = "Espírito Santo";
				break;
			case 9:
				setSigla("GO");
				this.nome = "Goiás";
				break;
			case 10:
				setSigla("MA");
				this.nome = "Maranhão";
				break;
			case 11:
				setSigla("MG");
				this.nome = "Minas Gerais";
				break;
			case 12:
				setSigla("MS");
				this.nome = "Mato Grosso do Sul";
				break;
			case 13:
				setSigla("MT");
				this.nome = "Mato Grosso";
				break;
			case 14:
				setSigla("PA");
				this.nome = "Pará";
				break;
			case 15:
				setSigla("PB");
				this.nome = "Paraíba";
				break;
			case 16:
				setSigla("PE");
				this.nome = "Pernambuco";
				break;
			case 17:
				setSigla("PI");
				this.nome = "Piauí";
				break;
			case 18:
				setSigla("PR");
				this.nome = "Paraná";
				break;
			case 19:
				setSigla("RJ");
				this.nome = "Rio de Janeiro";
				break;
			case 20:
				setSigla("RN");
				this.nome = "Rio Grande do Norte";
				break;
			case 21:
				setSigla("RO");
				this.nome = "Rondônia";
				break;
			case 22:
				setSigla("RR");
				this.nome = "Roraima";
				break;
			case 23:
				setSigla("RS");
				this.nome = "Rio Grande do Sul";
				break;
			case 24:
				setSigla("SC");
				this.nome = "Santa Catarina";
				break;
			case 25:
				setSigla("SE");
				this.nome = "Sergipe";
				break;
			case 26:
				setSigla("SP");
				this.nome = "São Paulo";
				break;
			case 27:
				setSigla("TO");
				this.nome = "Tocantins";
				break;				
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null) throw new IllegalArgumentException("Nome do estado inválido!");
		this.nome = nome;
		switch (nome) {
			case "Acre":
				setSigla("AC");
				this.id = 1;
				break;
			case "Alagoas":
				setSigla("AL");
				this.id = 2;
				break;
			case "Amazonas":
				setSigla("AM");
				this.id = 3;
				break;
			case "Amapá":
				setSigla("AP");
				this.id = 4;
				break;
			case "Bahia":
				setSigla("BA");
				this.id = 5;
				break;
			case "Ceará":
				setSigla("CE");
				this.id = 6;
				break;
			case "Distrito Federal":
				setSigla("DF");
				this.id = 7;
				break;
			case "Espírito Santo":
				setSigla("ES");
				this.id = 8;
				break;
			case "Goiás":
				setSigla("GO");
				this.id = 9;
				break;
			case "Maranhão":
				setSigla("MA");
				this.id = 10;
				break;
			case "Minas Gerais":
				setSigla("MG");
				this.id = 11;
				break;
			case "Mato Grosso do Sul":
				setSigla("MS");
				this.id = 12;
				break;
			case "Mato Grosso":
				setSigla("MT");
				this.id = 13;
				break;
			case "Pará":
				setSigla("PA");
				this.id = 14;
				break;
			case "Paraíba":
				setSigla("PB");
				this.id = 15;
				break;
			case "Pernambuco":
				setSigla("PE");
				this.id = 16;
				break;
			case "Piauí":
				setSigla("PI");
				this.id = 17;
				break;
			case "Paraná":
				setSigla("PR");
				this.id = 18;
				break;
			case "Rio de Janeiro":
				setSigla("RJ");
				this.id = 19;
				break;
			case "Rio Grande do Norte":
				setSigla("RN");
				this.id = 20;
				break;
			case "Rondônia":
				setSigla("RO");
				this.id = 21;
				break;
			case "Roraima":
				setSigla("RR");
				this.id = 22;
				break;
			case "Rio Grande do Sul":
				setSigla("RS");
				this.id = 23;
				break;
			case "Santa Catarina":
				setSigla("SC");
				this.id = 24;
				break;
			case "Sergipe":
				setSigla("SE");
				this.id = 25;
				break;
			case "São Paulo":
				setSigla("SP");
				this.id = 26;
				break;
			case "Tocantins":
				setSigla("TO");
				this.id = 27;
				break;
		}
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
