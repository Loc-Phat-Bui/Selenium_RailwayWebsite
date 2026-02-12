package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Constant;
import RailwayEnum.Button;
import RailwayEnum.TabContent;
import RailwayEnum.TabMenu;
import RailwayEnum.TextBox;
import RailwayEnum.ValidationError;

public class GeneralPage {
	private String tabElementXpath = "//div[@id='%s']//a[contains(@href,'%s')]";
	private String txtboxElementXpath = "//input[@id='%s']";
	private String btnElementXpath	=	"//input[@value='%s']";
	private String labelValidationErrorXpath = "//label[contains(@for,'%s') and contains(@class,'validation-error')]";
	/* 
	** Locators
	*/
	private final By lblWelomeMessage = By.xpath("//div[@class='account']/strong");
	private final By lblErrorMSG = By.xpath("//p[@class='message error']");
	// ------ Generate Xpath ------ //
	public String getTabElementXpath (TabMenu tabMenu) {
		return String.format(tabElementXpath, tabMenu.getDiv() ,tabMenu.getHref());
	}
	public String getTabElementXpath (TabContent tabContent) {
		return String.format(tabElementXpath, tabContent.getDiv() ,tabContent.getHref());
	}
	public String getTxtBoxElemnentXpath (TextBox txtBox) {
		return String.format(txtboxElementXpath, txtBox.getValue());
	}
	public String getBtnElemnentXpath (Button btnName) {
		return String.format(btnElementXpath, btnName.getValue());
	}
	public String getLabelValidationErrorXpath (ValidationError labelName) {
	    
		return String.format(labelValidationErrorXpath, labelName.getValue());
	}
	/* 
	** Elements
	*/
	// General elements
	protected WebElement getTabWebElement(TabMenu tabMenu) {	
		return WaitUtilities.waitForElementClickable(By.xpath(getTabElementXpath(tabMenu)));
	}
	protected WebElement getTabWebElement(TabContent tabContent) {	
		return WaitUtilities.waitForElementClickable(By.xpath(getTabElementXpath(tabContent)));
	}
	protected WebElement getTxtBoxWebElement (TextBox txtBox) {
		return WaitUtilities.waitForElementVisible(By.xpath(getTxtBoxElemnentXpath(txtBox)));
	}
	protected WebElement getBtnWebElement (Button btnName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getBtnElemnentXpath(btnName)));
	}
	protected WebElement getLabelValidationErrorWebElement(ValidationError labelName) {
		return  WaitUtilities.waitForElementClickable(By.xpath(getLabelValidationErrorXpath(labelName)));
	}
	protected WebElement getLblErrorMSGWebElement() {
		return WaitUtilities.waitForElementVisible(lblErrorMSG);
	}
	// Specific elements
	protected WebElement getLblWelcomeMessageWebElement() {
		return Constant.WEBDRIVER.findElement(this.lblWelomeMessage);
	}
	/* 
	** Methods
	*/
	// ------ Check Functions ------ //
	public boolean checkTabElementAvailable(TabMenu tabMenu) {
		return !Constant.WEBDRIVER.findElements(By.xpath(getTabElementXpath(tabMenu))).isEmpty();
	}
	public boolean checkTabElementAvailable(TabContent tabContent) {
		return !Constant.WEBDRIVER.findElements(By.xpath(getTabElementXpath(tabContent))).isEmpty();
	}
	public boolean checkPageURL(TabMenu tabMenu) {
		WebElement tab = getTabWebElement(tabMenu);
		String expectedHref = tab.getAttribute("href");
		String pageURL = Constant.WEBDRIVER.getCurrentUrl();
		
		return pageURL.contains(expectedHref);
	}
	
	// ------ Goto Page by clicking Tab Menu/Content link ------ //
	public <T> T gotoTabPage (TabMenu tabMenu, Class<T> returnPage) {
		SafetyUtilities.safeClick(this.getTabWebElement(tabMenu));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	public <T> T gotoTabPage (TabContent tabContent, Class<T> returnPage) {
		SafetyUtilities.safeClick(this.getTabWebElement(tabContent));
		
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
	public String getLabelValidationErrorText(ValidationError labelName) {
		return getLabelValidationErrorWebElement(labelName).getText();
	}
}
