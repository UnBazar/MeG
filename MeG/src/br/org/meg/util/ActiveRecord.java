package org.meg.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.meg.dao.ConnectionFactory;

public class ActiveRecord {
	private Connection databaseConnection = ConnectionFactory.getConnection();
	private final String tableName = this.getClass().getSimpleName();
	private int id;
	
	public <T> ActiveRecord(String columnName, T value) {
		String sqlStatement = String.format("SELECT * FROM %s WHERE %s = ?", this.tableName, columnName);
		
		try {
			ResultSet queryResult;
			PreparedStatement compiledStatement = databaseConnection.prepareStatement(sqlStatement);
			setSqlStatement(1, compiledStatement, value);
			queryResult = compiledStatement.executeQuery();
			if(queryResult.next()) {
				this.id = queryResult.getInt("id");
			} else {
				throw new IllegalArgumentException("Inexisting register passed as argument on ActiveRecord Constructor");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
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
	
	private <T> void setSqlStatement(int parameterIndex, PreparedStatement sqlStatement, T sqlArgument) {
		try {
			if(sqlArgument.getClass() == Integer.class) {
				sqlStatement.setInt(parameterIndex, (Integer) sqlArgument);
			} else if(sqlArgument.getClass() == Float.class) {
				sqlStatement.setFloat(parameterIndex, (Float) sqlArgument);
			} else if(sqlArgument.getClass() == String.class) {
				sqlStatement.setString(parameterIndex, (String) sqlArgument);
			} else {
				// no method matched
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
