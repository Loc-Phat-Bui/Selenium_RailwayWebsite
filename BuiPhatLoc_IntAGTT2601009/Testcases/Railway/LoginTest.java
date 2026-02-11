package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Constant.Constant;
import Constant.Macros;
import Datas.Account;

public class LoginTest extends TestBase{
	private SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void TC01() {
		System.out.println("TC1 - User can log into Railway with valid username and password");
		Account.AccountInfo account = this.accountSetup("TC1", false);
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Login\" tab");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		
		System.out.println("Step: 3. Enter valid Email and Password");
		System.out.println("Step: 4. Click on \"Login\" button");
		
		loginPage.login(account, HomePage.class);
		
		System.out.println("Verify: User is logged into Railway. Welcome user message is displayed.");
		
		String actualString = homePage.getWelcomeMessageString();
		String expectedString = "Welcome " + account.getUsername();		
		Assert.assertEquals(actualString, expectedString, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		System.out.println("TC2 - User cannot login with blank \"Username\" textbox");
		Account.AccountInfo account = this.accountSetup("TC2");
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Login\" tab");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		
		System.out.println("Step: 3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("Step: 4. Click on \"Login\" button");
		
		loginPage.login(account, HomePage.class);
		
		System.out.println("Verify: User can't login and message \"There was a problem with your login and/or errors exist in your form.\" appears.");
		
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		System.out.println("TC3 - User cannot log into Railway with invalid password ");
		Account.AccountInfo account = this.accountSetup("TC3");
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Login\" tab");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		
		System.out.println("Step: 3. Enter valid Email and invalid ");
		System.out.println("Step: 4. Click on \"Login\" button");
		
		loginPage.login(account, HomePage.class);
		
		System.out.println("Verify: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC04() {
		System.out.println("TC4 - System shows message when user enters wrong password many times");
		Account.AccountInfo account = this.accountSetup("TC4");
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Login\" tab");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		
		System.out.println("Step: 3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
		System.out.println("Step: 4. Click on \"Login\" button");
		
		loginPage.login(account, HomePage.class);
		
		System.out.println("Verify: \"Invalid username or password. Please try again\" is shown");
		
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "Invalid username or password. Please try again.";
		softAssert.assertEquals(actualString, expectedString, "Attempt - 1: Error Message is not displayed as expected");
		
		System.out.println("Step: 5. Repeat step 3 and 4 three more times.");
		
		for(int i = 2; i <= 4; i++) {
			loginPage.login(account, HomePage.class);
			if (i <= 3) {
				actualString = loginPage.getLblLoginErrorMsgText();
				softAssert.assertEquals(actualString, expectedString, "Attempt - "+ i + ": Error Message is not displayed as expected");
			}
		}
		softAssert.assertAll();
		
		System.out.println("Verify: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");

		actualString = loginPage.getLblLoginErrorMsgText();
		expectedString = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC05() {
		System.out.println("TC5 - User can't login with an account hasn't been activated");
		System.out.println("Pre-condition: a not-active account is existing");
		Account.AccountInfo account = this.accountSetup("TC5");

	    RegisterPage registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
	    registerPage.register(account, HomePage.class);
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Click on \"Login\" tab");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		
		System.out.println("Step: 3. Enter username and password of account hasn't been activated.");
		System.out.println("Step: 4. Click on \"Login\" button");
		
		loginPage.login(account, HomePage.class);
		
		System.out.println("Verify: User can't login and message \"Invalid username or password. Please try again.\" appears.");
		
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "Invalid username or password. Please try again.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC06() {
		System.out.println("TC6 - User is redirected to Home page after logging out");
		Account.AccountInfo account = this.accountSetup("TC6", false);
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with valid Email and Password");
		
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		loginPage.login(account, HomePage.class);
		
		System.out.println("Step: 3. Click on \"FAQ\" ");
		System.out.println("Step: 4. Click on \"Log out\" ");
		
		homePage.gotoTabPage(Macros.TAB_MENU_FAQ, FAQPage.class);
		homePage.gotoTabPage(Macros.TAB_MENU_LOGOUT, HomePage.class);
		
		System.out.println("Verify: Home page displays. \"Log out\" tab is disappeared.");
		softAssert.assertTrue(Constant.WEBDRIVER.getCurrentUrl().contains("HomePage.cshtml"), "Current Page is not the Page needed to be shown");
		softAssert.assertTrue(!homePage.checkTabElementAvailable(Macros.TAB_MENU_LOGOUT), "Logout Tab is still shown on the menu bar");
		
		softAssert.assertAll();
	}
}
