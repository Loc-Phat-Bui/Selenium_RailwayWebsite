package AutomationExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationExerciseData.AccountData.Account;
import Common.ActionUtilities;
import Common.WaitUtilities;

public class SignUpPage extends GeneralPage{
	/*
	** LOCATORS
	*/
	private final By radioTitleMr = By.xpath("//div[contains(@class,'login-form')]//input[@value='Mr']");
	private final By radioTitleMrs = By.xpath("//div[contains(@class,'login-form')]//input[@value='Mrs']");
	
	private final By inputName = By.xpath("//div[contains(@class,'login-form')]//input[@id='name']");
	private final By inputEmail = By.xpath("//div[contains(@class,'login-form')]//input[@id='email']");
	private final By inputPassword = By.xpath("//div[contains(@class,'login-form')]//input[@id='password']");
	
	private final By selectDay = By.xpath("//div[contains(@class,'login-form')]//select[@id='days']");
	private final By selectMonth = By.xpath("//div[contains(@class,'login-form')]//select[@id='months']");
	private final By selectYear = By.xpath("//div[contains(@class,'login-form')]//select[@id='years']");
	
	private final By checkboxNewsletter = By.xpath("//div[contains(@class,'login-form')]//input[@name='newsletter']");
	private final By checkboxSpecialOffers = By.xpath("//div[contains(@class,'login-form')]//input[@name='optin']");
	
	private final By inputFirstName = By.xpath("//div[contains(@class,'login-form')]//input[@id='first_name']");
	private final By inputLastName = By.xpath("//div[contains(@class,'login-form')]//input[@id='last_name']");
	private final By inputCompany = By.xpath("//div[contains(@class,'login-form')]//input[@id='company']");
	private final By inputAddress = By.xpath("//div[contains(@class,'login-form')]//input[@id='address1']");
	private final By inputAddress2 = By.xpath("//div[contains(@class,'login-form')]//input[@id='address2']");
	
	private final By selectCountry = By.xpath("//div[contains(@class,'login-form')]//select[@id='country']");
	
	private final By inputState = By.xpath("//div[contains(@class,'login-form')]//input[@id='state']");
	private final By inputCity = By.xpath("//div[contains(@class,'login-form')]//input[@id='city']");
	private final By inputZipcode = By.xpath("//div[contains(@class,'login-form')]//input[@id='zipcode']");
	private final By inputMobileNumber = By.xpath("//div[contains(@class,'login-form')]//input[@id='mobile_number']");
	
	private final By buttonCreateAccount = By.xpath("//button[contains(@data-qa,'create-account')]");
	/*
	** ELEMENTS
	*/
	protected WebElement getElement_RadioTitleMr() {
		return WaitUtilities.waitForElementClickable(radioTitleMr);
	}
	protected WebElement getElement_RadioTitleMrs() {
		return WaitUtilities.waitForElementClickable(radioTitleMrs);
	}
	protected WebElement getElement_InputName() {
		return WaitUtilities.waitForElementVisible(inputName);
	}
	protected WebElement getElement_InputEmail() {
		return WaitUtilities.waitForElementVisible(inputEmail);
	}
	protected WebElement getElement_InputPassword() {
		return WaitUtilities.waitForElementVisible(inputPassword);
	}
	protected WebElement getElement_SelectDay() {
		return WaitUtilities.waitForElementVisible(selectDay);
	}
	protected WebElement getElement_SelectMonth() {
		return WaitUtilities.waitForElementVisible(selectMonth);
	}
	protected WebElement getElement_SelectYear() {
		return WaitUtilities.waitForElementVisible(selectYear);
	}
	protected WebElement getElement_CheckboxNewsletter() {
		return WaitUtilities.waitForElementVisible(checkboxNewsletter);
	}
	protected WebElement getElement_CheckboxSpecialOffers() {
		return WaitUtilities.waitForElementVisible(checkboxSpecialOffers);
	}
	protected WebElement getElement_InputFirstName() {
		return WaitUtilities.waitForElementVisible(inputFirstName);
	}
	protected WebElement getElement_InputLastName() {
		return WaitUtilities.waitForElementVisible(inputLastName);
	}
	protected WebElement getElement_InputCompany() {
		return WaitUtilities.waitForElementVisible(inputCompany);
	}
	protected WebElement getElement_InputAddress() {
		return WaitUtilities.waitForElementVisible(inputAddress);
	}
	protected WebElement getElement_InputAddress2() {
		return WaitUtilities.waitForElementVisible(inputAddress2);
	}
	protected WebElement getElement_SelectCountry() {
		return WaitUtilities.waitForElementVisible(selectCountry);
	}
	protected WebElement getElement_InputState() {
		return WaitUtilities.waitForElementVisible(inputState);
	}
	protected WebElement getElement_InputCity() {
		return WaitUtilities.waitForElementVisible(inputCity);
	}
	protected WebElement getElement_InputZipcode() {
		return WaitUtilities.waitForElementVisible(inputZipcode);
	}
	protected WebElement getElement_InputMobileNumber() {
		return WaitUtilities.waitForElementVisible(inputMobileNumber);
	}
	protected WebElement getElement_ButtonCreateAccount() {
		return WaitUtilities.waitForElementClickable(buttonCreateAccount);
	}
	/*
	** METHODS
	*/
	public <T> T createAccount(Account account, Class<T> returnPage) {
	    if (account.getTitle().equalsIgnoreCase("Mr.")) {
	        ActionUtilities.clickAction(getElement_RadioTitleMr());
	    } else if (account.getTitle().equalsIgnoreCase("Mrs.")) {
	        ActionUtilities.clickAction(getElement_RadioTitleMrs());
	    }

	    ActionUtilities.sendKeyAction(getElement_InputPassword(), account.getPassword());
	    
	    ActionUtilities.selectByValueAction(getElement_SelectDay(), account.getDay());
	    ActionUtilities.selectByVisibleTextAction(getElement_SelectMonth(), account.getMonth());
	    ActionUtilities.selectByValueAction(getElement_SelectYear(), account.getYear());

	    ActionUtilities.clickCheckBoxAction(getElement_CheckboxNewsletter(), account.isNewsletter());
	    ActionUtilities.clickCheckBoxAction(getElement_CheckboxSpecialOffers(), account.isSpecialOffers());

	    ActionUtilities.sendKeyAction(getElement_InputFirstName(), account.getFirstName());
	    ActionUtilities.sendKeyAction(getElement_InputLastName(), account.getLastName());
	    ActionUtilities.sendKeyAction(getElement_InputCompany(), account.getCompany());
	    ActionUtilities.sendKeyAction(getElement_InputAddress(), account.getAddress());
	    ActionUtilities.sendKeyAction(getElement_InputAddress2(), account.getAddress2());
	    
	    ActionUtilities.selectByVisibleTextAction(getElement_SelectCountry(), account.getCountry());
	    
	    ActionUtilities.sendKeyAction(getElement_InputState(), account.getState());
	    ActionUtilities.sendKeyAction(getElement_InputCity(), account.getCity());
	    ActionUtilities.sendKeyAction(getElement_InputZipcode(), account.getZipcode());
	    ActionUtilities.sendKeyAction(getElement_InputMobileNumber(), account.getMobileNumber());

	    ActionUtilities.clickAction(getElement_ButtonCreateAccount());

	    try {
	        return returnPage.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Could not create instance of " + returnPage);
	    }
	}
}
