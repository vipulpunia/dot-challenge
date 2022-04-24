package gg.bayes.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.enums.EventEnum;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.IEventHandler;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

	private IEventHandler buyEventHandler;
	private IEventHandler castEventHandler;
	private IEventHandler hitsEventHandler;
	private IEventHandler killEventHandler;
	private IMatchDao matchDao;

	@Autowired
	public MatchServiceImpl(IMatchDao matchDao, @Qualifier("buyevent") IEventHandler buyEventHandler,
			@Qualifier("castevent") IEventHandler castEventHandler,
			@Qualifier("hitsevent") IEventHandler hitsEventHandler,
			@Qualifier("killevent") IEventHandler killEventHandler) {
		this.matchDao = matchDao;
		this.buyEventHandler = buyEventHandler;
		this.castEventHandler = castEventHandler;
		this.hitsEventHandler = hitsEventHandler;
		this.killEventHandler = killEventHandler;
	}

	@Override
	public Long ingestMatch(String payload) {
		Long matchId = matchDao.getLastSavedMatchId() + 1;
		String eventDetails[] = payload.split("\\n");
		for (int i = 0; i < eventDetails.length; i++) {
			String currentEventDetail = eventDetails[i];
			switch (this.getEventType(currentEventDetail)) {
			case BUY:
				buyEventHandler.processMatchEvent(matchId, currentEventDetail);
				break;
			case CAST:
				castEventHandler.processMatchEvent(matchId, currentEventDetail);
				break;
			case HIT:
				hitsEventHandler.processMatchEvent(matchId, currentEventDetail);
				break;
			case KILL:
				killEventHandler.processMatchEvent(matchId, currentEventDetail);
				break;
			default:
				log.info("Ignoring this event");
			}

		}
		return matchId;
	}

	@Override
	public List<HeroKills> getHeroKills(Long matchId) {
		return matchDao.getHeroKills(matchId);
	}

	@Override
	public List<HeroItems> getHeroItems(Long matchId, String hero) {
		return matchDao.getHeroItems(matchId, hero);
	}

	@Override
	public List<HeroSpells> getHeroSpellsStatistics(Long matchId, String hero) {
		return matchDao.getHeroSpellsStatistics(matchId, hero);
	}

	@Override
	public List<HeroDamage> getHeroDamageStatistics(Long matchId, String hero) {
		return matchDao.getHeroDamageStatistics(matchId, hero);
	}

	private EventEnum getEventType(String eventDetail) {
		String eventChunk[] = eventDetail.split(" ");
		if (eventChunk[2].equalsIgnoreCase(EventEnum.BUY.getDescription())) {
			return EventEnum.BUY;
		} else if (eventChunk[2].equalsIgnoreCase(EventEnum.CAST.getDescription())) {
			return EventEnum.CAST;
		} else if (eventChunk[2].equalsIgnoreCase(EventEnum.HIT.getDescription())) {
			return EventEnum.HIT;
		} else if (eventChunk[3].equalsIgnoreCase(EventEnum.KILL.getDescription())) {
			return EventEnum.KILL;
		} else {
			return EventEnum.OTHER;
		}
	}
}
