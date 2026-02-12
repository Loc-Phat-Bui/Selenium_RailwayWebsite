package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Guerrillamail.GuerrillaMailPage;
import RailwayDatas.Account;

public class CreateAccountTest extends TestBase {
	private final boolean createAccount = true; // true = Create account, false = use default account
	
	@Test
	public void TC07() {
		System.out.println("TC7 - User can't create account with an already in-use email");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account =  this.accountSetup("TC7", createAccount);
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Register\" tab");
		
		registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
		
		System.out.println("Step: 3. Enter information of the created account in Pre-condition");
		System.out.println("Step: 4. Click on \"Register\" button");
		
		registerPage.register(account, HomePage.class);
		
		System.out.println("Verify: Error message \"This email address is already in use.\" displays above the form.");
		
		actualString = registerPage.getLblRegisterErrorMSGText();
		expectedString = "This email address is already in use.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC08() {
		System.out.println("TC8 - User can't create account while password and PID fields are empty");
		Account.AccountInfo account =  this.accountSetup("TC8");
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Register\" tab");
		
		registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
		
		System.out.println("Step: 3. Enter valid email address and leave other fields empty");
		System.out.println("Step: 4. Click on \"Register\" button");
		
		registerPage.register(account, HomePage.class);
		
		System.out.println("Verify: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		
		actualString = registerPage.getLblRegisterErrorMSGText();
		expectedString = "There're errors in the form. Please correct the errors and try again.";
		softAssert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
		
		System.out.println("Verify: Next to password fields, error message \"Invalid password length\" displays");
		
		actualString = registerPage.getLblRegisterInvalidPasswordMSGText();
		expectedString = "Invalid password length";
		softAssert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
		
		System.out.println("Verify: Next to PID field, error message \"Invalid ID length\" displays");
		
		actualString = registerPage.getLblRegisterInvalidPIDMSGText();
		expectedString = "Invalid ID length";
		softAssert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC09() {
		System.out.println("TC9 - User create and activate account");
		account = this.accountSetup("TC9");
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Verify	: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		softAssert.assertTrue(homePage.checkTabElementAvailable(Macros.TAB_CONTENT_REGISTER), "Current Page is not the Page needed to be shown");
		
		System.out.println("Step: 2. Click on \"Create an account\"");
		registerPage = homePage.gotoTabPage(Macros.TAB_CONTENT_REGISTER, RegisterPage.class);
		
		System.out.println("Verify: Register page is shown");
		softAssert.assertTrue(homePage.checkPageURL(Macros.TAB_MENU_REGISTER), "Current Page is not the Page needed to be shown");
		
		System.out.println("Step: 3. Enter valid information into all fields");
		System.out.println("Step: 4. Click on \"Register\" button");
		registerPage.register(account, HomePage.class);
		
		System.out.println("Verify: \"Thank you for registering your account\" is shown");
		actualString = registerPage.getTextRegisterContentHeaderText();
		expectedString = "Thank you for registering your account";
		softAssert.assertEquals(actualString, expectedString, "Text is not displayed as expected");
		
		System.out.println("Step: 5. Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		System.out.println("Step: 6. Login to the mailbox");
		Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
		GuerrillaMailPage guerrillamailPage = new GuerrillaMailPage();
		guerrillamailPage.checkGuerillaEmail(account);
		
		System.out.println("Step: 7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("Step: 8. Click on the activate link");
		guerrillamailPage.checkConfirmEmail();
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
		
		System.out.println("Verify: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown.");
		actualString = registerPage.getTextRegisterContentParagraphText();
		expectedString = "Registration Confirmed! You can now log in to the site";
		softAssert.assertEquals(actualString, expectedString, "Text is not displayed as expected");
		
		softAssert.assertAll();
	}
}
