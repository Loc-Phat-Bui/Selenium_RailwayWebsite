package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Constant.StationLocation;
import Datas.Account;
import Datas.Ticket;
import Datas.TicketPrice;

public class BookTicketTest extends TestBase{
	private SoftAssert softAssert = new SoftAssert();
	private final boolean createAccount = true;
	
	@Test
	public void TC12() {
		System.out.println("TC12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account = this.accountSetup("TC12", createAccount);
		
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account ");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		
		System.out.println("Step: 3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoTabPage(Macros.TAB_MENU_BOOK_TICKET, BookTicketPage.class);
		
		
		
		System.out.println("Step: 4. Select the next 2 days from \"Depart date\"");
		System.out.println("Step: 5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("Step: 6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("Step: 7. Select \"1\" for \"Ticket amount\"");
		System.out.println("Step: 8. Click on \"Book ticket\" button");
		Ticket.TicketInfo ticket  = Ticket.getTicketInfo("TC12");
		String 	departDate = Utilities.getDateForBookTicket(
				new Select(bookTicketPage.getSelectorWebElement(Macros.SELECT_DEPART_DATE)).getFirstSelectedOption().getText(), 
				ticket.getDepartDateInterval());
		homePage = bookTicketPage.bookTicket(ticket, HomePage.class, departDate);
		
		
		
		System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualString = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedString = "Ticket booked successfully!";
		softAssert.assertEquals(actualString, expectedString, "Message is not displayed as expected");
		
		
		
		System.out.println("Verify: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		List<WebElement> cols = bookTicketPage.getTableBookTicketWebElement().findElements(By.tagName("td"));
		
		softAssert.assertEquals(cols.get(0).getText(), ticket.getDepartFrom(), "Depart Station Info does not match");
		softAssert.assertEquals(cols.get(1).getText(), ticket.getArriveAt(), "Arrive Station Info does not match");
		softAssert.assertEquals(cols.get(2).getText(), ticket.getSeatType(), "Seat Type Info does not match");
		softAssert.assertEquals(cols.get(3).getText(), departDate, "Depart Date Info does not match");
		softAssert.assertEquals(cols.get(4).getText(), Utilities.getDateForBookTicket((short) 0), "Book Date Info does not match");
		softAssert.assertEquals(cols.get(6).getText(), Byte.toString(ticket.getTicketAmount()), "Ticket Amount Info does not match");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC13() {
		System.out.println("TC13 - User can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account = this.accountSetup("TC13", createAccount);
		
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account ");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		
		System.out.println("Step: 3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoTabPage(Macros.TAB_MENU_BOOK_TICKET, BookTicketPage.class);
		
		
		
		System.out.println("Step: 4. Select the next 2 days from \"Depart date\"");
		System.out.println("Step: 5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("Step: 6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("Step: 7. Select \"1\" for \"Ticket amount\"");
		System.out.println("Step: 8. Click on \"Book ticket\" button");
		Ticket.TicketInfo ticket  = Ticket.getTicketInfo("TC13");
		String 	departDate = Utilities.getDateForBookTicket(
				new Select(bookTicketPage.getSelectorWebElement(Macros.SELECT_DEPART_DATE)).getFirstSelectedOption().getText(), 
				ticket.getDepartDateInterval());
		homePage = bookTicketPage.bookTicket(ticket, HomePage.class, departDate);
		
		
		
		System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualString = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedString = "Ticket booked successfully!";
		softAssert.assertEquals(actualString, expectedString, "Message is not displayed as expected");
		
		
		
		System.out.println("Verify: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		List<WebElement> cols = bookTicketPage.getTableBookTicketWebElement().findElements(By.tagName("td"));
		
		softAssert.assertEquals(cols.get(0).getText(), ticket.getDepartFrom(), "Depart Station Info does not match");
		softAssert.assertEquals(cols.get(1).getText(), ticket.getArriveAt(), "Arrive Station Info does not match");
		softAssert.assertEquals(cols.get(2).getText(), ticket.getSeatType(), "Seat Type Info does not match");
		softAssert.assertEquals(cols.get(3).getText(), departDate, "Depart Date Info does not match");
		softAssert.assertEquals(cols.get(4).getText(), Utilities.getDateForBookTicket((short) 0), "Book Date Info does not match");
		softAssert.assertEquals(cols.get(6).getText(), Byte.toString(ticket.getTicketAmount()), "Ticket Amount Info does not match");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC14() {
		System.out.println("TC14 - User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account = this.accountSetup("TC14", createAccount);
		
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		
		System.out.println("Step: 3. Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoTabPage(Macros.TAB_MENU_TIMETABLE, TimetablePage.class);
		
		
		
		System.out.println("Step: 4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		homePage = timetablePage.timetableCheckPrice(
				StationLocation.DA_NANG.getDisplayName(),
				StationLocation.SAI_GON.getDisplayName(),
				HomePage.class);
		
		
		
		System.out.println("Verify: \"Ticket Price\" page is loaded. Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".");
		softAssert.assertTrue(Constant.WEBDRIVER.getCurrentUrl().contains("TicketPricePage.cshtml"), "Current Page is not the Page needed to be shown");
		
		
		
		System.out.println("Verify: Price for each seat displays correctly HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		TicketPrice.TicketPriceInfo ticketPriceInfo = TicketPrice.getTicketPriceInfo("TC14");
		TicketPricePage ticketPricePage = new TicketPricePage();
		List<WebElement> cols = ticketPricePage.getTableTicketPriceWebElement().findElements(By.tagName("td"));
		
		softAssert.assertEquals(cols.get(0).getText(), ticketPriceInfo.getPriceHS(), "HS price does not match");
		softAssert.assertEquals(cols.get(1).getText(), ticketPriceInfo.getPriceSS(), "SS price Info does not match");
		softAssert.assertEquals(cols.get(2).getText(), ticketPriceInfo.getPriceSSC(), "SSC price Info does not match");
		softAssert.assertEquals(cols.get(3).getText(), ticketPriceInfo.getPriceHB(), "HB price does not match");
		softAssert.assertEquals(cols.get(4).getText(), ticketPriceInfo.getPriceSB(), "SB price does not match");
		softAssert.assertEquals(cols.get(5).getText(), ticketPriceInfo.getPriceSBC(), "SBC price does not match");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC15() {
		System.out.println("TC15 - User can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		Account.AccountInfo account = this.accountSetup("TC15", createAccount);
		
		
		
		System.out.println("Step: 1. Navigate to QA Railway Website");
		System.out.println("Step: 2. Login with a valid account");
		LoginPage loginPage = homePage.gotoTabPage(Macros.TAB_MENU_LOGIN, LoginPage.class);
		homePage = loginPage.login(account, HomePage.class);
		
		
		
		System.out.println("Step: 3. Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoTabPage(Macros.TAB_MENU_TIMETABLE, TimetablePage.class);
		
		
		
		System.out.println("Step: 4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		homePage = timetablePage.timetableBookTicket(
				StationLocation.QUANG_NGAI.getDisplayName(),
				StationLocation.HUE.getDisplayName(),
				HomePage.class);
		
		
		
		System.out.println("Verify: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		BookTicketPage bookTicketPage = new BookTicketPage();
		Select selectDepart = new Select(bookTicketPage.getSelectorWebElement(Macros.SELECT_DEPART_FROM));
		softAssert.assertEquals(
				selectDepart.getFirstSelectedOption().getText(),
				StationLocation.QUANG_NGAI.getDisplayName(),
				"Depart Station does not match in selection");
		Select selectArrive = new Select(bookTicketPage.getSelectorWebElement(Macros.SELECT_ARRIVE_AT));
		softAssert.assertEquals(
				selectArrive.getFirstSelectedOption().getText(), 
				StationLocation.HUE.getDisplayName(),
				"Arrive Station does not match in selection");
		
		
		
		System.out.println("Step: 5. Select Depart date = tomorrow");
		System.out.println("Step: 6. Select Ticket amount = 5");
		System.out.println("Step: 7. Click on \"Book ticket\" button");
		Ticket.TicketInfo ticket = Ticket.getTicketInfo("TC15");
		ticket.setDepartFrom(selectDepart.getFirstSelectedOption().getText());
		ticket.setArriveAt(selectArrive.getFirstSelectedOption().getText());
		ticket.setSeatType(new Select(bookTicketPage.getSelectorWebElement(Macros.SELECT_SEAT_TYPE)).getFirstSelectedOption().getText());
		homePage = bookTicketPage.bookTicket(ticket, HomePage.class);
		
		
		
		System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualString = Constant.WEBDRIVER.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedString = "Ticket booked successfully!";
		softAssert.assertEquals(actualString, expectedString, "Message is not displayed as expected");
		
		
		
		System.out.println("Verify: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		List<WebElement> cols = bookTicketPage.getTableBookTicketWebElement().findElements(By.tagName("td"));
		
		softAssert.assertEquals(cols.get(0).getText(), ticket.getDepartFrom(), "Depart Station Info does not match in Summary");
		softAssert.assertEquals(cols.get(1).getText(), ticket.getArriveAt(), "Arrive Station Info does not match in Summary");
		softAssert.assertEquals(cols.get(2).getText(), ticket.getSeatType(), "Seat Type Info does not match in Summary");
		softAssert.assertEquals(cols.get(3).getText(), Utilities.getDateForBookTicket(ticket.getDepartDateInterval()), "Depart Date Info does not match in Summary");
		softAssert.assertEquals(cols.get(4).getText(), Utilities.getDateForBookTicket((short) 0), "Book Date Info does not match in Summary");
		softAssert.assertEquals(cols.get(6).getText(), Byte.toString(ticket.getTicketAmount()), "Ticket Amount Info does not match in Summary");
		
		softAssert.assertAll();
	}
}
