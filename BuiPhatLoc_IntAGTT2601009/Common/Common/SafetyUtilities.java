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
	        actions.moveToElement(webElement).click().perform();
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, -150).perform();
	            actions.moveToElement(webElement).click().perform();
	        } catch (Exception e2) {
	            ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", webElement);
	        }
	    }
	}
	
	public static void safeSelect (WebElement webElement, String selectOption) {
		Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        Select select = new Select(webElement);
	        select.selectByVisibleText(selectOption);
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, 100).perform();
	            new Select(webElement).selectByVisibleText(selectOption);
	        } catch (Exception ex) {
	            System.err.println("Failed to select option: " + selectOption + ". Error: " + ex.getMessage());
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
	        	actions.moveToElement(webElement).click().perform();
	        }
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, -100).perform();
	            if (webElement.isSelected() != status) {
	            	actions.moveToElement(webElement).click().perform();
	            }
	        } catch (Exception ex) {
	            if (webElement.isSelected() != status) {
	                ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", webElement);
	            }
	        }
	    }
	}
}
