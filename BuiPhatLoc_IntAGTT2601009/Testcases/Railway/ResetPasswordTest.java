package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Datas.Account;
import Guerrillamail.GuerrillaMailPage;

public class ResetPasswordTest extends BaseTestMethod {
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void TC10() {
		System.out.println("TC10 - Reset password shows error if the new password is same as current");
		Account.AccountInfo account = this.accountSetup("TC10", false);
		
		System.out.println("Pre-condition: an actived account is existing");
		this.createValidAccount(account);
		
		System.out.println("Step: 1. Navigate to QA Railway Login page");
		System.out.println("Step: 2. Click on \"Forgot Password page\" link");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		ResetPasswordPage resetPasswordPage = loginPage.gotoTabPage(Macros.TAB_CONTENT_FORGOT_PASSWORD, ResetPasswordPage.class);
		
		System.out.println("Step: 3. Enter the email address of the activated account");
		System.out.println("Step: 4. Click on \"Send Instructions\" button");
		resetPasswordPage.resetPasswordEmail(account, HomePage.class);
		
		System.out.println("Step: 5. Login to the mailbox (the same mailbox when creating account) ");
		Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.checkGuerillaEmail(account);
		

		System.out.println("Step: 6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("Step: 7. Click on reset link");
		guerrillaMailPage.checkResetEmail();
		for(String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
		
		System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		softAssert.assertTrue(Constant.WEBDRIVER.getCurrentUrl().contains("PasswordReset?resetToken"));
		
		System.out.println("Step: 8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("Step: 9. Click Reset Password");
		resetPasswordPage.resetPassword(account.getPassword(), account.getPassword(), HomePage.class);
		
		
		System.out.println("Verify: Message \"The new password cannot be the same with the current password\" is shown");
		softAssert.assertTrue(resetPasswordPage.checkLblPasswordErrorMsg());
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC11() {
		System.out.println("TC - Reset password shows error if the new password and confirm password doesn't match");
		Account.AccountInfo account = this.accountSetup("TC11", false);
		
		System.out.println("Pre-condition: an actived account is existing");
		this.createValidAccount(account);
		
		System.out.println("Step: 1. Navigate to QA Railway Login page");
		System.out.println("Step: 2. Click on \"Forgot Password page\" link");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		ResetPasswordPage resetPasswordPage = loginPage.gotoTabPage(Macros.TAB_CONTENT_FORGOT_PASSWORD, ResetPasswordPage.class);
		
		System.out.println("Step: 3. Enter the email address of the activated account");
		System.out.println("Step: 4. Click on \"Send Instructions\" button");
		resetPasswordPage.resetPasswordEmail(account, HomePage.class);
		
		System.out.println("Step: 5. Login to the mailbox (the same mailbox when creating account)");
		Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.checkGuerillaEmail(account);
		
		System.out.println("Step: 6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("Step: 7. Click on reset link");
		guerrillaMailPage.checkResetEmail();
		for(String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
		}
		
		System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		softAssert.assertTrue(Constant.WEBDRIVER.getCurrentUrl().contains("PasswordReset?resetToken"));
		
		System.out.println("Step: 8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("Step: 9. Click Reset Password");
		resetPasswordPage.resetPassword(account.getPassword(), "AnythingGoes@123456", HomePage.class);
		
		System.out.println("Verify: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		String actualString = resetPasswordPage.getLblResetPasswordErrorMSGText();
		String expectedString = "Could not reset password. Please correct the errors and try again.";
		softAssert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
		
		System.out.println("Verify: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		actualString = resetPasswordPage.getLabelValidationErrorText(Macros.VALIDATE_ERR_CONFIRM_PASSWORD);
		expectedString = "The password confirmation did not match the new password.";
		softAssert.assertEquals(actualString, expectedString, "Error Message is not displayed as expected");
		
		softAssert.assertAll();
	}
}
