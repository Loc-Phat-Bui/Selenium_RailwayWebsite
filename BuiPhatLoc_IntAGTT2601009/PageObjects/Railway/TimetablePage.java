package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtilities;
import Common.WaitUtilities;
import RailwayEnum.Timetable;

public class TimetablePage extends GeneralPage {
	private String timetableTrainTableItemXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td/a[contains(@href,'%s')]";
	/* 
	** Locators
	*/
	public String getTimetableTrainTableItemXpath(String departFrom, String arriveAt, Timetable timetableAction) {
		return String.format(timetableTrainTableItemXpath, departFrom, arriveAt, timetableAction);
	}
	/* 
	** Elements
	*/
	protected WebElement getTimetableTrainTableItemWebElement(String departFrom, String arriveAt, Timetable timetableAction) {
		return WaitUtilities.waitForElementClickable(By.xpath(getTimetableTrainTableItemXpath(departFrom, arriveAt, timetableAction)));
	}
	/* 
	** Methods
	*/
	public <T> T timetableCheckPrice(String departFrom, String arriveAt, Class<T> returnPage) {
		
		ActionUtilities.click_Action(getTimetableTrainTableItemWebElement(departFrom, arriveAt, Timetable.CHECK_PRICE));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T timetableBookTicket(String departFrom, String arriveAt, Class<T> returnPage) {
		
		ActionUtilities.click_Action(getTimetableTrainTableItemWebElement(departFrom, arriveAt, Timetable.BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
