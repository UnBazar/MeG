package org.meg.model;

import org.apache.log4j.Logger;

public class Note {
	
	private int id;
	private String message;
	private String imageURL;
	
	// Messages can't be null nor higher than 254
	static final int MAX_MESSAGE_LENGTH = 254;
	
	Logger logger = Logger.getLogger("Note");

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message != null && message.length() <= MAX_MESSAGE_LENGTH) {
			this.message = message;
		} else {
			logger.error("Message NULL or with too many letters.");
			throw new IllegalArgumentException("Invalid note!");
		}
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		if (imageURL != null) {
			this.imageURL = imageURL;
		} else {
			logger.error("Image URL NULL.");
			throw new IllegalArgumentException("URL of image is invalid :" + imageURL);			
		}
	}
}