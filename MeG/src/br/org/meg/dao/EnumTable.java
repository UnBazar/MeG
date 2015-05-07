package org.meg.dao;

public enum EnumTable {
	SECTION("Secao"), 
	DESCRIPTION("Descricao"),
	STATE("Estado");

	private final String tableName;       
	
	private EnumTable(final String tableName) {
		this.tableName = tableName;
	}
	
	@Override
	public String toString() {
		return tableName;
	}
}
