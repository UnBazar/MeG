package org.meg.controller;

import java.util.ArrayList;
import java.util.List;

public enum EnumAttribute {
	SECTION("section"),
	DESCRIPTION("description"),
	STATE("state"),
	INITIAL_YEAR("initialYear"),
	FINAL_YEAR("finalYear"),
	UNIQUE_YEAR("uniqueYear"),
	OPTION("option");
	
	private final String attributeName;
	
	private EnumAttribute(final String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Override
	public String toString() {
		return attributeName;
	}
	
	public static List<String> getAttibuteNames(){
		List<String> attributeNames = new ArrayList<String>();
		for(EnumAttribute attribute : values()){
			attributeNames.add(attribute.toString());
		}
		return attributeNames;
	}
}
