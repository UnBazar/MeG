package br.org.meg.dao;

import java.sql.Connection;

import br.org.meg.model.Administrador;

public class AdministradorDAO {
	private Connection connection;
	
	public AdministradorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adicionar(Administrador adm) {
		
	}
	
	public Administrador buscar(Administrador adm) {
		return null;
	}
}
