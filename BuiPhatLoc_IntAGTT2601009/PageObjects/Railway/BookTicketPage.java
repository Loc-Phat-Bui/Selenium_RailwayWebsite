package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.WaitUtilities;
import Constant.Macros;

public class BookTicketPage {
	// Locators
	private String selectorXpath = "//select[contains(@name,'%s')]";
	// Elements
	protected WebElement getSelectorWebElement(String selectorName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	// Methods
	public String getSelectorXpath (String selectorName) {
		String namePart = "";
		switch (selectorName.toLowerCase()) {
		case Macros.SELECT_DEPART_DATE:
			namePart = "Date";
			break;
		case Macros.SELECT_DEPART_FROM:
			namePart = "DepartStation";
			break;
		case Macros.SELECT_ARRIVE_AT:
			namePart = "ArriveStation";
			break;
		case Macros.SELECT_SEAT_TYPE:
			namePart = "SeatType";
			break;
		case Macros.SELECT_TICKET_AMOUNT:
			namePart = "TicketAmount";
			break;
		default:
			break;
		}
		return String.format(selectorXpath, namePart);
	}
}
