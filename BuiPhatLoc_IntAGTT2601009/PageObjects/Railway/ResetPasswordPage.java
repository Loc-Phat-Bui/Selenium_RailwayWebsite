package Railway;

import org.openqa.selenium.By;

import Common.SafetyUtilities;
import Constant.Constant;
import RailwayDatas.Account;
import RailwayEnum.Button;
import RailwayEnum.TextBox;

public class ResetPasswordPage extends GeneralPage {
	/* 
	** Locators
	*/
	private final By lblPasswordErrorMsg = By.xpath("//p[@class='message error']");
	/* 
	** Elements
	*/
	/* 
	** Methods
	*/
	public <T> T resetPasswordEmail (Account.AccountInfo account, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(TextBox.EMAIL), account.getUsername());
		SafetyUtilities.safeClick(this.getBtnWebElement(Button.SEND_INSTRUCTIONS));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T resetPassword (String newPassword, String confirmPassword, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(TextBox.NEW_PASSWORD), newPassword);
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(TextBox.CONFIRM_PASSWORD), confirmPassword);
		SafetyUtilities.safeClick(this.getBtnWebElement(Button.RESET_PASSWORD));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	public String getLblResetPasswordErrorMSGText() {
		return this.getLblErrorMSGWebElement().getText();
	}
	public boolean checkLblPasswordErrorMsg() {
		return !Constant.WEBDRIVER.findElements(lblPasswordErrorMsg).isEmpty();
	}
}
