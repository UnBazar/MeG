package org.meg.estilo;

/**
 * Responsabilidades e conferencias dessa classe.
 * 
 * @author Phelipe Wener
 * @since 1.0
 * 
 */
public class FolhaEstilo {
	private final int INVALIDNUMBER = -1;
	/* Atributos globais devem ser comentados assim*/
	private String foo;
	/**
	 * Uso de anotações, e precedencia de comentários para com as anotações
	 */
	@Deprecated
	public FolhaEstilo() {
		
	}
	/**
	 * @param daa comentar todos parametros
	 */
	public FolhaEstilo(String daa) {
		//Manter as referencias claras
		this.foo = daa;
	}
	/**
	 * Essa classe soma dois inteiros passados por argumento.
	 * 
	 * @param numeroA fator para soma
	 * @param numeroB fator para soma
	 * 
	 * Preterivelmente com os links em cada referencia na documentacao
	 * 
	 * @return {@link Integer} fator de soma
	 */
	private Integer somaPositivos(Integer numeroA, Integer numeroB){
		//Comentarios tecnicos dentro de metodos devem ser feitos assim caso não excedam 2 linhas.
		/*
		 * Nesse caso usar 
		 * esse tipo de comentario.
		 */
		if(numeroA >= 0 && numeroB >= 0){
			return numeroA + numeroB;
		}else{
			return INVALIDNUMBER;
		}
	}
	/**
	 * Nesse exemplo se mostra como tratar multiplas excecoes.
	 * Toda excecao deve ser documentada.
	 * 
	 * @return String foo modificada pelo metodo toUppercase();
	 * 
	 * @exception {@link NullPointerException} Quando o foo tem um valor nulo essa excecao é chamada
	 * @exception {@link RuntimeException} Excecao onde os casos de ocorrencia sao desconhecidos, deixar em branco.
	 */
	private String obterFooMaiusculo() throws NullPointerException, RuntimeException{
		try{
			return this.foo.toUpperCase();
		}catch(NullPointerException nullPointerException){
			throw new NullPointerException("String foo passada vazia");
		}catch (RuntimeException runtimeException) {
			throw new RuntimeException("Ao tentar converter para foo");
		}
	}
}