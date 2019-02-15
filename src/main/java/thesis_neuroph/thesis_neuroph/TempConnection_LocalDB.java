package thesis_neuroph.thesis_neuroph;

/**
 * Used to create a connection to the local MySQL DB without using the Server
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TempConnection_LocalDB {
	private Connection dbConnection = null;

	public TempConnection_LocalDB() {
		this.setDBConnection("jdbc:mysql://localhost:3306/tutorData?useSSL=false", "melissa", "");

	}

	public void setDBConnection(String connectionName, String userName, String password) {
		/**
		 * @TODO: Remove the DB connection when integrating with plug-in
		 */
		try {
			dbConnection = DriverManager.getConnection(connectionName, userName, password);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

}
