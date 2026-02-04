package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Account.Account;
import Common.WaitUtilities;
import Constant.Constant;

public class RegisterPage {
	// Locators
	private final By txtboxEmail = By.xpath("//input[@id='email']");
	private final By txtboxPassword = By.xpath("//input[@id='password']");
	private final By txtboxConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtboxPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");
	
	// Elements
	protected WebElement getTxtboxEmailElement() {
		return WaitUtilities.waitForElementVisible(txtboxEmail);
	}
	protected WebElement getTxtboxPasswordElement() {
		return WaitUtilities.waitForElementVisible(txtboxPassword);
	}
	protected WebElement getTxtboxConfirmPasswordElement() {
		return WaitUtilities.waitForElementVisible(txtboxConfirmPassword);
	}
	protected WebElement getTxtboxPIDElement() {
		return WaitUtilities.waitForElementVisible(txtboxPID);
	}
	protected WebElement getBtnRegisterElement() {
		return WaitUtilities.waitForElementClickable(btnRegister);
	}
	
	// Methods
	public void clearTxtBox() {
		this.getTxtboxEmailElement().clear();
		this.getTxtboxPasswordElement().clear();
		this.getTxtboxConfirmPasswordElement().clear();
		this.getTxtboxPIDElement().clear();
	}
	
	public HomePage register(Account.AccountInfo account) {
		
		this.clearTxtBox();
		this.getTxtboxEmailElement().sendKeys(account.getUsername());
		this.getTxtboxPasswordElement().sendKeys(account.getPassword());
		this.getTxtboxConfirmPasswordElement().sendKeys(account.getPassword());
		this.getTxtboxPIDElement().sendKeys(account.getPID());
		
		WebElement buttonRegister = this.getBtnRegisterElement();
		Actions actions = new Actions(Constant.WEBDRIVER);
		
		try {
			actions.scrollToElement(buttonRegister).perform();
			buttonRegister.click();
		} catch (ElementClickInterceptedException e) {
			actions.scrollByAmount(0, 100).perform();
			actions.moveToElement(buttonRegister).click().perform();
		}
		
		return new HomePage();
	}
}
