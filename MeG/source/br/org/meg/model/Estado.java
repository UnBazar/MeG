package br.org.meg.model;

public class Estado {
	private int id;
	private String nome;
	private String sigla;

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
				setId(0);
				break;
			case "Alagoas":
				setSigla("AL");
				setId(1);
				break;
			case "Amapá":
				setSigla("AP");
				setId(2);
				break;
			case "Amazonas":
				setSigla("AM");
				setId(3);
				break;
			case "Bahia":
				setSigla("BA");
				setId(4);
				break;
			case "Ceará":
				setSigla("CE");
				setId(5);
				break;
			case "Distrito Federal":
				setSigla("DF");
				setId(6);
				break;
			case "Espírito Santo":
				setSigla("ES");
				setId(7);
				break;
			case "Goiás":
				setSigla("GO");
				setId(8);
				break;
			case "Maranhão":
				setSigla("MA");
				setId(9);
				break;
			case "Mato Grosso":
				setSigla("MT");
				setId(10);
				break;
			case "Mato Grosso do Sul":
				setSigla("MS");
				setId(11);
				break;
			case "Minas Gerais":
				setSigla("MG");
				setId(12);
				break;
			case "Pará":
				setSigla("PA");
				setId(13);
				break;
			case "Paraíba":
				setSigla("PB");
				setId(14);
				break;
			case "Paraná":
				setSigla("PR");
				setId(15);
				break;
			case "Pernambuco":
				setSigla("PE");
				setId(16);
				break;
			case "Piauí":
				setSigla("PI");
				setId(17);
				break;
			case "Rio de Janeiro":
				setSigla("RJ");
				setId(18);
				break;
			case "Rio Grande do Norte":
				setSigla("RN");
				setId(19);
				break;
			case "Rio Grande do Sul":
				setSigla("RS");
				setId(20);
				break;
			case "Rondônia":
				setSigla("RO");
				setId(21);
				break;
			case "Roraima":
				setSigla("RR");
				setId(22);
				break;
			case "Santa Catarina":
				setSigla("SC");
				setId(23);
				break;
			case "São Paulo":
				setSigla("SP");
				setId(24);
				break;
			case "Sergipe":
				setSigla("SE");
				setId(25);
				break;
			case "Tocantins":
				setSigla("TO");
				setId(26);
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
