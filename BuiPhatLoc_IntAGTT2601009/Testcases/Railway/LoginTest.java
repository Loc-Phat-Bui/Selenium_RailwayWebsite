package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Testcases.RailwayLoginTestRepo;
import Account.Account;

public class LoginTest extends BaseTestMethod{
	private SoftAssert softAssert = new SoftAssert();
	
	
	@Test
	public void TC01() {
		RailwayLoginTestRepo.printTestcaseInfo("TC1");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.getAccountInfo(Account.sceValidLogin));
		// Depend on Window/Browser size (width)
		String actualString = homePage.getWelcomeMessageString();
		String expectedString = "Welcome " + Account.getAccountInfo("TC1").getUsername();		
		Assert.assertEquals(actualString, expectedString, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		RailwayLoginTestRepo.printTestcaseInfo("TC2");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.getAccountInfo(Account.sceBlankUsername));
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		RailwayLoginTestRepo.printTestcaseInfo("TC3");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.getAccountInfo(Account.sceInvalidPassword));
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC04() {
		RailwayLoginTestRepo.printTestcaseInfo("TC4");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		String actualString;
		String expectedString;
		for(int i = 0; i < 4; i++) {
			loginPage.login(Account.getAccountInfo(Account.sceInvalidPassword));
			if (i < 3) {
				actualString = loginPage.getLblLoginErrorMsgText();
				expectedString = "Invalid username or password. Please try again.";
				softAssert.assertEquals(actualString, expectedString, "Attempt - "+ (i+1) + ": Error Message is not displayed as expected");
			}
		}
		
		softAssert.assertAll();
		
		actualString = loginPage.getLblLoginErrorMsgText();
		expectedString = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC05() {
		RailwayLoginTestRepo.printTestcaseInfo("TC5");
		//Pre-Condition
		RegisterPage registerPage = homePage.gotoRegisterPage();
		registerPage.register(Account.getAccountInfo(Account.sceNonActiveAccount));
		//Test
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.getAccountInfo(Account.sceNonActiveAccount));
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "Invalid username or password. Please try again.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC06() {
		RailwayLoginTestRepo.printTestcaseInfo("TC6");
		// 1. Navigate to QA Railway Website
		// 2. Login with valid Email and Password
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.getAccountInfo(Account.sceValidLogin));
		// 3. Click on "FAQ" tab
		homePage.gotoFAQPage();
		// 4. Click on "Log out" tab
		homePage.logout();
		
		Assert.assertTrue(homePage.isLogoutDisappear());
	}
}
