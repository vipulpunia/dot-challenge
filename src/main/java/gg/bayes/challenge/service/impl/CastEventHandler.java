package gg.bayes.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.entity.CastEventEntity;
import gg.bayes.challenge.service.IEventHandler;
import gg.bayes.challenge.util.CommonUtility;

@Service(value = "castevent")
public class CastEventHandler implements IEventHandler {

	private IMatchDao matchDao;

	@Autowired
	public CastEventHandler(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public void processMatchEvent(Long matchId, String eventDetail) {
		String eventChunks[] = eventDetail.split(" ");
		CastEventEntity castEventEntity = new CastEventEntity();
		castEventEntity.setMatchId(matchId);
		castEventEntity.setEventTime(CommonUtility.convertToMillis(eventChunks[0]));
		castEventEntity.setEventActionHero(CommonUtility.parseHeroName(eventChunks[1]));
		castEventEntity.setSpellName(eventChunks[4]);
		castEventEntity.setReceiverHero(CommonUtility.parseHeroName(eventChunks[8]));
		matchDao.saveMatchEvent(castEventEntity);
	}

}
