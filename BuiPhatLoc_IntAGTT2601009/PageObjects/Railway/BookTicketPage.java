package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.Utilities;
import Common.WaitUtilities;
import Constant.Macros;
import RailwayDatas.Ticket;
import RailwayEnum.Location;
import Constant.Constant;

public class BookTicketPage extends GeneralPage{
	private final boolean doWaitForOptions = true;
	private final boolean dontWaitForOptions = false;
	// Locators
	private String selectorXpath = "//select[contains(@name,'%s')]";
	private final String ticketConfirmationXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/..";
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
	
	// Use Today as base
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		String departDate = Utilities.getDateForBookTicket(ticket.getDepartDateInterval());
		
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_FROM), ticket.getDepartFrom());
		if(ticket.getDepartFrom() == Location.SAI_GON.getDisplayName()) {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_ARRIVE_AT, dontWaitForOptions), ticket.getArriveAt());
		} else {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_ARRIVE_AT, doWaitForOptions), ticket.getArriveAt());
		}
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Macros.BTN_BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	// Directly use the input departDate
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage, String departDate) {
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_DEPART_FROM), ticket.getDepartFrom());
		if(ticket.getDepartFrom() == Location.SAI_GON.getDisplayName()) {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_ARRIVE_AT, dontWaitForOptions), ticket.getArriveAt());
		} else {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_ARRIVE_AT, doWaitForOptions), ticket.getArriveAt());
		}
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Macros.SELECT_TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Macros.BTN_BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isTicketCorrect(Ticket.TicketInfo ticket) {
		String departDate = Utilities.getDateForBookTicket(ticket.getDepartDateInterval());
		String xpathTicket = String.format(
				ticketConfirmationXpath, 
				ticket.getDepartFrom(),
				ticket.getArriveAt(),
				ticket.getSeatType(),
				departDate,
				ticket.getTicketAmount());
		
		return !Constant.WEBDRIVER.findElements(By.xpath(xpathTicket)).isEmpty();
	}
	public boolean isTicketCorrect(Ticket.TicketInfo ticket, String departDate) {
		String xpathTicket = String.format(
				ticketConfirmationXpath, 
				ticket.getDepartFrom(),
				ticket.getArriveAt(),
				ticket.getSeatType(),
				departDate,
				ticket.getTicketAmount());
		System.out.println(xpathTicket);
		
		return !Constant.WEBDRIVER.findElements(By.xpath(xpathTicket)).isEmpty();
	}
}
