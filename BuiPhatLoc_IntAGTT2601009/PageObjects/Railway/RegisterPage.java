package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Account;
import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Macros;

public class RegisterPage extends GeneralPage {
	// Locators
	private final By lblRegisterErrorMSG = By.xpath("//p[@class='message error']");
	private final By textRegisterContentParagraph = By.xpath("//div[@id='content']/p");
	private final By textRegisterContentHeader = By.xpath("//div[@id='content']/h1");
	private final By lblRegisterInvalidPasswordMSG = By.xpath("//label[@for='password' and @class='validation-error']");
	private final By lblRegisterInvalidPIDMSG = By.xpath("//label[@for='pid' and @class='validation-error']");
	// Elements
	protected WebElement getLblRegisterErrorMSGWebElement() {
		return WaitUtilities.waitForElementVisible(lblRegisterErrorMSG);
	}
	protected WebElement getTextRegisterContentParagraphWebElement() {
		return WaitUtilities.waitForElementVisible(textRegisterContentParagraph);
	}
	protected WebElement getTextRegisterContentHeaderWebElement() {
		return WaitUtilities.waitForElementVisible(textRegisterContentHeader);
	}
	protected WebElement getLblRegisterInvalidPasswordMSGWebElement() {
		return WaitUtilities.waitForElementVisible(lblRegisterInvalidPasswordMSG);
	}
	protected WebElement getLblRegisterInvalidPIDMSGWebElement() {
		return WaitUtilities.waitForElementVisible(lblRegisterInvalidPIDMSG);
	}
	// Methods
	public <T> T register(Account.AccountInfo account, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxEmail), account.getUsername());
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPassword), account.getPassword());
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxConfirmPassword), account.getPassword());	
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPID), account.getPID());
		SafetyUtilities.safeClick(this.getBtnWebElement(Macros.btnRegister));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public String getLblRegisterErrorMSGText() {
		return this.getLblRegisterErrorMSGWebElement().getText();
	}
	public String getTextRegisterContentParagraphText() {
		return this.getTextRegisterContentParagraphWebElement().getText();
	}
	public String getTextRegisterContentHeaderText() {
		return this.getTextRegisterContentHeaderWebElement().getText();
	}
	public String getLblRegisterInvalidPasswordMSGText() {
		return this.getLblRegisterInvalidPasswordMSGWebElement().getText();
	}
	public String getLblRegisterInvalidPIDMSGText() {
		return this.getLblRegisterInvalidPIDMSGWebElement().getText();
	}
}
