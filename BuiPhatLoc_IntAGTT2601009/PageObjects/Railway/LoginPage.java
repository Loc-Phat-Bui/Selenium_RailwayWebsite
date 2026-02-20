package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import RailwayDatas.Account;
import RailwayEnum.Button;
import RailwayEnum.TextBox;

public class LoginPage extends GeneralPage { 
	/* 
	** Locators
	*/
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	
	/* 
	** Elements
	*/
	protected WebElement getLblLoginErrorMsgWebElement() {
		return WaitUtilities.waitForElementVisible(lblLoginErrorMsg);
	}
	
	/* 
	** Methods
	*/
	public String getLblLoginErrorMsgText () {
		return this.getLblLoginErrorMsgWebElement().getText();
	}
	
	public <T> T login(Account.AccountInfo account, Class<T> returnPage) {
		
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(TextBox.USERNAME), account.getUsername());
		SafetyUtilities.safeSendkey(this.getTxtBoxWebElement(TextBox.PASSWORD), account.getPassword());
		
		SafetyUtilities.safeClick(this.getBtnWebElement(Button.LOGIN));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
