package org.meg.model;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.meg.dao.EnumTable;
import org.meg.dao.GenericModelDAO;
import org.meg.exception.SystemBreakException;

public class Description implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private final GenericModelDAO DAO = new GenericModelDAO(EnumTable.DESCRIPTION);

	// The id from description must be between 1 & 5
	static final int MIN_ID_DESC = 1;
	static final int MAX_ID_DESC = 5;
	
	Logger logger = Logger.getLogger("Description");

	
	public Description() {
		// Default constructor
	}
	
	/**
	 * Description constructor that sets id.
	 * 
	 * @param id access his place in the database.
	 */
	public Description(int id){
		setId(id);
	}
	
	/**
	 * Description constructor that sets Content.
	 * 
	 * @param content access the content of the description.  
	 */
	public Description(String content){
		setContent(content);
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Setting the ID and getting the content from it at DAO.
	 * 
	 * @param id his placement in the database.
	 * 
	 * @exception SystemBreakException if the function is trying to
	 *			 get something that is not in the database the system.
	 */
	public void setId(int id) {
		// Fixed interval id
		if (id >= MIN_ID_DESC && id <= MAX_ID_DESC) {
			this.id = id;
			this.content = DAO.getNameFromID(id);
		} else {
			logger.error("Invalid id inserted.");
			throw new SystemBreakException("An invalid id was inserted in " 
						+ this.getClass().getName());
		}
	}

	public String getContent() {
		return content;
	}

	/**
	 * Get the ID from DAO by it's content.
	 * 
	 * @param content access the content of the description.  
	 */
	public void setContent(String content) {
		this.content = content;
		this.id = DAO.getIDFromName(content);
	}
}