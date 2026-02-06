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
	// Elements
	protected WebElement getLblRegisterErrorMSGWebElement() {
		return WaitUtilities.waitForElementVisible(lblRegisterErrorMSG);
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
}
