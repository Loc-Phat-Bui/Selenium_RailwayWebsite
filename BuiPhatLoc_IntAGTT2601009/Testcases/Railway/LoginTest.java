package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Constant.Constant;
import Testcases.RailwayLoginTestRepo;
import Account.Account;

public class LoginTest extends BaseTestMethod{
	private SoftAssert softAssert = new SoftAssert();
	private String[] testCaseID = {
			"TC0",
			"TC1",
			"TC2",
			"TC3",
			"TC4",
			"TC5"
	};
	
	
	@Test
	public void TC01() {
		RailwayLoginTestRepo.printTestcaseInfo("TC1");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.validUSERNAME, Account.validPASSWORD);
		// Depend on Window/Browser size (width)
		String actualString = homePage.getWelcomeMessageString();
		String expectedString = "Welcome " + Account.validUSERNAME;		
		Assert.assertEquals(actualString, expectedString, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		RailwayLoginTestRepo.printTestcaseInfo("TC2");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.blankUSERNAME, Account.validPASSWORD);
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		RailwayLoginTestRepo.printTestcaseInfo("TC3");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Account.validUSERNAME, Account.invalidPASSWORD);
		String actualString = loginPage.getLblLoginErrorMsgText();
		String expectedString = "There was a problem with your login and/or errors exist in your form.";
		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
	}
	
//	@Test
//	public void TC04() {
//		RailwayLoginTest_TestcaseRepo.printRailwayLoginTest_TestcaseInfoInfo("TC4");
//		
//		LoginPage loginPage = homePage.gotoLoginPage();
//		String actualString;
//		String expectedString;
//		for(int i = 0; i < 4; i++) {
//			loginPage.login(Account.validUSERNAME, Account.invalidPASSWORD);
//			if (i < 3) {
//				actualString = loginPage.getLblLoginErrorMsgText();
//				expectedString = "Invalid username or password. Please try again.";
//				softAssert.assertEquals(actualString, expectedString, "Attempt - "+ (i+1) + ": Error Message is not displayed as expected");
//				loginPage.clearTxtbox();
//			}
//		}
//		
//		softAssert.assertAll();
//		
//		actualString = loginPage.getLblLoginErrorMsgText();
//		expectedString = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
//		Assert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
//	}
	
}
