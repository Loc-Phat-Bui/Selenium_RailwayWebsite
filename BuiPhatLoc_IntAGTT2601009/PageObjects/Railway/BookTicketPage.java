package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.Utilities;
import Common.WaitUtilities;
import Constant.Macros;
import Datas.Ticket;

public class BookTicketPage extends GeneralPage{
	// Locators
	private String selectorXpath = "//select[contains(@name,'%s')]";
	private final By tableBookTicket = By.xpath("//table[@class='MyTable WideTable']//tr[@class='OddRow']");
	// Elements
	protected WebElement getSelectorWebElement(String selectorName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	protected WebElement getSelectorWebElement(String selectorName, boolean waitForOptions) {
		if(waitForOptions) WaitUtilities.waitForElementToRefresh(By.xpath(getSelectorXpath(selectorName)  + "/option"));
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	protected WebElement getTableBookTicketWebElement() {
		return WaitUtilities.waitForElementVisible(tableBookTicket);
	}
	// Methods
	public String getSelectorXpath (String selectorName) {
		return String.format(selectorXpath, selectorName);
	}
	
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		String departDate = Utilities.getDateForBookTicket(ticket.getDepartDateInterval());
		
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_FROM), ticket.getDepartFrom());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_ARRIVE_AT, true), ticket.getArriveAt());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Macros.BTN_BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T bookTicketTimetable(Ticket.TicketInfo ticket, Class<T> returnPage) {
		String departDate = Utilities.getDateForBookTicket(ticket.getDepartDateInterval());
		
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Macros.BTN_BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
