package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Constant.Macros;
import Datas.Account;
import Datas.Ticket;

public class CancelMyTicketTest extends BaseTestMethod {
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account = this.accountSetup("TC16", true);
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		System.out.println("Step: 3. Book a ticket");
		BookTicketPage bookTicketPage = homePage.gotoTabPage(Macros.TAB_MENU_BOOK_TICKET, BookTicketPage.class);
		Ticket.TicketInfo ticket  = Ticket.getTicketInfo("TC12");
		homePage = bookTicketPage.bookTicket(ticket, HomePage.class);
		
		
		System.out.println("Step: 4. Click on \"My ticket\" ");
		MyTicketPage myTicketPage = homePage.gotoTabPage(Macros.TAB_MENU_MY_TICKET, MyTicketPage.class);
		
		
		System.out.println("Step: 5. Click on \"Cancel\" button of ticket which user want to cancel.");
		System.out.println("Step: 6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		homePage = myTicketPage.cancelMyTicket(ticket, HomePage.class);
		
		
		System.out.println("Verify: The canceled ticket is disappeared.");
	}
}
