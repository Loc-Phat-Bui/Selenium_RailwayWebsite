package Constant;
import org.openqa.selenium.WebDriver;

import Common.Utilities;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final String AUTOMATION_EXERCISE_URL = "https://www.automationexercise.com/";
	public static final String CHROME_DRIVER_PATH = Utilities.getProjectPath() + "\\Executables\\chromedriver.exe";
	public static final String FIREFOX_DRIVER_PATH = Utilities.getProjectPath() + "\\Executables\\geckodriver.exe";
}
