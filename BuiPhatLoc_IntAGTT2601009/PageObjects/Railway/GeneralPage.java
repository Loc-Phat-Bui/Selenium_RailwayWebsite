package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class GeneralPage {
	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
	private final By lblWelomeMessage = By.xpath("//div[@class='account']/strong");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	
	// Elements
	protected WebElement getTabLoginWebElement() {
		return Constant.WEBDRIVER.findElement(this.tabLogin);
	}
	protected WebElement getTabLogoutWebElement() {
		return Constant.WEBDRIVER.findElement(this.tabLogout);
	}
	protected WebElement getTabChangePasswordWebElement() {
		return Constant.WEBDRIVER.findElement(this.tabChangePassword);
	}
	protected WebElement getLblWelcomeMessageWebElement() {
		return Constant.WEBDRIVER.findElement(this.lblWelomeMessage);
	}
	protected WebElement getTabRegisterWebElement() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}
	
	// Methods
	public String getWelcomeMessageString() {
		return this.getLblWelcomeMessageWebElement().getText();	
	}
	
	public boolean isLogoutDisplayed() {
		return this.getTabLogoutWebElement().isDisplayed();
	}
	
	public boolean isChangePasswordDisplayed() {
		return this.getTabChangePasswordWebElement().isDisplayed();
	}
	
	public LoginPage gotoLoginPage() {
		this.getTabLoginWebElement().click();
		return new LoginPage();
	}
	
	public RegisterPage gotoRegisterPage() {
		this.getTabRegisterWebElement().click();
		return new RegisterPage();
	}
}
