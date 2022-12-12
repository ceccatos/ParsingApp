package application;

import java.sql.SQLException;
import java.util.Scanner;

import database.DatabaseManager;
import parser.RecordTrack;
import parser.RecordTrackLibrary;
import parser.RecordTrackType;

public class ParsingApp {

	public static void main(String[] args) {

		DatabaseManager dbManager = new DatabaseManager("RecordTrackLibrary.db");
		RecordTrackLibrary recordTrackLibrary = new RecordTrackLibrary();
		Scanner keyboard = new Scanner(System.in);

		try {
			dbManager.open();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		try {
			recordTrackLibrary = dbManager.getExistingLibrary();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		while (true) {
			System.out.println("Choices:\n1 - New RecordTrack\n2 - List RecordTrack\n3 - Quit");
			String userInput = keyboard.nextLine();

			if (userInput.equals("3")) {
				System.out.println("Application terminated");
				break;
			}
			if (userInput.equals("1")) {

				System.out.println("Owner:");
				String owner = keyboard.nextLine();
				System.out.println("Name:");
				String name = keyboard.nextLine();
				System.out.println("Description:");
				String description = keyboard.nextLine();
				System.out.println("Type (POSITION, SEPARATOR):");
				String type = keyboard.nextLine().toUpperCase();
				RecordTrack recordTrack = new RecordTrack(owner, name, description, RecordTrackType.valueOf(type));
				recordTrackLibrary.addRecordTrack(recordTrack);
				try {
					dbManager.insertRecordTrack(recordTrack);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (userInput.equals("2")) {
				recordTrackLibrary.printLibrary();
			}

		}
		keyboard.close();
		try {
			dbManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
