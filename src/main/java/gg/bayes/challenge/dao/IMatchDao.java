package gg.bayes.challenge.dao;

import java.util.List;

import gg.bayes.challenge.entity.EventEntity;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

public interface IMatchDao {

	void saveMatchEvent(EventEntity eventEntity);

	List<HeroKills> getHeroKills(Long matchId);

	List<HeroItems> getHeroItems(Long matchId, String hero);

	List<HeroSpells> getHeroSpellsStatistics(Long matchId, String hero);

	List<HeroDamage> getHeroDamageStatistics(Long matchId, String hero);

	Long getLastSavedMatchId();

}
