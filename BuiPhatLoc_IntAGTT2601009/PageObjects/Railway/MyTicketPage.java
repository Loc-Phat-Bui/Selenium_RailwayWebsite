package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import RailwayDatas.Ticket;

public class MyTicketPage extends GeneralPage {
	// Locators
	private String myTicketTableItemXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td/input";
	// Elements
	protected WebElement getMyTicketTableItemWebElement(String departFrom, String arriveAt) {
		return WaitUtilities.waitForElementClickable(By.xpath(getMyTicketTableItemXpath(departFrom, arriveAt)));
	}
	// Methods
	public String getMyTicketTableItemXpath(String departFrom, String arriveAt) {
		return String.format(myTicketTableItemXpath, departFrom, arriveAt);
	}
	public <T> T cancelMyTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		
		SafetyUtilities.safeClick(getMyTicketTableItemWebElement(ticket.getDepartFrom(), ticket.getArriveAt()));
		
		WaitUtilities.waitForAlert().accept();
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
