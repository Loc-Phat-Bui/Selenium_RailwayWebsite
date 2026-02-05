package Railway;

import Account.Account;
import Common.SafetyUtilities;
import Constant.Macros;

public class RegisterPage extends GeneralPage {
	// Locators
	// Elements
	// Methods
	public <T> T register(Account.AccountInfo account, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxUsername), account.getUsername());
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPassword), account.getPassword());
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxConfirmPassword), account.getPassword());	
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(Macros.txtboxPID), account.getPID());
		SafetyUtilities.safeClick(this.getTxtBoxWebElement(Macros.btnRegister));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
