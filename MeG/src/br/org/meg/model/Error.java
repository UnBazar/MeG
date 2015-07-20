package org.meg.model;

import java.sql.Date;

public class Error {
	private int id;
	private String description;
	private String referringClassName;
	private Date date;
	/* Status code of the errror 
	 * 0 for not verified
	 * 1 for verification in progress 
	 * 2 for verified
	 */
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferringClassName() {
		return referringClassName;
	}

	public void setReferringClassName(String referringClassName) {
		this.referringClassName = referringClassName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}