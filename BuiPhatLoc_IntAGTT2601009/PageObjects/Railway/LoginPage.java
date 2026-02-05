package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Account;
import Common.Utilities;
import Common.WaitUtilities;
import Constant.Macros;

public class LoginPage extends GeneralPage { 
	// Locators
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	// Elements
	protected WebElement getLblLoginErrorMsgWebElement() {
		return WaitUtilities.waitForElementVisible(lblLoginErrorMsg);
	}
	
	// Methods
	public String getLblLoginErrorMsgText () {
		return this.getLblLoginErrorMsgWebElement().getText();
	}
	
	public <T> T login(Account.AccountInfo account, Class<T> returnPage) {
		
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxUsername), account.getUsername());
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPassword), account.getPassword());
		
		Utilities.safeClick(this.getTxtBoxWebElement(Macros.btnLogin));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
