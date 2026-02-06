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
		String divPart = "";
		String hrefPart = "";
		switch (tabName.toLowerCase()) {
			case Macros.TAB_MENU_LOGIN: 
				divPart = "menu";
				hrefPart = "/Account/Login.cshtml"; 
				break;
			case Macros.TAB_MENU_LOGOUT: 
				divPart = "menu";
				hrefPart = "/Account/Logout"; 
				break;
			case Macros.TAB_MENU_CHANGE_PASSWORD: 
				divPart = "menu";
				hrefPart = "/Account/ChangePassword.cshtml"; 
				break;
			case Macros.TAB_MENU_REGISTER: 
				divPart = "menu";
				hrefPart = "/Account/Register.cshtml"; 
				break;
			case Macros.TAB_MENU_FAQ: 
				divPart = "menu";
				hrefPart = "/Page/FAQ.cshtml"; 
				break;
			case Macros.TAB_CONTENT_REGISTER: 
				divPart = "content";
				hrefPart = "/Account/Register.cshtml"; 
				break;
			case Macros.TAB_CONTENT_FORGOT_PASSWORD: 
				divPart = "content";
				hrefPart = "/Account/ForgotPassword.cshtml";
				break;
			default:
				break;
		}
		return String.format(tabElementXpath, divPart ,hrefPart);
	}
	public String getTxtBoxElemnentXpath (String txtxboxName) {
		String hrefPart = "";
		switch (txtxboxName.toLowerCase()) {
			case Macros.TXT_BOX_USERNAME: hrefPart = "username"; break;
			case Macros.TXT_BOX_EMAIL: hrefPart = "email"; break;
			case Macros.TXT_BOX_PASSWORD: hrefPart = "password"; break;
			case Macros.TXT_BOX_CONFIRM_PASSWORD: hrefPart = "confirmPassword"; break;
			case Macros.TXT_BOX_PID: hrefPart = "pid"; break;
			case Macros.TXT_BOX_CURRENT_PASSWORD: hrefPart = "currentPassword"; break;
			case Macros.TXT_BOX_NEW_PASSWORD: hrefPart = "newPassword"; break;
			default: break;
		}
		return String.format(txtboxElementXpath, hrefPart);
	}
	public String getBtnElemnentXpath (String btnName) {
		String hrefPart = "";
		switch (btnName.toLowerCase()) {
			case Macros.BTN_LOGIN: hrefPart = "login"; break;
			case Macros.BTN_REGISTER: hrefPart = "Register"; break;
			case Macros.BTN_CHANGE_PASSWORD: hrefPart = "Change Password"; break;
			case Macros.BTN_RESET_PASSWORD: hrefPart = "Reset Password"; break; 
			case Macros.BTN_SEND_INSTRUCTIONS: hrefPart = "Send Instructions"; break;
			default: break;
		}
		return String.format(btnElementXpath, hrefPart);
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
