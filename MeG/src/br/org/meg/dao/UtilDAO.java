package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.meg.exception.DAOException;
import org.meg.model.History;
import org.meg.model.Note;

import java.util.List;

import org.meg.model.Error;

public class UtilDAO {
	private Connection connection;
	
	Logger logger = Logger.getLogger("UtilDAO");

	public UtilDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	/**
	 * Method that takes the state abbreviation through the id of the database
	 * 
	 * @param id
	 * @return String with the state abbreviation
	 */
	public String getStateAbbreviation(int id) {
		try {
			//SQL command to get the state abbreviation through the id
			String sql = "SELECT sigla FROM Estado WHERE id = ?";
			String siglaDoEstado = null;
			// Get global connection to prepare statement
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			// Get result query
			ResultSet rs = stmt.executeQuery();
			//Runs through the database
			if (rs.next()) {
				siglaDoEstado = rs.getString("sigla");
			}
			stmt.close();
			rs.close();

			return siglaDoEstado;

		} catch(SQLException sqlException) {
			logger.error("Error getting state abbreviation in the database");
			throw new DAOException("Error getting the state abbreviation in the database", this
							.getClass().getName());
		}
	}

	/**
	 * Method that takes the minimum wage of the database
	 * 
	 * @param year
	 * @return float with the minimum wage
	 */
	public float getMinimumWage(int ano) {
		try {
			float minimumWage = 0;
			//SQL command to get the minimum wage through a specific year
			String sql = "SELECT valor FROM SalarioMinimo WHERE ano = ?";
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, ano);
			// Get result query
			ResultSet resultSet = preparedStatement.executeQuery();
			//Runs through the database
			if (resultSet.next()) {
				minimumWage = resultSet.getFloat("valor");
			}
			preparedStatement.close();
			resultSet.close();
			return minimumWage;
		} catch(SQLException sqlException) {
			logger.error("Error searching year in the database");
			throw new DAOException("Error searching year in the database",
					this.getClass().getName());
		}
	}

	/**
	 * Method which adds the history of use of the servlets in the database
	 * 
	 * @param id
	 */
	public void addHistory(String path) {
		try {
			//SQL command to update the number of access in the database table
			String add = "UPDATE Historico SET acessos = acessos + 1 WHERE "
					+ "nome = '"+path+"'";
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = this.connection.prepareStatement(add);
			preparedStatement.execute();
			preparedStatement.close();
		} catch(SQLException sqlException) {
			logger.error("Error adding to history");
			throw new DAOException("Error adding to history", 
					this.getClass().getName());
		}
	}
	/**
	 * Get 3 randomic notes
	 * 
	 * @return {@link ArrayList} of Note
	 */
	public ArrayList<Note> getNotes() {
		// SQL command to get randomic notes
		String sql = "SELECT * FROM Noticias ORDER BY RAND() LIMIT 3";
		// Create an arrayList of notes
		ArrayList<Note> notes = new ArrayList<Note>();
		PreparedStatement preparedStatement;
		try {
			// Get global connection to prepare statement
			preparedStatement = this.connection.prepareStatement(sql);
			// Get result query
			ResultSet resultSet = preparedStatement.executeQuery();
			// Runs while exist to the next
			while (resultSet.next()) {
				// Get each data from note in the database
				int noteID = resultSet.getInt("id");
				String message = resultSet.getString("noticia");
				String image = resultSet.getString("imagem");
				// Create and set an new note
				Note note = new Note();
				note.setMessage(message);
				note.setId(noteID);
				note.setImageURL(image);
				
				notes.add(note);
			}
		} catch(SQLException e) {
			logger.error("Error to obtain notes");
			throw new DAOException("Error to obtain notes", this.getClass().getName());
		}
		return notes;
	}

	/**
	 * Register an exception in the database
	 * 
	 * @param error
	 *            contains information
	 */
	public void registerError(Error error) {
		//SQL command to register an exception in the database
		String sql = "INSERT INTO Erro(descricao, nomeDaClasseReferente, data, status) values(?,?,?,?)";
		try {
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, error.getDescription());
			preparedStatement.setString(2, error.getReferringClassName());
			preparedStatement.setDate(3, error.getDate());
			preparedStatement.setInt(4, error.getStatus());
			preparedStatement.execute();
			preparedStatement.close();
		} catch(SQLException e) {
			// Impossible to save exception
		}
	}

	/**
	 * Removes a certain register
	 * 
	 * @param id
	 *            Identifier of register
	 */
	public void removeErrorRegister(int id) {
		//SQL command to delete an error through the id
		String sql = "DELETE FROM Erro WHERE id = ?";
		try {
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of recorded errors
	 * 
	 * @return One list of errors
	 */
	public List<Error> getErrors() {
		//SQL command that gets all errors
		String sql = "SELECT * FROM Erro";
		//Creates an arrayList of errors
		List<Error> errors = new ArrayList<Error>();
		try {
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// Get result query
			ResultSet resultSet = preparedStatement.executeQuery();
			//Runs while next exists
			while (resultSet.next()) {
				Error error = new Error();
				error.setDate(resultSet.getDate("data"));
				error.setReferringClassName(resultSet
						.getString("nomeDaClasseReferente"));
				error.setId(resultSet.getInt("id"));
				error.setStatus(resultSet.getInt("status"));
				error.setDescription(resultSet.getString("descricao"));
				errors.add(error);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// Impossible to test
			e.printStackTrace();
		}
		return errors;
	}
	
	/**
	 * Gets the history of access
	 * 
	 * @param id
	 * @return a object history with its id and number of access
	 */
	
	public History getHistory(int id){
		History history = null;
		try {
			//SQL command to get the history of access through the id
			String sql = "SELECT * FROM Historico WHERE id = ?";
			// Get global connection to prepare statement
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			// Get result query
			ResultSet resultSet = preparedStatement.executeQuery();
			//Runs through the database
			if (resultSet.next()) {
				int access = resultSet.getInt("acessos");
				history = new History(id, access);
			}
		} catch(SQLException sqlException) {
			logger.error("Error getting history");
			throw new DAOException("Error fetching the history of id 1 = "+id, 
					this.getClass().getName());
		}
		return history;
	}
}
