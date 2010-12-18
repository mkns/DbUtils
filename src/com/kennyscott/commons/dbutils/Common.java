package com.kennyscott.commons.dbutils;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Common {

	/**
	 * Funnily enough, this creates a DataSource and returns it. The
	 * implementation is hardcoded to create a MysqlDataSource, as it happens.
	 * 
	 * @return
	 * @throws SQLException
	 */
	static protected DataSource getDataSource() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("dbutils");
		ds.setPassword("dbutils");
		ds.setServerName("192.168.2.201");
		ds.setPortNumber(3306);
		ds.setDatabaseName("dbutils");
		return ds;
	}

	/**
	 * I get tired of typing System.out.println()
	 * 
	 * @param text
	 */
	static protected void log(Object text) {
		System.out.println(text);
	}

}
