package com.kennyscott.commons.dbutils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang.ArrayUtils;

public class SqlConcat extends AbstractMain {

	@Override
	protected void execute(String[] args) {
		DataSource dataSource;
		log("starting...");
		try {
			dataSource = getDataSource();
			QueryRunner queryRunner = getQueryRunner(dataSource);
			String query = "select concat('Name: ', name) from Person;";
			ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
			List<Object[]> result = queryRunner.query(query, rsh);
			log("Rows returned: " + result.size());
			for (Iterator<Object[]> i = result.iterator(); i.hasNext();) {
				Object[] row = i.next();
				log(ArrayUtils.toString(row));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SqlConcat s = new SqlConcat();
		s.execute(args);
	}

}
