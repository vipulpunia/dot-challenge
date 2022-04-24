package gg.bayes.challenge.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.apache.commons.lang3.StringUtils;

public class CommonUtility {

	private static final String PREFIX_HERO_NAME = "npc_dota_hero_";
	private static final String PREFIX_NPC_DOTA = "npc_dota_";
	private static final String PREFIX_ITEM_NAME = "item_";

	public static long convertToMillis(String timeString) {
		LocalTime localTime = LocalTime.parse(timeString.substring(1, timeString.indexOf("]")));
		return ChronoUnit.MILLIS.between(LocalTime.MIDNIGHT, localTime);
	}

	public static String parseHeroName(String heroName) {
		if ("dota_unknown".equals(heroName)) {
			return heroName;
		} else if (heroName.indexOf(PREFIX_HERO_NAME) == StringUtils.INDEX_NOT_FOUND) {
			return StringUtils.substringAfter(heroName, PREFIX_NPC_DOTA);
		} else {
			return StringUtils.substringAfter(heroName, PREFIX_HERO_NAME);
		}
	}

	public static String parseItem(String item) {
		return StringUtils.substringAfter(item, PREFIX_ITEM_NAME);
	}

	public static String parseDamagedBy(String damagedBy) {
		if (damagedBy.indexOf(PREFIX_ITEM_NAME) == StringUtils.INDEX_NOT_FOUND) {
			return damagedBy;
		} else {
			return parseItem(damagedBy);
		}
	}
}
