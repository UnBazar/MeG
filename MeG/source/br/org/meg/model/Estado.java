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
				setNome("Amapá");
				break;
			case 4:
				setNome("Amazonas");
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
				setNome("Mato Grosso");
				break;
			case 12:
				setNome("Mato Grosso do Sul");
				break;
			case 13:
				setNome("Minas Gerais");
				break;
			case 14:
				setNome("Pará");
				break;
			case 15:
				setNome("Paraíba");
				break;
			case 16:
				setNome("Paraná");
				break;
			case 17:
				setNome("Pernambuco");
				break;
			case 18:
				setNome("Piauí");
				break;
			case 19:
				setNome("Rio de Janeiro");
				break;
			case 20:
				setNome("Rio Grande do Norte");
				break;
			case 21:
				setNome("Rio Grande do Sul");
				break;
			case 22:
				setNome("Rondônia");
				break;
			case 23:
				setNome("Roraima");
				break;
			case 24:
				setNome("Santa Catarina");
				break;
			case 25:
				setNome("São Paulo");
				break;
			case 26:
				setNome("Sergipe");
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
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.equals(null)) throw new IllegalArgumentException("Nome do estado invalido!");
		this.nome = nome;
		switch (nome) {
			case "Acre":
				setSigla("AC");
				setId(1);
				break;
			case "Alagoas":
				setSigla("AL");
				setId(2);
				break;
			case "Amapá":
				setSigla("AP");
				setId(3);
				break;
			case "Amazonas":
				setSigla("AM");
				setId(4);
				break;
			case "Bahia":
				setSigla("BA");
				setId(5);
				break;
			case "Ceará":
				setSigla("CE");
				setId(6);
				break;
			case "Distrito Federal":
				setSigla("DF");
				setId(7);
				break;
			case "Espírito Santo":
				setSigla("ES");
				setId(8);
				break;
			case "Goiás":
				setSigla("GO");
				setId(9);
				break;
			case "Maranhão":
				setSigla("MA");
				setId(10);
				break;
			case "Mato Grosso":
				setSigla("MT");
				setId(11);
				break;
			case "Mato Grosso do Sul":
				setSigla("MS");
				setId(12);
				break;
			case "Minas Gerais":
				setSigla("MG");
				setId(13);
				break;
			case "Pará":
				setSigla("PA");
				setId(14);
				break;
			case "Paraíba":
				setSigla("PB");
				setId(15);
				break;
			case "Paraná":
				setSigla("PR");
				setId(16);
				break;
			case "Pernambuco":
				setSigla("PE");
				setId(17);
				break;
			case "Piauí":
				setSigla("PI");
				setId(18);
				break;
			case "Rio de Janeiro":
				setSigla("RJ");
				setId(19);
				break;
			case "Rio Grande do Norte":
				setSigla("RN");
				setId(20);
				break;
			case "Rio Grande do Sul":
				setSigla("RS");
				setId(21);
				break;
			case "Rondônia":
				setSigla("RO");
				setId(22);
				break;
			case "Roraima":
				setSigla("RR");
				setId(23);
				break;
			case "Santa Catarina":
				setSigla("SC");
				setId(24);
				break;
			case "São Paulo":
				setSigla("SP");
				setId(25);
				break;
			case "Sergipe":
				setSigla("SE");
				setId(26);
				break;
			case "Tocantins":
				setSigla("TO");
				setId(27);
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
