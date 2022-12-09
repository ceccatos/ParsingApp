package database;

import java.sql.*;

public class DatabaseManager {

	private String dbName;

	public boolean readTable(String table) {

		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
			connection.setAutoCommit(false);

			statement = connection.createStatement();
			String query = "";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return true;
	}

}
