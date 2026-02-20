package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.WaitUtilities;
import Constant.Constant;
import RailwayDatas.TicketPrice;

public class TicketPricePage {
	/* 
	** Locators
	*/
	private final By tableTicketPrice = By.xpath("//table[@class='MyTable MedTable']//tr[contains(.,'Price (VND)')]");
	private final String ticketPriceXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/..";
	/* 
	** Elements
	*/
	protected WebElement getTableTicketPriceWebElement() {
		return WaitUtilities.waitForElementVisible(tableTicketPrice);
	}
	/* 
	** Methods
	*/
	public boolean checkTicketPrice(TicketPrice.TicketPriceInfo ticketPrice) {
		By xPath = By.xpath(String.format(
				ticketPriceXpath,
				ticketPrice.getPriceHS(),
				ticketPrice.getPriceSS(),
				ticketPrice.getPriceSSC(),
				ticketPrice.getPriceHB(),
				ticketPrice.getPriceSB(),
				ticketPrice.getPriceSBC()));
		return !Constant.WEBDRIVER.findElements(xPath).isEmpty();
	}
}
