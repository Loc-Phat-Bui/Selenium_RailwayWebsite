package Common;

import java.awt.Desktop.Action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Constant.Constant;

public class ActionUtilities {
	
	public static void clickAction (WebElement webElement) {
		Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	    	removeAds();
	        actions.scrollToElement(webElement).perform();
	        webElement.click();
	    } catch (Exception e) {
	    	try {
	    		removeAds();
				actions.scrollByAmount(0, 300).perform();
				actions.moveToElement(webElement).click().perform();
			} catch (Exception ex) {
				removeAds();
				// Final Fallback: Force click via JavaScript
				((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", webElement);
			}
	    }
	}
	
	public static void selectByVisibleTextAction (WebElement webElement, String selectOptionName) {
		Actions actions = new Actions(Constant.WEBDRIVER);
        Select select = new Select(webElement);
	    
	    try {
	    	removeAds();
	        actions.scrollToElement(webElement).perform();
	        select.selectByVisibleText(selectOptionName);
	    } catch (Exception e) {
	        try {
	        	removeAds();
	            actions.scrollByAmount(0, 300).perform();
	            select.selectByVisibleText(selectOptionName);
	        } catch (Exception ex) {
	        	removeAds();
	        	// Final Fallback: Force selection via JavaScript (matches by text)
				String jsScript = "var sel = arguments[0]; " +
				                  "for(var i=0; i<sel.options.length; i++){ " +
				                  "  if(sel.options[i].text == arguments[1]){ " +
				                  "    sel.selectedIndex = i; " +
				                  "    sel.dispatchEvent(new Event('change')); " +
				                  "    break; " +
				                  "  } " +
				                  "}";
				((JavascriptExecutor) Constant.WEBDRIVER).executeScript(jsScript, webElement, selectOptionName);
			}
	    }
	}
	
	public static void selectByValueAction(WebElement webElement, String selectValue) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	    	removeAds();
	        actions.scrollToElement(webElement).perform();
	        Select select = new Select(webElement);
	        select.selectByValue(selectValue);
	    } catch (Exception e) {
	        try {
	        	removeAds();
	            actions.scrollByAmount(0, 300).perform();
	            new Select(webElement).selectByValue(selectValue);
	        } catch (Exception ex) {
	        	removeAds();
	            // Final Fallback: Force selection via JavaScript using the value attribute
	            String jsScript = "var sel = arguments[0]; " +
	                              "sel.value = arguments[1]; " + 
	                              "sel.dispatchEvent(new Event('change', { bubbles: true })); " +
	                              "sel.dispatchEvent(new Event('input', { bubbles: true }));";
	            
	            ((JavascriptExecutor) Constant.WEBDRIVER).executeScript(jsScript, webElement, selectValue);
	        }
	    }
	}
	
	public static void sendKeyAction(WebElement webElement, String keys) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	    	removeAds();
	        actions.scrollToElement(webElement).perform();
	        webElement.clear();
	        webElement.sendKeys(keys);
	    } catch (Exception e) {
	        try {
	        	removeAds();
//	            actions.moveToElement(webElement).click().perform();
//	            webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
		        actions.scrollByAmount(0, 300).perform();
		        webElement.clear();
		        webElement.sendKeys(keys);
	        } catch (Exception ex) {
	        	removeAds();
	        	// Final Fallback: Force value via JavaScript
	            ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].value='" + keys + "';", webElement);
	        }
	    }
	}
	
	public static void clickCheckBoxAction(WebElement webElement, boolean status) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	    	removeAds();
	        actions.scrollToElement(webElement).perform();
	        if (webElement.isSelected() != status) {
	        	webElement.click();
	        }
	    } catch (Exception e) {
	    	try {
	    		removeAds();
				actions.scrollByAmount(0, 300).perform();
				if (webElement.isSelected() != status) {
					actions.moveToElement(webElement).click().perform();
				}
			} catch (Exception ex) {
				removeAds();
				// Final Fallback: Force checkbox state via JavaScript
				((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].checked = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", webElement, status);
			}
	    }
	}
	
	public static void removeAds() {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
	}
}
