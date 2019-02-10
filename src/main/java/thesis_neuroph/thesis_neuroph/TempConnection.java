package thesis_neuroph.thesis_neuroph;

/**
 * @TODO: Remove this class when integrating with plug-in
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TempConnection {
	private Connection dbConnection = null;

	public TempConnection() {
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
