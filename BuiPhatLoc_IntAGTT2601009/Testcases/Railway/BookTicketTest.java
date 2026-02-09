package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Datas.Account;
import Datas.Ticket;

public class BookTicketTest extends BaseTestMethod{
	private SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void TC12() {
		System.out.println("TC12 - User can book 1 ticket at a time");
		Account.AccountInfo account = Account.getAccountInfo("TC00");
		
		System.out.println("Pre-condition: an actived account is existing");
//		this.createValidAccount(account);
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account ");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		loginPage.login(account, HomePage.class);
		
		System.out.println("Step: 3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoTabPage(Macros.TAB_MENU_BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("Step: 4. Select the next 2 days from \"Depart date\"");
		System.out.println("Step: 5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("Step: 6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("Step: 7. Select \"1\" for \"Ticket amount\"");
		System.out.println("Step: 8. Click on \"Book ticket\" button");
		Ticket.TicketInfo ticket  = Ticket.getTicketInfo("TC12");
		bookTicketPage.bookTicket(ticket, HomePage.class);
		
		System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualString = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedString = "Ticket booked successfully!";
		softAssert.assertEquals(actualString, expectedString, "Message is not displayed as expected");
		
		System.out.println("Verify: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		List<WebElement> cols = bookTicketPage.getTableBookTicketWebElement().findElements(By.tagName("td"));
		
		softAssert.assertEquals(cols.get(0).getText(), ticket.getDepartFrom(), "Depart Station Info does not match");
		softAssert.assertEquals(cols.get(1).getText(), ticket.getArriveAt(), "Arrive Station Info does not match");
		softAssert.assertEquals(cols.get(2).getText(), ticket.getSeatType(), "Seat Type Info does not match");
		softAssert.assertEquals(cols.get(3).getText(), Utilities.getDateForBookTicket(ticket.getDepartDateInterval()), "Depart Date Info does not match");
		softAssert.assertEquals(cols.get(4).getText(), Utilities.getDateForBookTicket((short) 0), "Book Date Info does not match");
		softAssert.assertEquals(cols.get(6).getText(), Byte.toString(ticket.getTicketAmount()), "Ticket Amount Info does not match");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC13() {
		System.out.println("TC13 - ");
		
		System.out.println("Step: ");
		
		System.out.println("Verify: ");
	}
	
	@Test
	public void TC14() {
		System.out.println("TC14 - ");
		
		System.out.println("Step: ");
		
		System.out.println("Verify: ");
	}
	
	@Test
	public void TC15() {
		System.out.println("TC15 - ");
		
		System.out.println("Step: ");
		
		System.out.println("Verify: ");
	}
}
