package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public static String getDateAfterInterval(short interval) {
		return LocalDate.now().plusDays(interval).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	}
	public static String getDateAfterInterval(String bookDate, short interval) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
	    LocalDate date = LocalDate.parse(bookDate, formatter);
	    return date.plusDays(interval).format(formatter);
	}
}
