package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.Utilities;
import Common.WaitUtilities;
import RailwayDatas.Ticket;
import RailwayEnum.Button;
import RailwayEnum.Location;
import RailwayEnum.Selector;
import Constant.Constant;

public class BookTicketPage extends GeneralPage{
	private final boolean doWaitForOptions = true;
	private final boolean dontWaitForOptions = false;
	private String selectorXpath = "//select[contains(@name,'%s')]";
	private String ticketConfirmationXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/..";
	// Locators
	private final By tableBookTicket = By.xpath("//table[@class='MyTable WideTable']//tr[@class='OddRow']");
	private final By headerBookTicketSucess = By.xpath("//div[@id='content']/h1[contains(text(),'successfully')]");
	// Generate xPath
	public String getSelectorXpath (Selector selectorName) {
		return String.format(selectorXpath, selectorName.getValue());
	}
	// Elements
	protected WebElement getSelectorWebElement(Selector selectorName) {
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	protected WebElement getSelectorWebElement(Selector selectorName, boolean waitForOptions) {
		if(waitForOptions) WaitUtilities.waitForElementToRefresh(By.xpath(getSelectorXpath(selectorName)  + "/option"));
		return WaitUtilities.waitForElementClickable(By.xpath(getSelectorXpath(selectorName)));
	}
	protected WebElement getTableBookTicketWebElement() {
		return WaitUtilities.waitForElementVisible(tableBookTicket);
	}
	protected WebElement getHeaderBookTicketSucessWebElement() {
		return WaitUtilities.waitForElementVisible(headerBookTicketSucess);
	}
	// Methods
	
	// Use Today as base
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		String departDate = Utilities.getDateAfterInterval(ticket.getDepartDateInterval());
		
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.DEPART_FROM), ticket.getDepartFrom());
		if(ticket.getDepartFrom() == Location.SAI_GON.getDisplayName()) {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.ARRIVE_AT, dontWaitForOptions), ticket.getArriveAt());
		} else {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.ARRIVE_AT, doWaitForOptions), ticket.getArriveAt());
		}
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Button.BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	// Directly use the input departDate
	public <T> T bookTicket(Ticket.TicketInfo ticket, Class<T> returnPage, String departDate) {
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.DEPART_DATE), departDate);
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.DEPART_FROM), ticket.getDepartFrom());
		if(ticket.getDepartFrom() == Location.SAI_GON.getDisplayName()) {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.ARRIVE_AT, dontWaitForOptions), ticket.getArriveAt());
		} else {
			SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.ARRIVE_AT, doWaitForOptions), ticket.getArriveAt());
		}
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.SEAT_TYPE), ticket.getSeatType());
		SafetyUtilities.safeSelectByVisibleText(getSelectorWebElement(Selector.TICKET_AMOUNT), Byte.toString(ticket.getTicketAmount()));
		SafetyUtilities.safeClick(getBtnWebElement(Button.BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isTicketCorrect(Ticket.TicketInfo ticket) {
		String departDate = Utilities.getDateAfterInterval(ticket.getDepartDateInterval());
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
	
	public String getHeaderBookTicketSucessText() {
		return this.getHeaderBookTicketSucessWebElement().getText();
	}
}
