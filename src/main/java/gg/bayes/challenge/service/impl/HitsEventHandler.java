package gg.bayes.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.entity.HitsEventEntity;
import gg.bayes.challenge.service.IEventHandler;
import gg.bayes.challenge.util.CommonUtility;

@Service(value = "hitsevent")
public class HitsEventHandler implements IEventHandler {

	private IMatchDao matchDao;

	@Autowired
	public HitsEventHandler(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public void processMatchEvent(Long matchId, String eventDetail) {
		String eventChunks[] = eventDetail.split(" ");
		HitsEventEntity hitsEventEntity = new HitsEventEntity();
		hitsEventEntity.setMatchId(matchId);
		hitsEventEntity.setEventTime(CommonUtility.convertToMillis(eventChunks[0]));
		hitsEventEntity.setEventActionHero(CommonUtility.parseHeroName(eventChunks[1]));
		hitsEventEntity.setReceiverHero(CommonUtility.parseHeroName(eventChunks[3]));
		hitsEventEntity.setDamageBy(CommonUtility.parseDamagedBy(eventChunks[5]));
		hitsEventEntity.setDamageCount(Integer.parseInt(eventChunks[7]));
		matchDao.saveMatchEvent(hitsEventEntity);
	}

}
