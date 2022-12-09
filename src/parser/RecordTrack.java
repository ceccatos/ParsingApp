package parser;

public class RecordTrack {

	private int token; 
	private String owner;
	private String name;
	private String description;
	private RecordTrackType typology;

	public RecordTrack(String owner, String name, String description, RecordTrackType typology) {
		this.owner = capitalize(owner);
		this.name = capitalize(name);
		this.description = capitalize(description);
		this.typology = typology;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public String getOwner() {
		return this.owner;
	}

	public RecordTrackType getTypology() {
		return this.typology;
	}
	
	public int getToken() {
		return this.token;
	}
	
	public void setToken(int token) {
		this.token = token;
	}

	// Capitalize first letter of a String
	public String capitalize(String input) {
		if (input == null || input.equals("")) {
			return "";
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RecordTrack)) {
			return false;
		}
		RecordTrack compared = (RecordTrack) obj;
		if (this.name.equals(compared.name) && this.owner.equals(compared.owner) && this.typology == compared.typology) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "RecordTrack [owner = " + owner + ", name = " + name + ", desc = " + description + ", type = "
				+ typology + "]";
	}
	
	

}
