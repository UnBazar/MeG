package org.meg.model;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.dao.UtilDAO;
import org.meg.exception.SystemBreakException;

public class State implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String stateAbbreviation;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.STATE);
	
	// The interval between IDs from State must be between 1 and 27
	static final int MIN_ID_STATE = 1;
	static final int MAX_ID_STATE = 27;
	
	Logger logger = Logger.getLogger("State");

	
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
		if (id >= MIN_ID_STATE && id <= MAX_ID_STATE){		
			this.id = id;
			// Deprecated
			UtilDAO utilDAO = new UtilDAO();
			this.stateAbbreviation = utilDAO.getStateAbbreviation(id);
			
			this.name = DAO.getNameFromID(id);
		}else{
			logger.error("An invalid id of state was inserted!");
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
			logger.error("Name of state is null.");
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
