package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.meg.exception.DAOException;
import org.meg.model.Description;
import org.meg.model.State;
import org.meg.model.Frame;
import org.meg.model.Section;

public class FrameDAO {
	private Connection connection;
	/**
	 * Creates a connection with the MYSQL database through ConnectionFactory 
	 */
	public FrameDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Persists a frame in the database
	 * 
	 * @param frame	that will be saved in the database
	 */
	public void addFrame(Frame frame) {
		if(!frameExists(frame)){
			String sqlStatement = "INSERT INTO Quadro(ano, valor, estado_id, secao_id, descricao_id) "
					+ "VALUES(?,?,?,?,?)";
			try {
				PreparedStatement preparedStatement = this.connection.prepareStatement(sqlStatement);
				preparedStatement.setInt(1, frame.getYear());
				preparedStatement.setFloat(2, frame.getValue());
				preparedStatement.setInt(3, frame.getState().getId());
				preparedStatement.setInt(4, frame.getSection().getId());
				preparedStatement.setInt(5, frame.getDescription().getId());
				preparedStatement.execute();
				preparedStatement.close();
			} catch (SQLException sqlException) {
				throw new DAOException("The system failed to persist a frame. Exception: " 
											+ sqlException.getMessage(), this.getClass().getName());
			}
		}
	}
	
	/**
	 * Identify if the frame sent as argument exists in the database
	 * 
	 * @param frame that will be analysed
	 * @return	true if the frame exists
	 */
	public boolean frameExists(Frame frame){
		String sqlStatement = "SELECT * FROM Quadro "
				+ "WHERE estado_id = ? AND secao_id = ? "
				+ "AND descricao_id = ? AND ano = ?";
		try {
			boolean exists = false;
			// A database of result set
			ResultSet queryResult;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, frame.getState().getId());
			preparedStatement.setInt(2, frame.getSection().getId());
			preparedStatement.setInt(3, frame.getDescription().getId());
			preparedStatement.setInt(4, frame.getYear());
			// Get result set from preparedStatement
			queryResult = preparedStatement.executeQuery();
			// returns true if a match is found
			exists = queryResult.first();
			// If two frames are match, throw an exception
			if(queryResult.next()){
				throw new DAOException("Twice frame is catch in method frame exists", this.getClass().getName());
			}else{
				// do nothing
			}
			queryResult.close();
			preparedStatement.close();
			return exists;
		}catch(SQLException exception){
			throw new DAOException("An unexpected failure occurred while verifying existing frame. Exception: " 
										+ exception.getMessage(), this.getClass().getClass().getName());
		}
	}
	
	/**
	 * Search in the database a list of frames corresponding to the values sent as argument.
	 * 
	 * @param initialYear
	 * @param finalYear
	 * @param state
	 * @param section
	 * @param description
	 * @return a list of frames if found on database or null if not
	 */
	public List<Frame> getFramesList(int initialYear, int finalYear, State state, 
			Section section, Description description) {
		String sqlStatement = "SELECT * FROM Quadro "
				+ "WHERE estado_id = ?  AND secao_id = ? "
				+ "AND descricao_id = ? AND ano >= ? AND ano <= ? ";
		try {
			List<Frame> frames = new ArrayList<Frame>();;
			// A database result set
			ResultSet queryResult;
			PreparedStatement sqlCompiledStatement = connection.prepareStatement(sqlStatement);
			sqlCompiledStatement.setInt(1, state.getId());
			sqlCompiledStatement.setInt(2, section.getId());
			sqlCompiledStatement.setInt(3, description.getId());
			sqlCompiledStatement.setInt(4, initialYear);
			sqlCompiledStatement.setInt(5, finalYear);
			// Get an result from database
			queryResult = sqlCompiledStatement.executeQuery();
			while(queryResult.next()) {
				// Row catch, populate new frames
				Frame frame = new Frame();
				frame.setDescription(description);
				frame.setState(state);
				frame.setSection(section);
				frame.setYear(queryResult.getInt("ano"));
				frame.setValue(queryResult.getFloat("valor"));
				// Add frame created to list
				frames.add(frame);				
			}
			queryResult.close();
			sqlCompiledStatement.close();
			// If none frame is catch, throw an Exception
			if(frames.isEmpty()) {
				String message = "Tried to get frames between " + initialYear 
						+ " - " + finalYear + " and nothing was found.";
				throw new DAOException(message, this.getClass().getName());
			}
			// Treat to throw an checked exception
			return frames;
		} catch(SQLException sqlException) {
			throw new DAOException("Failed while trying to list frames in the database. Exception:  "
										+ sqlException.getMessage(), this.getClass().getName());
		}
	}
	
	/**
	 * Queries database searching for a determined frame for each state.
	 * 
	 * @param year 
	 * @param section
	 * @param description
	 * @return list of frames for each state
	 */
	public List<Frame> getFramesList(int year, Section section, Description description) {
		String sqlStatement = "SELECT * FROM Quadro "
				+ "WHERE secao_id = ? "
				+ "AND descricao_id = ? "
				+ "AND ano = ?";
		try {
			List<Frame> frames;
			ResultSet queryResult;
			PreparedStatement sqlCompiledStatement = connection.prepareStatement(sqlStatement);
			sqlCompiledStatement.setInt(1, section.getId());
			sqlCompiledStatement.setInt(2, description.getId());
			sqlCompiledStatement.setInt(3, year);
			queryResult = sqlCompiledStatement.executeQuery();
			frames = new ArrayList<>();
			while(queryResult.next()) {
				Frame frame = new Frame();
				State state = new State();
				state.setId(queryResult.getInt("estado_id"));
				frame.setYear(year);
				frame.setState(state);
				frame.setSection(section);
				frame.setDescription(description);
				frame.setValue(queryResult.getFloat("valor"));
				frames.add(frame);
			}
			queryResult.close();
			sqlCompiledStatement.close();
			return frames;
		} catch(SQLException exception) {
			throw new DAOException("Failed while trying to search frames in the database. Exception: "
										+ exception.getMessage(), this.getClass().getName());
		}
		
	}
}
