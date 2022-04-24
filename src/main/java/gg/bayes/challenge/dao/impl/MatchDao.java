package gg.bayes.challenge.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.bayes.challenge.dao.IMatchDao;
import gg.bayes.challenge.entity.EventEntity;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

@Repository
public class MatchDao implements IMatchDao {

	private EntityManager entityManager;

	@Autowired
	public MatchDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public void saveMatchEvent(EventEntity eventEntity) {
		entityManager.persist(eventEntity);
	}

	@Override
	public List<HeroKills> getHeroKills(Long matchId) {
		Query query = entityManager.createNativeQuery(
				"SELECT e.action_hero,count(k.receiver_hero) from event e left join kill_event k on e.ID=k.ID where e.match_id = ?1 group by e.action_hero");
		query.setParameter(1, matchId);
		List<Object[]> results = query.getResultList();
		List<HeroKills> heroKills = new ArrayList<>();
		for (Object[] obj : results) {
			HeroKills heroKill = new HeroKills();
			heroKill.setHero(obj[0].toString());
			heroKill.setKills((((BigInteger) obj[1]).intValue()));
			heroKills.add(heroKill);
		}
		return heroKills;
	}

	@Override
	public List<HeroItems> getHeroItems(Long matchId, String hero) {
		Query query = entityManager.createNativeQuery(
				"SELECT b.item,e.event_time from event e join buy_event b on e.ID=b.ID where e.match_id = ?1 and e.action_hero = ?2");
		query.setParameter(1, matchId);
		query.setParameter(2, hero);
		List<Object[]> results = query.getResultList();
		List<HeroItems> heroItems = new ArrayList<>();
		for (Object[] obj : results) {
			HeroItems heroItem = new HeroItems();
			heroItem.setItem(obj[0].toString());
			heroItem.setTimestamp(((BigInteger) obj[1]).longValue());
			heroItems.add(heroItem);
		}
		return heroItems;

	}

	@Override
	public List<HeroSpells> getHeroSpellsStatistics(Long matchId, String hero) {
		Query query = entityManager.createNativeQuery(
				"SELECT c.spell_name,count(spell_name) from event e join cast_event c on e.ID=c.ID where e.match_id = ?1 and e.action_hero = ?2 group by c.spell_name");
		query.setParameter(1, matchId);
		query.setParameter(2, hero);
		List<Object[]> results = query.getResultList();
		List<HeroSpells> heroSpells = new ArrayList<>();
		for (Object[] obj : results) {
			HeroSpells heroSpell = new HeroSpells();
			heroSpell.setSpell(obj[0].toString());
			heroSpell.setCasts(((BigInteger) obj[1]).intValue());
			heroSpells.add(heroSpell);
		}
		return heroSpells;
	}

	@Override
	public List<HeroDamage> getHeroDamageStatistics(Long matchId, String hero) {
		Query query = entityManager.createNativeQuery(
				"SELECT h.receiver_hero,count(receiver_hero),sum(damage_count) from event e join hit_event h on e.ID=h.ID where e.match_id = ?1 and e.action_hero = ?2 group by h.receiver_hero");
		query.setParameter(1, matchId);
		query.setParameter(2, hero);
		List<Object[]> results = query.getResultList();
		List<HeroDamage> heroDamages = new ArrayList<>();
		for (Object[] obj : results) {
			HeroDamage heroDamage = new HeroDamage();
			heroDamage.setTarget(obj[0].toString());
			heroDamage.setDamageInstances(((BigInteger) obj[1]).intValue());
			heroDamage.setTotalDamage(((BigInteger) obj[2]).intValue());
			heroDamages.add(heroDamage);
		}
		return heroDamages;
	}

	@Transactional
	@Override
	public Long getLastSavedMatchId() {
		Query query = entityManager.createNativeQuery("SELECT max(match_id) from event e");
		return query.getSingleResult() == null ? 0 : ((BigInteger) query.getSingleResult()).longValue();
	}

}
