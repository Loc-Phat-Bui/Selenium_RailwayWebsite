package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.WaitUtilities;
import Constant.Macros;

public class TimetablePage extends GeneralPage {
	// Locators
	private String timetableTrainTableItemXpath = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]/following-sibling::td/a[contains(@href,'%s')]";
	// Elements
	protected WebElement getTimetableTrainTableItemWebElement(String departFrom, String arriveAt, String timetableAction) {
		return WaitUtilities.waitForElementClickable(By.xpath(getTimetableTrainTableItemXpath(departFrom, arriveAt, timetableAction)));
	}
	// Methods
	public String getTimetableTrainTableItemXpath(String departFrom, String arriveAt, String timetableAction) {
		int index = timetableAction.indexOf('/');
	    if (index == -1) {
	        return String.format(timetableTrainTableItemXpath, departFrom, arriveAt, timetableAction, "");
	    }
	    String divPart = timetableAction.substring(0, index);
	    String hrefPart = timetableAction.substring(index + 1);
		return String.format(timetableTrainTableItemXpath, departFrom, arriveAt, hrefPart);
	}
	
	public <T> T timetableCheckPrice(String departFrom, String arriveAt, Class<T> returnPage) {
		
		SafetyUtilities.safeClick(getTimetableTrainTableItemWebElement(departFrom, arriveAt, Macros.TIMETABLE_CHECK_PRICE));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T timetableBookTicket(String departFrom, String arriveAt, Class<T> returnPage) {
		
		SafetyUtilities.safeClick(getTimetableTrainTableItemWebElement(departFrom, arriveAt, Macros.TIMETABLE_BOOK_TICKET));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
