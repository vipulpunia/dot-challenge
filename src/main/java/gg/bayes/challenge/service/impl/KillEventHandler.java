package gg.bayes.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.entity.KillEventEntity;
import gg.bayes.challenge.service.IEventHandler;
import gg.bayes.challenge.util.CommonUtility;

@Service(value = "killevent")
public class KillEventHandler implements IEventHandler {

	private IMatchDao matchDao;

	@Autowired
	public KillEventHandler(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public void processMatchEvent(Long matchId, String eventDetail) {
		String eventChunks[] = eventDetail.split(" ");
		KillEventEntity killEventEntity = new KillEventEntity();
		killEventEntity.setMatchId(matchId);
		killEventEntity.setEventTime(CommonUtility.convertToMillis(eventChunks[0]));
		killEventEntity.setEventActionHero(CommonUtility.parseHeroName(eventChunks[5]));
		killEventEntity.setReceiverHero(CommonUtility.parseHeroName(eventChunks[1]));
		matchDao.saveMatchEvent(killEventEntity);
	}

}
