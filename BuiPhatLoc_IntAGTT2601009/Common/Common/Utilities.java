package Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import Constant.Constant;

public class Utilities {
	
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
	
	public static String genString() {
		String[] bioticString = {
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
		String[] abioticString = {
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
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmmss_"));
		
		Random random = new Random();
		String randomBiotic = bioticString[random.nextInt(bioticString.length)];
		String randomAbiotc = abioticString[random.nextInt(abioticString.length)];
		
		return timestamp + randomBiotic + randomAbiotc;
	}
}
