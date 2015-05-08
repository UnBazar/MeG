package org.meg.model;

import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Description {
	private int id;
	private String nome;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.DESCRIPTION);

	public Description() {
		// Default constructor
	}
	
	public Description(int id){
		setId(id);
	}
	
	public Description(String titulo){
		setNome(titulo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		// fixed interval id
		if (id >= 1 && id <= 5) {
			this.id = id;
			this.nome = DAO.getNameFromID(id);
		} else {
			throw new SystemBreakException("An invalid id was inserted in " 
						+ this.getClass().getName());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		this.id = DAO.getIDFromName(nome);
	}
}
