package application;

import java.sql.SQLException;
import database.DatabaseManager;
import parser.RecordTrackLibrary;

public class ParsingApp {

	public void startApplication() {

		DatabaseManager dbManager = new DatabaseManager("RecordTrackLibrary.db");
		RecordTrackLibrary recordTrackLibrary = new RecordTrackLibrary();

		try {
			dbManager.open();
			recordTrackLibrary = dbManager.getExistingLibrary();
			dbManager.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		/*
		 * System.out.println("Owner:"); String owner = keyboard.nextLine();
		 * System.out.println("Name:"); String name = keyboard.nextLine();
		 * System.out.println("Description:"); String description = keyboard.nextLine();
		 * System.out.println("Type (POSITION, SEPARATOR):"); String type =
		 * keyboard.nextLine().toUpperCase(); RecordTrack recordTrack = new
		 * RecordTrack(owner, name, description, RecordTrackType.valueOf(type));
		 * recordTrackLibrary.addRecordTrack(recordTrack); try {
		 * dbManager.insertRecordTrack(recordTrack); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}

}
