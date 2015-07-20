package org.meg.model;

public class Frame {
	private int id;
	private Description description;
	private int year;
	private float value;
	private State state;
	private Section section;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}
	
	public String toString() {
		if(description != null && year != 0 && state != null && section != null) {
			return String.format("Description: %s Year: %d State: %s Section: %s Value: %.2f", 
					description.getContent(), year, state.getName(), section.getName(), value);
		} else {
			return super.toString();
		}
	}
	
}
