package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Account.Account;
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
	public String getLblLoginErrorMsgText() {
		return this.getLblLoginErrorMsgWebElement().getText();
	}
	
	public <T> T login(Account.AccountInfo account, Class<T> pageClass) {
		this.sendKeyTxtBox(Macros.txtboxUsername, account.getUsername());
		this.sendKeyTxtBox(Macros.txtboxPassword, account.getPassword());
		
		this.safeClick(Macros.btnLogin);
		
		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + pageClass);
		}
	}
}
