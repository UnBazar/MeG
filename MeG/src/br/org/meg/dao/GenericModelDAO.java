package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.meg.exception.DAOException;

public class GenericModelDAO {
	private Connection connection;
	private String tableName;

	public GenericModelDAO(EnumTable modelName) {
		this.connection = ConnectionFactory.getConnection();
		this.tableName = modelName.toString();
	}

	public String getNameFromID(int id) {
		try {
			String sql = "SELECT nome FROM " + tableName + " WHERE id = ?";
			String name = null;
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				name = resultSet.getString("nome");
			}else{
				throw new DAOException("Don't exist register of "
						+ tableName + " with id " + id, this.getClass().getName());
			}
			resultSet.close();
			preparedStatement.close();
			return name;
		} catch (SQLException sqlException) {
			throw new DAOException("An failure occurred while getNameFromID of"
							+ tableName + "model", this.getClass().getName());
		}
	}
	
	public int getIDFromName(String name) {
		try {
			String sql = "SELECT id FROM " + tableName + " WHERE nome = ?";
			int foundID = 0;
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				foundID = resultSet.getInt("id");
			}else{
				throw new DAOException("Don't exist register of "
						+ tableName + " with name equals " + name, this.getClass().getName());
			}
			preparedStatement.close();
			resultSet.close();
			return foundID;
		} catch (SQLException sqlException) {
			throw new DAOException("An failure occurred while getIDFromName of"
							+ tableName + "model", this.getClass().getName());
		}
	}
}
