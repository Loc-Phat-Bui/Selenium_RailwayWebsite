package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class WaitUtilities {
	private static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
	
	public static WebElement waitForElementVisible(By locator) {
		WebDriverWait waitForElement = new WebDriverWait(Constant.WEBDRIVER, DEFAULT_TIMEOUT);
		return waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForElementClickable (By locator) {
		WebDriverWait waitForElement = new WebDriverWait(Constant.WEBDRIVER, DEFAULT_TIMEOUT);
		return waitForElement.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
