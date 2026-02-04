package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Account.Account;
import Common.WaitUtilities;
import Constant.Constant;

public class LoginPage extends GeneralPage { 
	
	// Locators
	private final By txtboxUsername = By.xpath("//input[@id='username']");
	private final By txtboxPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	// Elements
	protected WebElement getTxtboxUsernameWebElement() {
		return WaitUtilities.waitForElementVisible(txtboxUsername);
	}
	protected WebElement getTxtboxPasswordWebElement() {
		return WaitUtilities.waitForElementVisible(txtboxPassword);
	}
	protected WebElement getBtnLoginWebElement() {
		return WaitUtilities.waitForElementClickable(btnLogin);
	}
	protected WebElement getLblLoginErrorMsgWebElement() {
		return WaitUtilities.waitForElementVisible(lblLoginErrorMsg);
	}
	
	// Methods
	public String getLblLoginErrorMsgText() {
		return this.getLblLoginErrorMsgWebElement().getText();
	}
	
	public HomePage login(Account.AccountInfo account) {
		this.getTxtboxUsernameWebElement().sendKeys(account.getUsername());
		this.getTxtboxPasswordWebElement().sendKeys(account.getPassword());
		
		WebElement buttonLogin = this.getBtnLoginWebElement();
		Actions actions = new Actions(Constant.WEBDRIVER);
		
		try {
			actions.scrollToElement(buttonLogin).perform();
			
			buttonLogin.click();
		} catch (ElementClickInterceptedException e) {
			actions.scrollByAmount(0, 100).perform();
			actions.moveToElement(buttonLogin).click().perform();
		}

		
		return new HomePage();
	}
	
	public void clearTxtbox () {
		this.getTxtboxUsernameWebElement().clear();
		this.getTxtboxPasswordWebElement().clear();
	}
}
