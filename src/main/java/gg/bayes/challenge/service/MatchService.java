package gg.bayes.challenge.service;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

public interface MatchService {
	Long ingestMatch(String payload);

	List<HeroKills> getHeroKills(Long matchId);

	List<HeroItems> getHeroItems(Long matchId, String hero);

	List<HeroSpells> getHeroSpellsStatistics(Long matchId, String hero);

	List<HeroDamage> getHeroDamageStatistics(Long matchId, String hero);
}
