package org.meg.model;

import java.io.Serializable;

import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Section implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.SECTION);
	
	public Section() {
		// Default Constructor
	}
	
	public Section(int id) {
		this.setId(id);
	}
	
	public Section(String name){
		this.setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		// fixed interval id
		if (id >= 1 && id <= 21) {
			this.id = id;
			this.name = DAO.getNameFromID(id);
		} else {
			throw new SystemBreakException("An invalid id was inserted in" 
					+ this.getClass().getName());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name != null) {
			this.name = name;
			this.id = DAO.getIDFromName(name);
		} else {
			throw new IllegalArgumentException("Name of section is invalid!");
		}
	}	
}
