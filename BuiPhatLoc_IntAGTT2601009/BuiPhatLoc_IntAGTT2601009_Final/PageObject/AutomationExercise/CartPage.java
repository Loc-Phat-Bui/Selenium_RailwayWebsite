package AutomationExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationExerciseData.AccountData.Account;
import Common.ActionUtilities;
import Common.WaitUtilities;
import Constant.Constant;

public class CartPage extends GeneralPage{
	private final String deliveryInfoXpath = 
			"//h3[contains(text(),'delivery address')]/.."
			+ "/following-sibling::li[contains(text(), '%s')]"
			+ "/following-sibling::li[contains(text(), '%s')]"
			+ "/following-sibling::li[contains(text(), '%s')]"
			+ "/following-sibling::li[contains(text(), '%s') and contains(text(), '%s')]"
			+ "/following-sibling::li[contains(text(), '%s')]"
			+ "/following-sibling::li[contains(text(), '%s')]"
			+ "/.."; 
	/*
	** LOCATORS
	*/
	private By buttonProceedToCheckout = By.xpath("//a[contains(@class,'check_out')]");
	
	private By getLocator_DeliveryInfo(Account account) {
		String tmpXapth = String.format(
				deliveryInfoXpath, 
				account.getTitle() + " " + account.getFirstName() + " " + account.getLastName(),
				account.getCompany(),
				account.getAddress(),
				account.getCity() + " " + account.getState(), account.getZipcode(),
				account.getCountry(),
				account.getMobileNumber()
				);
		return By.xpath(tmpXapth);
	}
	/*
	** ELEMENTS
	*/
	protected WebElement getElement_ButtonProceedToCheckout() {
		return WaitUtilities.waitForElementClickable(buttonProceedToCheckout);
	}
	/*
	** METHODS
	*/
	public boolean isDeliveryInfoMatch(Account account) {
//		WaitUtilities.waitForElementVisible(getLocator_DeliveryInfo(account));
//		System.out.println(getLocator_DeliveryInfo(account));
		if(Constant.WEBDRIVER.findElements(getLocator_DeliveryInfo(account)).isEmpty()) return false;
		return true;
	}
	
	public <T> T proceedToCheckout(Class<T> returnPage) {
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(getElement_ButtonProceedToCheckout());
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
