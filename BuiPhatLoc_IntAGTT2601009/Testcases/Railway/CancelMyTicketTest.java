package Railway;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import RailwayDatas.Ticket;
import RailwayEnum.TabMenu;

public class CancelMyTicketTest extends TestBase {
	private final boolean createAccount = true; // true = Create account, false = use default account
	
	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		account = this.accountSetup("TC16", createAccount);
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account");
		loginPage = homePage.gotoTabPage(TabMenu.LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		System.out.println("Step: 3. Book a ticket");
		bookTicketPage = homePage.gotoTabPage(TabMenu.BOOK_TICKET, BookTicketPage.class);
		Ticket.TicketInfo ticket  = Ticket.getTicketInfo("TC16");
		homePage = bookTicketPage.bookTicket(ticket, HomePage.class);
		
		
		System.out.println("Step: 4. Click on \"My ticket\" ");
		MyTicketPage myTicketPage = homePage.gotoTabPage(TabMenu.MY_TICKET, MyTicketPage.class);
		
		
		System.out.println("Step: 5. Click on \"Cancel\" button of ticket which user want to cancel.");
		System.out.println("Step: 6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		homePage = myTicketPage.cancelMyTicket(ticket, HomePage.class);
		
		
		System.out.println("Verify: The canceled ticket is disappeared.");
		
		Assert.assertTrue(Constant.WEBDRIVER.findElements(By.xpath(myTicketPage.getMyTicketTableItemXpath(ticket.getDepartFrom(), ticket.getArriveAt()))).isEmpty(), "Ticket is not deleted");
	}
}
