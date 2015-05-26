package org.meg.model;

public class Note {
	
	private int id;
	private String message;
	private String imageURL;

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
		if (message != null && message.length() < 255) {
			this.message = message;
		} else {
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
			throw new IllegalArgumentException("URL of image is invalid :" + imageURL);			
		}
	}
}