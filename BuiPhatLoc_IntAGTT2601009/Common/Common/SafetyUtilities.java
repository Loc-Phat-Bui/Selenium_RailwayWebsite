package Common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Constant.Constant;

public class SafetyUtilities {
	
	public static void safeClick (WebElement webElement) {
		Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        webElement.click();
	    } catch (Exception e) {
            actions.scrollByAmount(0, 300).perform();
            actions.moveToElement(webElement).click().perform();
	    }
	}
	
	public static void safeSelect (WebElement webElement, String selectOptionName) {
		Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        Select select = new Select(webElement);
	        select.selectByVisibleText(selectOptionName);
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, 100).perform();
	            new Select(webElement).selectByVisibleText(selectOptionName);
	        } catch (Exception ex) {
	            System.err.println("Failed to select option: " + selectOptionName + ". Error: " + ex.getMessage());
	        }
	    }
	}
	
	public static void safeSendkey(WebElement webElement, String keys) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        webElement.clear();
	        webElement.sendKeys(keys);
	    } catch (Exception e) {
	        try {
	            actions.moveToElement(webElement).click().perform();
	            webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
	            webElement.sendKeys(keys);
	        } catch (Exception ex) {
	            ((JavascriptExecutor) Constant.WEBDRIVER)
	                .executeScript("arguments[0].value='" + keys + "';", webElement);
	        }
	    }
	}
	
	public static void safeCheckChkBox(WebElement webElement, boolean status) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        if (webElement.isSelected() != status) {
	        	webElement.click();
	        }
	    } catch (Exception e) {
            actions.scrollByAmount(0, 300).perform();
            if (webElement.isSelected() != status) {
            	webElement.click();
            }
	    }
	}
}
