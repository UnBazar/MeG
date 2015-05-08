package org.meg.model;

import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Section {
	private int id;
	private String nome;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.SECTION);
	
	public Section() {
		// Default Constructor
	}
	
	public Section(int id) {
		this.setId(id);
	}
	
	public Section(String nome){
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		// fixed interval id
		if (id >= 1 && id <= 21) {
			this.id = id;
			this.nome = DAO.getNameFromID(id);
		} else {
			throw new SystemBreakException("An invalid id was inserted in" 
					+ this.getClass().getName());
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome;
			this.id = DAO.getIDFromName(nome);
		} else {
			throw new IllegalArgumentException("Name of section is invalid!");
		}
	}	
}
