package com.kennyscott.commons.dbutils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 * MapListHandler is the one to use if you want column names rather than just an
 * Object array of values
 * 
 * @author mkns
 * 
 */
public class ColumnListExample extends AbstractMain {

	protected void execute(String[] args) {
		try {
			DataSource dataSource = getDataSource();
			QueryRunner queryRunner = getQueryRunner(dataSource);
			MapListHandler rsh = new MapListHandler();
			List<Map<String, Object>> result = queryRunner.query("SELECT * FROM Person WHERE name=?", rsh, "John Doe");
			log("result has " + result.size() + " rows");

			/*
			 * Let's loop over each row and pull out the column name and value
			 */
			for (Iterator<Map<String, Object>> i = result.iterator(); i.hasNext();) {
				log("New row:");
				Map<String, Object> m = i.next();
				Set<String> keySet = m.keySet();
				for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
					String key = it.next();
					Object value = m.get(key);
					log(key + " => " + value);
				}
			}

			/*
			 * Now let's just get stuck in and pull out a value, since we know
			 * the name of the columns
			 */
			Map<String, Object> row = result.get(0);
			Object age = row.get("age");
			log("The age is " + age);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ColumnListExample c = new ColumnListExample();
		c.execute(args);
	}

}
