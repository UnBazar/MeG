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
	private String name;
	private String stateAbbreviation;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.STATE);
	
	public State() {
	
	}

	public State(int stateId) {
		setId(stateId);
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
			this.stateAbbreviation = utilDAO.getStateAbbreviation(id);
			
			this.name = DAO.getNameFromID(id);
		}else{
			throw new SystemBreakException("An invalid id of state was inserted!");
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
			this.id = DAO.getIDFromName(name);
		}else{
			throw new IllegalArgumentException("Name of state is null");
		}
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

}
