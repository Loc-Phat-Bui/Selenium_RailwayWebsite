package AutomationExerciseData;

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
			"Firetruck",
			"Cleaner",
			"Lock",
			"Train",
			"Light"
	};
	private static Random random = new Random();
	
	public static String genBiotic () {
		random = new Random();
		return bioticString[random.nextInt(bioticString.length)];
	}
	public static String genAbiotic () {
		random = new Random();
		return abioticString[random.nextInt(abioticString.length)];
	}
	public static String genUsernameBuild(String biotic, String abiotic) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("_yyMMddHHmm"));
		return biotic + abiotic + timestamp + "@test.com";
	}
}
