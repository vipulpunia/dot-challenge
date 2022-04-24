package gg.bayes.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.entity.BuyEventEntity;
import gg.bayes.challenge.service.IEventHandler;
import gg.bayes.challenge.util.CommonUtility;

@Service(value = "buyevent")
public class BuyEventHandler implements IEventHandler {

	private IMatchDao matchDao;

	@Autowired
	public BuyEventHandler(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Override
	public void processMatchEvent(Long matchId, String eventDetail) {
		String eventChunks[] = eventDetail.split(" ");
		BuyEventEntity buyEventEntity = new BuyEventEntity();
		buyEventEntity.setMatchId(matchId);
		buyEventEntity.setEventTime(CommonUtility.convertToMillis(eventChunks[0]));
		buyEventEntity.setEventActionHero(CommonUtility.parseHeroName(eventChunks[1]));
		buyEventEntity.setItem(CommonUtility.parseItem(eventChunks[4]));
		matchDao.saveMatchEvent(buyEventEntity);
	}

}
