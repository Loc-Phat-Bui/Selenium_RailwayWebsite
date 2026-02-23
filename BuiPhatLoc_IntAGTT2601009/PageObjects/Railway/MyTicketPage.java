package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtilities;
import Common.Utilities;
import Common.WaitUtilities;
import Constant.Constant;
import RailwayDatas.Ticket;

public class MyTicketPage extends GeneralPage {
	private String myTicketTableItemXpath = ""
			+ "//td[contains(text(),'%s')]"
			+ "/following-sibling::td[contains(text(),'%s')]"
			+ "/following-sibling::td[contains(text(),'%s')]"
			+ "/following-sibling::td[contains(text(),'%s')]"
			+ "/following-sibling::td[contains(text(),'%s')]"
			+ "/..//input";
	/* 
	** Locators
	*/
	public By getMyTicketTableItemXpath(Ticket.TicketInfo ticket) {
		String departDate = Utilities.getDateAfterInterval(ticket.getDepartDateInterval());
		String tmpXpath = String.format(
				myTicketTableItemXpath, 
				ticket.getDepartFrom(), 
				ticket.getArriveAt(),
				ticket.getSeatType(),
				departDate,
				ticket.getTicketAmount());
		System.out.println(tmpXpath);
		
		return By.xpath(tmpXpath);
	}
	/* 
	** Elements
	*/
	protected WebElement getMyTicketTableItemWebElement(Ticket.TicketInfo ticket) {
		return WaitUtilities.waitForElementClickable(getMyTicketTableItemXpath(ticket));
	}
	/* 
	** Methods
	*/
	public <T> T cancelMyTicket(Ticket.TicketInfo ticket, Class<T> returnPage) {
		
		ActionUtilities.click_Action(getMyTicketTableItemWebElement(ticket));
		
		WaitUtilities.waitForAlert().accept();
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isTicketExist(Ticket.TicketInfo ticket) {
		return !Constant.WEBDRIVER.findElements(getMyTicketTableItemXpath(ticket)).isEmpty();
	}
}
