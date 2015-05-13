package org.meg.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.meg.dao.ConnectionFactory;

public abstract class ActiveRecord {
	private Connection databaseConnection = ConnectionFactory.getConnection();;
	
	public final <T> boolean set(String columnName, T value) {
		String sqlStatement = "UPDATE ? SET ? = ?";
		String tableName = this.getClass().toString();
		boolean sqlQueryResult = false;
		try {
			PreparedStatement compiledStatement = databaseConnection.prepareStatement(sqlStatement);
			setSqlStatement(compiledStatement, tableName, 1);
			setSqlStatement(compiledStatement, columnName, 2);
			setSqlStatement(compiledStatement, value, 3);
			sqlQueryResult = compiledStatement.execute();
			compiledStatement.close();
			return sqlQueryResult;			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private <T> void setSqlStatement(PreparedStatement sqlStatement, T sqlArgument, int parameterIndex) {
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
