package org.meg.model;

public class History {
	private int id;
	private String name;
	private long value;

	public final static String[] functionalities = { "ranking", "compara", "projecao", "grafico" };

	public History(int id) {
		this.id = id;
		// id - 1 actually
		setName(functionalities[id - 1]);
	}

	public History(int id, int access) {
		this.value = access;
		this.id = id;
		setName(functionalities[id - 1].toUpperCase());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
