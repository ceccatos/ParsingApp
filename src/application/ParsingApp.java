package application;

import java.util.Scanner;

import parser.RecordTrack;
import parser.RecordTrackLibrary;
import parser.RecordTrackType;

public class ParsingApp {

	public static void main(String[] args) {

		RecordTrackLibrary recordTrackLibrary = new RecordTrackLibrary();
		Scanner keyboard = new Scanner(System.in);

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
				String typeInput = keyboard.nextLine();
				RecordTrackType type = RecordTrackType.POSITION;
				if (typeInput.equalsIgnoreCase("POSITION")) {
					type = RecordTrackType.POSITION;
				} else if (typeInput.equalsIgnoreCase("SEPARATOR")) {
					type = RecordTrackType.SEPARATOR;
				}
				RecordTrack recordTrack = new RecordTrack(owner, name, description, type);
				if (recordTrackLibrary.addRecordTrack(recordTrack, true)) {
					System.out.println("Record Track added to Library");
				} else {
					System.out.println("Record Track not added to Library");
				}

			}
			if (userInput.equals("2")) {
				recordTrackLibrary.printLibrary();
			}

		}
		keyboard.close();

	}

}
