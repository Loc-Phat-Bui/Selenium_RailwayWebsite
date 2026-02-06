package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Macros;
import Datas.Ticket;

public class BookTicketPage {
	// Locators
	private String selectorXpath = "//select[contains(@name,'%s')]";
	// Elements
	protected WebElement getSelectorWebElement(String selectorName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	// Methods
	public String getSelectorXpath (String selectorName) {
		return String.format(selectorXpath, selectorName);
	}
	
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		
		SafetyUtilities.safeSelect(getSelectorWebElement(Macros.SELECT_DEPART_DATE), ticket.getDepartDate());
		SafetyUtilities.safeSelect(getSelectorWebElement(Macros.SELECT_DEPART_FROM), ticket.getDepartFrom());
		SafetyUtilities.safeSelect(getSelectorWebElement(Macros.SELECT_ARRIVE_AT), ticket.getArriveAt());
		SafetyUtilities.safeSelect(getSelectorWebElement(Macros.SELECT_SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelect(getSelectorWebElement(Macros.SELECT_TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
