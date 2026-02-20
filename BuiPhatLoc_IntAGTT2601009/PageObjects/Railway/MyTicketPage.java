package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Constant;
import RailwayDatas.Ticket;

public class MyTicketPage extends GeneralPage {
	private String myTicketTableItemXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/input";
	// Locators
	public By getMyTicketTableItemXpath(Ticket.TicketInfo ticket) {
		return By.xpath(String.format(myTicketTableItemXpath, ticket.getDepartFrom(), ticket.getArriveAt()));
	}
	// Elements
	protected WebElement getMyTicketTableItemWebElement(Ticket.TicketInfo ticket) {
		return WaitUtilities.waitForElementClickable(getMyTicketTableItemXpath(ticket));
	}
	// Methods
	public <T> T cancelMyTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		
		SafetyUtilities.safeClick(getMyTicketTableItemWebElement(ticket));
		
		WaitUtilities.waitForAlert().accept();
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isTicketExist(Ticket.TicketInfo ticket) {
		return Constant.WEBDRIVER.findElements(getMyTicketTableItemXpath(ticket)).isEmpty();
	}
}
