package com.kennyscott.commons.dbutils;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang.StringUtils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class AbstractMain {
	
	protected abstract void execute(String[] args);

	/**
	 * I get tired of typing System.out.println()
	 * 
	 * @param text
	 */
	protected void log(Object text) {
		System.out.println(text);
	}

	protected void sleep() {
		sleep(5000);
	}
	
	protected void sleep(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Funnily enough, this creates a DataSource and returns it. The
	 * implementation is hardcoded to create a MysqlDataSource, as it happens.
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected DataSource getDataSource() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("dbutils");
		ds.setPassword("dbutils");
		ds.setServerName("172.30.30.72");
		ds.setPortNumber(3306);
		ds.setDatabaseName("dbutils");
		return ds;
	}

	protected QueryRunner getQueryRunner(DataSource dataSource) {
		return new QueryRunner(dataSource);
	}
	
	/**
	 * Returns a UUID stripped of hyphens
	 * 
	 * @return
	 */
	protected String generateRandomValue() {
		// let's not be silly, now
		return generateRandomValue(0);
	}

	/**
	 * Returns a UUID stripped of hyphens, restricted to a maximum length based
	 * on the parameter provided
	 * 
	 * @param length
	 * @return
	 */
	protected String generateRandomValue(int length) {
		String uuid = UUID.randomUUID().toString();
		Pattern pattern = Pattern.compile("-");
		Matcher matcher = pattern.matcher(uuid);
		if (matcher.find()) {
			String stripped = matcher.replaceAll("");
			if (length != 0) {
				return stripped.substring(length);
			}
			else {
				return stripped;
			}
		} else {
			// this shouldn't happen, so if it does, assplode
			return null;
		}
	}

	/**
	 * Given a table name, this method dumps each row CSV style
	 * @param queryRunner
	 * @param tableName
	 * @throws SQLException
	 */
	protected String dumpRows(QueryRunner queryRunner, String tableName) throws SQLException {
		ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();

		List<Object[]> result = queryRunner.query("SELECT * FROM " + tableName, rsh);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.size(); i++) {
			Object[] row = (Object[]) result.get(i);
			sb.append(StringUtils.join(row, ","));
		}
		return sb.toString();
	}
}
