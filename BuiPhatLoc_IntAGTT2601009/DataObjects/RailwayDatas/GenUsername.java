package RailwayDatas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenUsername {
	private static final String[] bioticString = {
			"Rabbit",
			"Gorilla",
			"Hawk",
			"Ninja",
			"Panda",
			"Hedgehog",
			"Lion",
			"Dragon",
			"Pirate",
			"Octopus"
	};
	private static final String[] abioticString = {
			"Tank",
			"Diamond",
			"Gatling",
			"Comic",
			"Rocket",
			"Fire",
			"Cleaner",
			"Lock",
			"Train",
			"Light"
	};
	private static final String[] gaiaT2MemoryString = {
			"Accel",
			"Bird",
			"Cyclone",
			"Dummy",
			"Eternal",
			"Fang",
			"Gene",
			"Heat",
			"Iceage",
			"Joker",
			"Key",
			"Luna",
			"Metal",
			"Nasca",
			"Ocean",
			"Puppeteer",
			"Queen",
			"Rocket",
			"Skull",
			"Trigger",
			"Unicorn",
			"Violence",
			"Weather",
			"Xtreme",
			"Yesterday",
			"Zone"
	};
	
	public static String genUsernameBuild() {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm_"));
		Random random = new Random();
		String randomBiotic = bioticString[random.nextInt(bioticString.length)];
		String randomAbiotic = abioticString[random.nextInt(abioticString.length)];
		return timestamp + randomBiotic + randomAbiotic + "@grr.la";
	}
	public static String genUsernameW() {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm_"));
		Random random = new Random();
		String randomGaiaMemory = gaiaT2MemoryString[random.nextInt(gaiaT2MemoryString.length)];
		return timestamp + randomGaiaMemory + "@spam4.me";
	}
}
