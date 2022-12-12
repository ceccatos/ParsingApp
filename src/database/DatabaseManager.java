package database;

import java.sql.*;
import parser.RecordTrack;
import parser.RecordTrackLibrary;
import parser.RecordTrackType;

public class DatabaseManager {

	private Connection connection;
	private String url;

	public DatabaseManager(String dbName) {
		this.url = "jdbc:sqlite:" + dbName;
		this.connection = null;
	}

	public void open() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		this.connection = DriverManager.getConnection(this.url);
		this.createDB();
	}

	public void close() throws SQLException {
		this.connection.close();
	}

	private void createDB() throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS record_track ("
				+ "token_record_track INTEGER NOT NULL, "
				+ "owner TEXT NOT NULL, "
				+ "name TEXT NOT NULL, "
				+ "description TEXT NOT NULL, "
				+ "type TEXT NOT NULL DEFAULT 'POSITION', "
				+ "PRIMARY KEY(token_record_track)"
				+ ")");
		statement.close();
	}
	
	public void clearDB() throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("DELETE FROM record_track");
		statement.close();
	}

	public void insertRecordTrack(RecordTrack recordTrack) throws SQLException {
		PreparedStatement prepStatement = this.connection
				.prepareStatement("INSERT INTO record_track (owner, name, description, type) VALUES (?,?,?,?)");
		prepStatement.setString(1, recordTrack.getOwner());
		prepStatement.setString(2, recordTrack.getName());
		prepStatement.setString(3, recordTrack.getDescription());
		prepStatement.setString(4, recordTrack.getType().name());
		prepStatement.executeUpdate();
		prepStatement.close();
	}

	public RecordTrackLibrary getExistingLibrary() throws SQLException {
		RecordTrackLibrary existingLibrary = new RecordTrackLibrary();
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM record_track");
		while (resultSet.next()) {
			String owner = resultSet.getString("owner");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String type = resultSet.getString("type").toUpperCase();
			RecordTrack recordTrack = new RecordTrack(owner, name, description, RecordTrackType.valueOf(type));
			existingLibrary.addRecordTrack(recordTrack);
		}
		statement.close();
		resultSet.close();
		return existingLibrary;
	}
}
