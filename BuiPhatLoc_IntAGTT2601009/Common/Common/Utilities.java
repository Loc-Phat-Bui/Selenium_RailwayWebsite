package Common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import Constant.Constant;

public class Utilities {
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
	
	public static String getProjectPath() {
        return System.getProperty("user.dir");
    }
	
	public static String[] splitEmail(String username) {
		String[] returnResult = username.split("@");
		return returnResult;
	}
	
	public static void switchToNewTab(String expectedUrl) {
	    String originalWindow = Constant.WEBDRIVER.getWindowHandle();

	    ((org.openqa.selenium.JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open()");

	    for (String windowHandle : Constant.WEBDRIVER.getWindowHandles()) {
	        if (!originalWindow.contentEquals(windowHandle)) {
	            Constant.WEBDRIVER.switchTo().window(windowHandle);
	            break;
	        }
	    }

	    Constant.WEBDRIVER.get(expectedUrl);
	}
	
	public static String genUsernameString() {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm_"));
		
		Random random = new Random();
		String randomBiotic = bioticString[random.nextInt(bioticString.length)];
		String randomAbiotic = abioticString[random.nextInt(abioticString.length)];
		String randomGaiaMemory = gaiaT2MemoryString[random.nextInt(gaiaT2MemoryString.length)];
		
//		return timestamp + randomGaiaMemory + "@grr.la"
		return timestamp + randomBiotic + randomAbiotic + "@grr.la";
	}
	
	public static String getDateForBookTicket(short interval) {
		return LocalDate.now().plusDays(interval).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	public static String getDateForBookTicket(String bookDate, short interval) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	    
	    LocalDate date = LocalDate.parse(bookDate, formatter);
	    
	    return date.plusDays(interval).format(formatter);
	}
}
