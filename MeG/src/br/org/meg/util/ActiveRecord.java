package org.meg.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.meg.dao.ConnectionFactory;

public class ActiveRecord {
	private Connection databaseConnection = ConnectionFactory.getConnection();
	private final String tableName = this.getClass().getSimpleName();
	private int id;
	
	/**
	 * Checks if register pass as argument and returns an object 
	 * 
	 * @param id 
	 * @return ActiveRecord 
	 */
	public static final ActiveRecord find(int id) {
		String sqlStatement = String.format("SELECT * FROM %s WHERE id = ?", getCurrentClass());
		ActiveRecord newObject = new ActiveRecord();
		Connection staticDatabaseConnection = ConnectionFactory.getConnection();
		
		try {
			ResultSet queryResult;
			PreparedStatement compiledStatement = staticDatabaseConnection.prepareStatement(sqlStatement);
			setSqlStatement(1, compiledStatement, id);
			queryResult = compiledStatement.executeQuery();
			if(queryResult.next()) {
				((ActiveRecord) newObject).id = queryResult.getInt("id");
			} else {
				throw new IllegalArgumentException("Inexisting register passed as argument on ActiveRecord Constructor");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return newObject;
	}
	
	/**
	 * Returns an object of the column searched 
	 * 
	 * @param columnName in the database
	 * @return Object with the column name 
	 */
	public final Object get(String columnName) {
		String sqlStatement = String.format("SELECT %s FROM %s WHERE id = %d", columnName, this.tableName, this.id);
		System.out.println(sqlStatement);
		try {
			PreparedStatement compiledStatement = databaseConnection.prepareStatement(sqlStatement);
			ResultSet queryResult = compiledStatement.executeQuery();
			if(queryResult.next()) {
				return queryResult.getObject(columnName);
			} else {
				throw new IllegalArgumentException("Inexisting column name passed as argument on ActiveRecord get method");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Updates a value from the database 
	 * 
	 * @param columnName name of the column in the database 
	 * @param value
	 * @return sqlQueryResult 
	 */
	public final <T> boolean set(String columnName, T value) {
		String sqlStatement = String.format("UPDATE %s SET %s = ? WHERE id = %d", this.tableName, columnName, this.id);
		boolean sqlQueryResult = false;

		try {
			PreparedStatement compiledStatement = databaseConnection.prepareStatement(sqlStatement);
			setSqlStatement(1, compiledStatement, value);
			sqlQueryResult = compiledStatement.execute();
			compiledStatement.close();
			return sqlQueryResult;			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Used to set parameters with SQL for the database 
	 * 
	 * @param parameterIndex 
	 * @param sqlStatement message in SQL to be used
	 * @param sqlArgument the type of the argument
	 * @return List<Quadro> with scenes corresponding 
	 */
	private static <T> void setSqlStatement(int parameterIndex, PreparedStatement sqlStatement, T sqlArgument) {
		try {
			if(sqlArgument.getClass() == Integer.class) {
				sqlStatement.setInt(parameterIndex, (Integer) sqlArgument);
			} else if(sqlArgument.getClass() == Float.class) {
				sqlStatement.setFloat(parameterIndex, (Float) sqlArgument);
			} else if(sqlArgument.getClass() == String.class) {
				sqlStatement.setString(parameterIndex, (String) sqlArgument);
			} else {
				// No method matched
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the current class by callerLine 
	 * 
	 * @return String with the elements of file 
	 */
	public static String getCurrentClass() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String currentFileName = stackTraceElements[stackTraceElements.length - 1].getFileName();
		int callerLine = stackTraceElements[stackTraceElements.length - 1].getLineNumber();
		System.out.println(stackTraceElements[stackTraceElements.length - 1].getFileName());
		return readClassFile(currentFileName, callerLine);
	}
	
	@SuppressWarnings("resource")
	private static String readClassFile(String fileName, int lineNumber) {
		File classFile = new File("/home/pedro/workspace/MeG/MeG/src/br/org/meg/util/" + fileName);
		Scanner s;
		try {
			s = new Scanner(classFile);
			String caller = null;
			for(int i = 0; i < lineNumber; i++) {
				caller = s.nextLine();
			}
			caller = caller.split("\\.")[0];
			return caller;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
