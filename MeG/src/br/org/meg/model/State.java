package org.meg.model;

import java.io.Serializable;

import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.dao.UtilDAO;
import org.meg.exception.SystemBreakException;

public class State implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String sigla;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.STATE);
	
	public State() {
	
	}

	public State(int idEstado) {
		setId(idEstado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		// fixed interval id
		if (id >= 1 && id <= 27){		
			this.id = id;
			// Deprecated
			UtilDAO utilDAO = new UtilDAO();
			this.sigla = utilDAO.getSiglaEstado(id);
			
			this.nome = DAO.getNameFromID(id);
		}else{
			throw new SystemBreakException("An invalid id of state was inserted!");
		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome;
			this.id = DAO.getIDFromName(nome);
		}else{
			throw new IllegalArgumentException("Name of state is null");
		}
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
