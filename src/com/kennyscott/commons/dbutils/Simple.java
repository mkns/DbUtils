package com.kennyscott.commons.dbutils;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Basic implementation showing how to use the DbUtils package from Apache
 * Commons. It's surprisingly easy.
 * 
 * First, I created a MySQL database called dbutils, and a user called dbutils
 * with a password of, wait for it, dbutils. Then I created a simple table
 * called Person with 2 columns, and populated some data in it so that there was
 * at least a column called 'name' and it had multiple rows with a name of 'John
 * Doe'. This is because the query later on then SELECTs out those rows, and
 * outputs them to STDOUT.
 * 
 * To get this working yourself, you probably just want to muck around with the
 * values in the getDataSource() method, because at the very least, your server
 * is almost certainly not at the same IP address as mine. Maybe it is, but
 * unlikely, let's face it.
 * 
 * @author mkns
 * 
 */
public class Simple {

	/**
	 * It's a CLI class, so we need a main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		Simple s = new Simple();
		s.execute(args);
	}

	/**
	 * main() calls this one to get out of void context. It does nothing other
	 * than call other methods to get the work done.
	 * 
	 * @param args
	 */
	private void execute(String[] args) {
		DataSource dataSource = null;
		try {
			dataSource = this.getDataSource();
			this.simpleSelect(dataSource);
		} catch (SQLException e) {
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
	private DataSource getDataSource() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("dbutils");
		ds.setPassword("dbutils");
		ds.setServerName("192.168.2.201");
		ds.setPortNumber(3306);
		ds.setDatabaseName("dbutils");
		return ds;
	}

	/**
	 * This is the method which actually uses the DbUtils stuff. I'm using the
	 * ArrayListHandler object here, which creates a List of zero or more rows,
	 * with each row being represented by an Object[].
	 * 
	 * @param dataSource
	 * @throws SQLException
	 */
	private void simpleSelect(DataSource dataSource) throws SQLException {
		ResultSetHandler<List<Object[]>> foo = new ArrayListHandler();

		QueryRunner run = new QueryRunner(dataSource);

		List<Object[]> result = run.query("SELECT * FROM Person WHERE name=?", foo, "John Doe");
		for (int i = 0; i < result.size(); i++) {
			log("Row #" + i);
			Object[] row = (Object[]) result.get(i);
			for (int j = 0; j < row.length; j++) {
				log("Column #" + j + ": " + row[j]);
			}
		}
	}

	/**
	 * I get tired of typing System.out.println()
	 * @param text
	 */
	private void log(Object text) {
		System.out.println(text);
	}
}