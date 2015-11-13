package org.meg.model;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Section implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.SECTION);
	
	
	// The interval between IDs from Section must be between 1 and 21
	static final int MIN_ID_SECTION = 1;
	static final int MAX_ID_SECTION = 21;
	
	Logger logger = Logger.getLogger("Section");

	
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
		// Fixed interval id
		if (id >= MIN_ID_SECTION && id <= MAX_ID_SECTION) {
			this.id = id;
			this.name = DAO.getNameFromID(id);
		} else {
			logger.error("Invalid id inserted.");
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
			logger.error("NULL name.");
			throw new IllegalArgumentException("Name of section is invalid!");
		}
	}	
}
