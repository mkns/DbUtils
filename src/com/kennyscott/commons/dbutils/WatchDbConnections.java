package com.kennyscott.commons.dbutils;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import com.kennyscott.commons.dbutils.AbstractMain;
import com.kennyscott.commons.dbutils.Common;

/**
 * <p>
 * This class shows when database connections are being made.
 * 
 * <p>
 * Initially, I guessed that the QueryRunner would be making the database
 * connection and that the connection would be held open until it was either
 * explicitly disconnected, or it went out of scope. I was wrong.
 * 
 * <p>
 * It transpires that the connection is only made when a query is being
 * performed, and then it is immediately being disconnected.
 * 
 * <p>
 * Now, this is a good thing so long as you're not working on something majorly
 * critical where db connections happening all the time are a bad thing. Where I
 * work, in some of our systems, that's a bad thing.
 * 
 * <p>
 * However, it's not always bad, and for this example it's totally fine.
 * 
 * <p>
 * To see this in practice, you really want to be watching the MySQL log file.
 * On my Linux box, for example, I do this:
 * 
 * <p>
 * sudo tail -F /var/log/mysql/mysql.log
 * 
 * <p>
 * Each time this class does something, it sleeps for 5 seconds to give you a
 * chance to see what happened in the mysql log file. Take careful note of the
 * Connect and Quit lines - you can see the database being connected to, and
 * disconnected from, twice for each time that this class goes to query the
 * database.
 * 
 * @author mkns
 * 
 */
public class WatchDbConnections extends AbstractMain {

	@Override
	/**
	 * The main meat, which creates a DataSource, a QueryRunner, then does some SELECTs on the database, while logging and sleeping variously.
	 */
	protected void execute(String[] args) {
		DataSource dataSource;
		try {
			log("Starting, about to get DataSource");
			dataSource = Common.getDataSource();
			sleep();
			log("Got DataSource, now getting QueryRunner");
			QueryRunner queryRunner = Common.getQueryRunner(dataSource);
			sleep();
			log("Got QueryRunner, going to dump rows");
			Common.dumpRows(queryRunner, "vehicle");
			sleep();
			log("Got vehicle rows, now going to get Person rows");
			Common.dumpRows(queryRunner, "Person");
			sleep();
			log("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * CLI needs a main()
	 * @param args
	 */
	public static void main(String[] args) {
		WatchDbConnections w = new WatchDbConnections();
		w.execute(args);
	}

}
