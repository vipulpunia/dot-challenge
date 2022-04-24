package gg.bayes.challenge.service;

public interface IEventHandler {

	void processMatchEvent(Long matchId, String eventDetail);

}
