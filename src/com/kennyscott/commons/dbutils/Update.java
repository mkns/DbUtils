/**
 * 
 */
package com.kennyscott.commons.dbutils;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

/**
 * <p>
 * This class shows how to update data in the database, especially using
 * variable number of parameters, which came in with Java 5.
 * 
 * @author mkns
 * 
 */
public class Update extends AbstractMain {

	/**
	 * CLI needs main() method to run
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Update u = new Update();
		u.execute(args);
	}

	/**
	 * Standard no-arg constructor
	 */
	public Update() {
	}

	/**
	 * We start off with minimal data, then update the info on the Renault. Then
	 * we throw caution to the wind and get a Ferrari. We see the state of the
	 * table before and after each change.
	 * 
	 * @param args
	 */
	protected void execute(String[] args) {
		try {
			QueryRunner queryRunner = Common.getQueryRunner(Common.getDataSource());
			log(Common.dumpRows(queryRunner, "vehicle"));
			this.doUpdate1(queryRunner);
			log(Common.dumpRows(queryRunner, "vehicle"));
			this.doUpdate2(queryRunner);
			log(Common.dumpRows(queryRunner, "vehicle"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the info on the Renault
	 * @param queryRunner
	 * @throws SQLException
	 */
	private void doUpdate1(QueryRunner queryRunner) throws SQLException {
		queryRunner.update("UPDATE vehicle SET id=?, colour=?", Common.generateRandomValue(), "Black");
	}

	/**
	 * Throws caution to the wind, and we get a Ferrari
	 * @param queryRunner
	 * @throws SQLException
	 */
	private void doUpdate2(QueryRunner queryRunner) throws SQLException {
		queryRunner.update("UPDATE vehicle SET id=?, make=?, model=?, colour=?", Common.generateRandomValue(), "Ferrari", "458 Italia", "Black");
	}

}
