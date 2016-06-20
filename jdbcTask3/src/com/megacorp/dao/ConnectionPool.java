package com.megacorp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.megacorp.properties.Property;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private DataSource ds;

	private ConnectionPool() {
		ds = getDataSource();
	}

	private DataSource getDataSource() {
		try {
			String dbConfig = Property.getProperty().getDbInitParam();
			Context initContext = new InitialContext();
			Context rootContext = (Context) initContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource) rootContext
					.lookup(dbConfig);

			return dataSource;

		} catch (NamingException e) {
			throw new RuntimeException("cant create connection", e);
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection cn = ds.getConnection();

		return cn;
	}

	public static void closeResourses(Connection cn,
			PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("Error: Statement has not been closed!");
			}
		}

		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				System.out.println("Error: Connection has not been closed!");
			}
		}
	}

	public static void closeResourses(Connection cn,
			PreparedStatement statement, ResultSet set) {

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				System.out.println("Error: ResultSet has not been closed!");
			}
		}

		closeResourses(cn, statement);

	}

}
