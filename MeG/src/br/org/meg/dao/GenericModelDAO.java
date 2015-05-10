package org.meg.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.meg.exception.DAOException;
import org.meg.model.Description;
import org.meg.model.Section;
import org.meg.model.State;

/**
 * This implementation is responsible for some generic operations 
 * present in State, Description and Section.
 * 
 */
public class GenericModelDAO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	/*	Table name that will operate */
	private final String tableName;
	
	/**
	 * Unique constructor 
	 * 
	 * @param modelName	Type of model that will operate
	 */
	public GenericModelDAO(EnumTable modelName) {
		this.connection = ConnectionFactory.getConnection();
		this.tableName = modelName.toString();
	}
	
	/**
	 * Get an name or title from an id
	 * 
	 * @param id of register
	 * @return name found
	 */
	public String getNameFromID(int id) {
		try {
			String sql = "SELECT nome FROM " + tableName + " WHERE id = ?";
			String name = null;
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			// In the case of search find something
			if (resultSet.next()) {
				name = resultSet.getString("nome");
			}else{
				// Exception throw, bd must be invalid
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
	
	/**
	 * Get an id from an name or title
	 * 
	 * @param name of register
	 * @return id found
	 */
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
				// Exception throw, bd must be invalid
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
	
	/**
	 * This method list all registers in an table of database.
	 * Therefore, it just work to follow models:
	 * <ul>
	 * 	<li>Description</li>
	 *	<li>Section</li>
	 *	<li>State</li>
	 * </ul>
	 * @return an {@link ArrayList} with values founded
	 */
	public ArrayList<Object> listAll() {
		String sql = "SELECT * FROM " + tableName;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Object> genericList = new ArrayList<Object>();
			while(resultSet.next()){
				switch(tableName){
					case "Descricao":
						Description description = new Description();
						description.setId(resultSet.getInt("id"));
						genericList.add(description);
						break;
					case "Secao":
						Section section = new Section();
						section.setId(resultSet.getInt("id"));
						genericList.add(section);
						break;
					case "Estado":
						State state = new State();
						state.setId(resultSet.getInt("id"));
						genericList.add(state);
						break;
					default :
						throw new RuntimeException("Exception throw in listAll of GenericDAO...");
				}
			}
			return genericList;
		} catch (SQLException sqlException) {
			throw new DAOException("An failure occurred while list all registers of"
					+ tableName + "model", this.getClass().getName());
		}
	}
}
