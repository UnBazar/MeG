package org.meg.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.meg.dao.ConnectionFactory;

public abstract class ActiveRecord {
	private Connection databaseConnection = ConnectionFactory.getConnection();;
	
	public final <T> boolean set(String columnName, T value) {
		String sqlStatement = "UPDATE table_name SET column_name = ?";
		String tableName = this.getClass().getSimpleName();
		boolean sqlQueryResult = false;
		
		sqlStatement = sqlStatement.replace("table_name", tableName);
		sqlStatement = sqlStatement.replace("column_name", columnName);
		
		try {
			PreparedStatement compiledStatement = databaseConnection.prepareStatement(sqlStatement);
			System.out.printf("Table name: %s Column Name: %s Value: %s\n", tableName, columnName, value);
			setSqlStatement(1, compiledStatement, value);
			System.out.println(compiledStatement);
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
