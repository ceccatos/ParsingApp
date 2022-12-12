package parser;

import java.util.ArrayList;

public class RecordTrackLibrary {

	private ArrayList<RecordTrack> recordTrackLibrary;

	public RecordTrackLibrary() {
		this.recordTrackLibrary = new ArrayList<>();
	}

	public void addRecordTrack(RecordTrack recordTrack) {
		if (!(recordTrackLibrary.contains(recordTrack))) {
			recordTrackLibrary.add(recordTrack);
		} else {
			throw new IllegalArgumentException("recordTrack already recorded");
		}
	}
	
	public void clearLibrary() {
		recordTrackLibrary.clear();
	}
	
	public int size() {
		return this.recordTrackLibrary.size();
	}
	

	public void printLibrary() {
		if (this.recordTrackLibrary.isEmpty()) {
			System.out.println("No record track in the library");
		}
		for (RecordTrack recordTrack : recordTrackLibrary) {
			System.out.println(recordTrack);
		}
	}

}
