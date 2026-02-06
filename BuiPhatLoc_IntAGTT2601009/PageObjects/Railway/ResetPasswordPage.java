package Railway;

import org.openqa.selenium.By;

import Account.Account;
import Common.SafetyUtilities;
import Constant.Constant;
import Constant.Macros;

public class ResetPasswordPage extends GeneralPage {
	// Locators
	private final By lblPasswordErrorMsg = By.xpath("//p[@class='message error']");
	// Elements
	// Methods
	public <T> T resetPasswordEmail (Account.AccountInfo account, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.TXT_BOX_EMAIL), account.getUsername());
		SafetyUtilities.safeClick(this.getBtnWebElement(Macros.BTN_SEND_INSTRUCTIONS));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T resetPassword (String newPassword, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.TXT_BOX_NEW_PASSWORD), newPassword);
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.TXT_BOX_CONFIRM_PASSWORD), newPassword);
		SafetyUtilities.safeClick(this.getBtnWebElement(Macros.BTN_RESET_PASSWORD));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean checkLblPasswordErrorMsg() {
		return !Constant.WEBDRIVER.findElements(lblPasswordErrorMsg).isEmpty();
	}
}
