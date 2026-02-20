package AutomationExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationExerciseData.AccountData.Account;
import Common.ActionUtilities;
import Common.WaitUtilities;

public class LoginPage extends GeneralPage{
	/*
	** LOCATORS
	*/
	private final By inputLoginEmail = By.xpath("//input[@data-qa='login-email']");
	private final By inputLoginPassword = By.xpath("//input[@data-qa='login-password']");
	private final By inputSignupName = By.xpath("//input[@data-qa='signup-name']");
	private final By inputSignupEmail = By.xpath("//input[@data-qa='signup-email']");
	
	private final By buttonLogin = By.xpath("//button[@data-qa='login-button']");
	private final By buttonSignup = By.xpath("//button[@data-qa='signup-button']");
	/*
	** ELEMENTS
	*/
	protected WebElement getElement_InputLoginEmail() {
		return WaitUtilities.waitForElementVisible(inputLoginEmail);
	}
	protected WebElement getElement_InputLoginPassword() {
		return WaitUtilities.waitForElementVisible(inputLoginPassword);
	}
	protected WebElement getElement_InputSignupName() {
		return WaitUtilities.waitForElementVisible(inputSignupName);
	}
	protected WebElement getElement_InputSignupEmail() {
		return WaitUtilities.waitForElementVisible(inputSignupEmail);
	}
	protected WebElement getElement_ButtonLogin() {
		return WaitUtilities.waitForElementClickable(buttonLogin);
	}
	protected WebElement getElement_ButtonSignup() {
		return WaitUtilities.waitForElementClickable(buttonSignup);
	}
	/*
	** METHODS
	*/
	public <T> T login(Account account, Class<T> returnPage) {
		ActionUtilities.sendKeyAction(getElement_InputLoginEmail(), account.getEmail());
		ActionUtilities.sendKeyAction(getElement_InputLoginPassword(), account.getPassword());
		
		ActionUtilities.clickAction(getElement_ButtonLogin());
		
	    try {
	        return returnPage.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Could not create instance of " + returnPage);
	    }
	}
	
	public <T> T signup(Account account, Class<T> returnPage) {
		ActionUtilities.sendKeyAction(getElement_InputSignupName(), account.getName());
		ActionUtilities.sendKeyAction(getElement_InputSignupEmail(), account.getEmail());
		
		ActionUtilities.clickAction(getElement_ButtonSignup());
		
	    try {
	        return returnPage.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Could not create instance of " + returnPage);
	    }
	}
}
