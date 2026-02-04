package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class LoginPage extends GeneralPage {
	// Locators
	private final By txtboxUsername = By.xpath("//input[@id='username']");
	private final By txtboxPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	// Elements
	protected WebElement getTxtboxUsernameWebElement() {
		return Constant.WEBDRIVER.findElement(txtboxUsername);
	}
	protected WebElement getTxtboxPasswordWebElement() {
		return Constant.WEBDRIVER.findElement(txtboxPassword);
	}
	protected WebElement getBtnLoginWebElement() {
		return Constant.WEBDRIVER.findElement(btnLogin);
	}
	protected WebElement getLblLoginErrorMsgWebElement() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}
	
	// Methods
	public String getLblLoginErrorMsgText() {
		return this.getLblLoginErrorMsgWebElement().getText();
	}
	
	public HomePage login(String username, String password) {
		this.getTxtboxUsernameWebElement().sendKeys(username);
		this.getTxtboxPasswordWebElement().sendKeys(password);
		this.getBtnLoginWebElement().click();
		
		return new HomePage();
	}
	
	public void clearTxtbox () {
		this.getTxtboxUsernameWebElement().clear();
		this.getTxtboxPasswordWebElement().clear();
	}
}
