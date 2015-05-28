package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.meg.exception.DAOException;
import org.meg.model.History;
import org.meg.model.Note;

import java.util.List;

import org.meg.model.Error;

public class UtilDAO {
	private Connection connection;

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
			String sql = "SELECT sigla FROM Estado WHERE id = ?";
			String siglaDoEstado = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				siglaDoEstado = rs.getString("sigla");
			}
			stmt.close();
			rs.close();

			return siglaDoEstado;

		} catch(SQLException sqlException) {
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
			String sql = "SELECT valor FROM SalarioMinimo WHERE ano = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, ano);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				minimumWage = rs.getFloat("valor");
			}
			stmt.close();
			rs.close();
			return minimumWage;
		} catch(SQLException sqlException) {
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
			String add = "UPDATE Historico SET acessos = acessos + 1 WHERE "
					+ "nome = '"+path+"'";
			PreparedStatement stmt = this.connection.prepareStatement(add);
			stmt.execute();
			stmt.close();
		} catch(SQLException sqlException) {
			System.err.println(sqlException);
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
		String sql = "INSERT INTO Erro(descricao, nomeDaClasseReferente, data, status) values(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, error.getDescription());
			stmt.setString(2, error.getReferringClassName());
			stmt.setDate(3, error.getDate());
			stmt.setInt(4, error.getStatus());
			stmt.execute();
			stmt.close();
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
		String sql = "DELETE FROM Erro WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
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
		String sql = "SELECT * FROM Erro";
		List<Error> errors = new ArrayList<Error>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Error error = new Error();
				error.setDate(rs.getDate("data"));
				error.setReferringClassName(rs
						.getString("nomeDaClasseReferente"));
				error.setId(rs.getInt("id"));
				error.setStatus(rs.getInt("status"));
				error.setDescription(rs.getString("descricao"));
				errors.add(error);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// Impossible to test
			e.printStackTrace();
		}
		return errors;
	}
	
	public History getHistory(int id){
		History history = null;
		try {
			String sql = "SELECT * FROM Historico WHERE id = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int access = resultSet.getInt("acessos");
				history = new History(id, access);
			}
		} catch(SQLException sqlException) {
			throw new DAOException("Error fetching the history of id 1 = "+id, 
					this.getClass().getName());
		}
		return history;
	}
}
