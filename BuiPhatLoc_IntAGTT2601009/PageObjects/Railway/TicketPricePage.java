package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.WaitUtilities;

public class TicketPricePage {
	// Locators
	private final By tableTicketPrice = By.xpath("//table[@class='MyTable MedTable']//tr[contains(.,'Price (VND)')]");
	// Elements
	protected WebElement getTableTicketPriceWebElement() {
		return WaitUtilities.waitForElementVisible(tableTicketPrice);
	}
	// Methods
	
}
