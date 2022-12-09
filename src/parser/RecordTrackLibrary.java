package parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RecordTrackLibrary {

	private ArrayList<RecordTrack> recordTrackLibrary;

	public RecordTrackLibrary() {
		this.recordTrackLibrary = new ArrayList<>();
		// retrieve existing library from database
		this.initLibrary("RecordTrackLibrary.db");
	}

	public boolean addRecordTrack(RecordTrack recordTrack, boolean writeToDb) {
		if (!(recordTrackLibrary.contains(recordTrack))) {
			recordTrackLibrary.add(recordTrack);
			if (writeToDb) {
				
			}
			return true;
		}
		return false;
	}

	public void printLibrary() {
		if (this.recordTrackLibrary.isEmpty()) {
			System.out.println("No record track in the library");
		}
		for (RecordTrack recordTrack : recordTrackLibrary) {
			System.out.println(recordTrack);
		}
	}

	public boolean initLibrary(String database) {

		Connection connection;
		Statement statement;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + database);
			connection.setAutoCommit(false);

			statement = connection.createStatement();
			String query = "SELECT * FROM record_track;";

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String owner = resultSet.getString("owner");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String typologyDB = resultSet.getString("typology");
				RecordTrackType typology = RecordTrackType.POSITION;
				if (typologyDB.equalsIgnoreCase("POSITION")) {
					typology = RecordTrackType.POSITION;
				} else if (typologyDB.equalsIgnoreCase("SEPARATOR")) {
					typology = RecordTrackType.SEPARATOR;
				}
				RecordTrack recordTrack = new RecordTrack(owner, name, description, typology);
				this.addRecordTrack(recordTrack, false);
			}
			resultSet.close();
			statement.close();
			connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
