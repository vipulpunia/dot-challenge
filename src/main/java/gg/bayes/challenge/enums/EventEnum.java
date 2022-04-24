package gg.bayes.challenge.enums;

public enum EventEnum {

	BUY("buys"), KILL("Killed"), CAST("Casts"), HIT("Hits"), OTHER("Other event");

	private final String eventDescription;

	private EventEnum(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getDescription() {
		return eventDescription;
	}

}
