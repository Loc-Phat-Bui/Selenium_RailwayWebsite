package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Account;
import Common.WaitUtilities;

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
		this.getBtnLoginWebElement().click();
		
		return new HomePage();
	}
	
	public void clearTxtbox () {
		this.getTxtboxUsernameWebElement().clear();
		this.getTxtboxPasswordWebElement().clear();
	}
}
