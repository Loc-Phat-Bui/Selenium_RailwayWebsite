package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Common.Utilities;
import Common.WaitUtilities;
import Constant.Constant;
import Constant.Macros;

public class GeneralPage {
	/* 
	** Locators
	**/
	private String tabElementXpath = "//div[@id='menu']//a[contains(@href,'%s')]";
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
		return WaitUtilities.waitForElementClickable(By.xpath(getTxtBoxElemnentXpath(txtboxName)));
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
	// General methods
	public String getTabElementXpath (String tabName) {
		String hrefPart = "";
		switch (tabName.toLowerCase()) {
			case Macros.tabLogin: hrefPart = "/Account/Login.cshtml"; break;
			case Macros.tabLogout: hrefPart = "/Account/Logout"; break;
			case Macros.tabChangePassword: hrefPart = "/Account/ChangePassword.cshtml"; break;
			case Macros.tabRegister: hrefPart = "/Account/Register.cshtml"; break;
			case Macros.tabFAQ: hrefPart = "/Page/FAQ.cshtml"; break;
		}
		return String.format(tabElementXpath, hrefPart);
	}
	public String getTxtBoxElemnentXpath (String txtxboxName) {
		String hrefPart = "";
		switch (txtxboxName.toLowerCase()) {
			case Macros.txtboxUsername: hrefPart = "username"; break;
			case Macros.txtboxEmail: hrefPart = "email"; break;
			case Macros.txtboxPassword: hrefPart = "password"; break;
			case Macros.txtboxConfirmPassword: hrefPart = "confirmPassword"; break;
			case Macros.txtboxPID: hrefPart = "pid"; break;
		}
		return String.format(txtboxElementXpath, hrefPart);
	}
	public String getBtnElemnentXpath (String btnName) {
		String hrefPart = "";
		switch (btnName.toLowerCase()) {
			case Macros.btnLogin: hrefPart = "login"; break;
			case Macros.btnRegister: hrefPart = "register"; break;
		}
		return String.format(btnElementXpath, hrefPart);
	}
	
	public boolean checkTabElementAvailable(String tabName) {
		return !Constant.WEBDRIVER.findElements(By.xpath(getTabElementXpath(tabName))).isEmpty();
	}
	
	public <T> T gotoTabPage (String tabName, Class<T> returnPage) {
		Utilities.safeClick(this.getTabWebElement(tabName));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	// Specific methods
	public String getWelcomeMessageString() {
		return this.getLblWelcomeMessageWebElement().getText();	
	}
}
