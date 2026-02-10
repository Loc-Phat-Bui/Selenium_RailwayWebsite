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
	    	try {
				actions.scrollByAmount(0, 300).perform();
				actions.moveToElement(webElement).click().perform();
			} catch (Exception ex) {
				// Final Fallback: Force click via JavaScript
				((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", webElement);
			}
	    }
	}
	
	public static void safeSelectByVisibleText (WebElement webElement, String selectOptionName) {
		Actions actions = new Actions(Constant.WEBDRIVER);
        Select select = new Select(webElement);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        select.selectByVisibleText(selectOptionName);
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, 300).perform();
	            select.selectByVisibleText(selectOptionName);
	        } catch (Exception ex) {
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
	
	public static void safeSelectByValue(WebElement webElement, String selectValue) {
	    Actions actions = new Actions(Constant.WEBDRIVER);
	    
	    try {
	        actions.scrollToElement(webElement).perform();
	        Select select = new Select(webElement);
	        select.selectByValue(selectValue);
	    } catch (Exception e) {
	        try {
	            actions.scrollByAmount(0, 300).perform();
	            new Select(webElement).selectByValue(selectValue);
	        } catch (Exception ex) {
	            // Final Fallback: Force selection via JavaScript using the value attribute
	            String jsScript = "var sel = arguments[0]; " +
	                              "sel.value = arguments[1]; " + 
	                              "sel.dispatchEvent(new Event('change', { bubbles: true })); " +
	                              "sel.dispatchEvent(new Event('input', { bubbles: true }));";
	            
	            ((JavascriptExecutor) Constant.WEBDRIVER).executeScript(jsScript, webElement, selectValue);
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
	        	// Final Fallback: Force value via JavaScript
	            ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].value='" + keys + "';", webElement);
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
	    	try {
				actions.scrollByAmount(0, 300).perform();
				if (webElement.isSelected() != status) {
					actions.moveToElement(webElement).click().perform();
				}
			} catch (Exception ex) {
				// Final Fallback: Force checkbox state via JavaScript
				((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].checked = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", webElement, status);
			}
	    }
	}
}
