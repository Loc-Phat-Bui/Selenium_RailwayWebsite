package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Account.Account;
import Common.Utilities;
import Common.WaitUtilities;
import Constant.Constant;
import Constant.Macros;

public class RegisterPage extends GeneralPage {
	// Locators
	// Elements
	// Methods
	public <T> T register(Account.AccountInfo account, Class<T> returnPage) {
		
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxUsername), account.getUsername());
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPassword), account.getPassword());
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxConfirmPassword), account.getPassword());	
		Utilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPID), account.getPID());
		Utilities.safeClick(this.getTxtBoxWebElement(Macros.btnRegister));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
