package org.meg.model;

import java.io.Serializable;

import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Description implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.DESCRIPTION);

	public Description() {
		// Default constructor
	}
	
	public Description(int id){
		setId(id);
	}
	
	public Description(String content){
		setContent(content);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		// fixed interval id
		if (id >= 1 && id <= 5) {
			this.id = id;
			this.content = DAO.getNameFromID(id);
		} else {
			throw new SystemBreakException("An invalid id was inserted in " 
						+ this.getClass().getName());
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.id = DAO.getIDFromName(content);
	}
}
