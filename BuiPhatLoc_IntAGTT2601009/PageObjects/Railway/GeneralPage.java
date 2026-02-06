package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Constant;
import Constant.Macros;

public class GeneralPage {
	/* 
	** Locators
	**/
	private String tabElementXpath = "//div[@id='%s']//a[contains(@href,'%s')]";
	private String txtboxElementXpath = "//input[@id='%s']";
	private String btnElementXpath	=	"//input[@value='%s']";
	private final By lblWelomeMessage = By.xpath("//div[@class='account']/strong");
	
	/* 
	** Elements
	**/
	// General elements
	protected WebElement getTabWebElement(String tabName) {	
		return WaitUtilities.waitForElementClickable(By.xpath(getTabElementXpath(tabName)));
	}
	protected WebElement getTxtBoxWebElement (String txtboxName) {
		return WaitUtilities.waitForElementVisible(By.xpath(getTxtBoxElemnentXpath(txtboxName)));
	}
	protected WebElement getBtnWebElement (String btnName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getBtnElemnentXpath(btnName)));
	}
	// Specific elements
	protected WebElement getLblWelcomeMessageWebElement() {
		return Constant.WEBDRIVER.findElement(this.lblWelomeMessage);
	}
	/* 
	** Methods
	**/
	// ------ Generate Xpath ------ //
	public String getTabElementXpath (String tabName) {
		int index = tabName.indexOf('/');
	    if (index == -1) {
	        return String.format(tabElementXpath, tabName, "");
	    }
	    String divPart = tabName.substring(0, index);
	    String hrefPart = tabName.substring(index + 1);
	    
		return String.format(tabElementXpath, divPart ,hrefPart);
	}
	public String getTxtBoxElemnentXpath (String txtxboxName) {
		return String.format(txtboxElementXpath, txtxboxName);
	}
	public String getBtnElemnentXpath (String btnName) {
		return String.format(btnElementXpath, btnName);
	}
	
	// ------ Check Functions ------ //
	public boolean checkTabElementAvailable(String tabName) {
		return !Constant.WEBDRIVER.findElements(By.xpath(getTabElementXpath(tabName))).isEmpty();
	}
	public boolean checkPageURL(String tabName) {
		WebElement tab = getTabWebElement(tabName);
		String expectedHref = tab.getAttribute("href");
		String pageURL = Constant.WEBDRIVER.getCurrentUrl();
		
		return pageURL.contains(expectedHref);
	}
	
	// ------ Goto Page by clicking Tab Menu/Content link ------ //
	public <T> T gotoTabPage (String tabName, Class<T> returnPage) {
		SafetyUtilities.safeClick(this.getTabWebElement(tabName));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	// ------ Get text from the website ------ //
	public String getWelcomeMessageString() {
		return this.getLblWelcomeMessageWebElement().getText();	
	}
}
