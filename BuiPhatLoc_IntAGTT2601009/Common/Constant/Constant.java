package Constant;

import org.openqa.selenium.WebDriver;

import Common.Utilities;

public class Constant {
	public static WebDriver WEBDRIVER;
	public static final String RAILWAY_URL="http://saferailway.somee.com";
	public static final String GUERRILLA_MAIL_URL = "https://www.guerrillamail.com";
	public static final String CHROME_DRIVER_PATH = Utilities.getProjectPath() + "\\Executables\\chromedriver.exe";
	public static final String FIREFOX_DRIVER_PATH = Utilities.getProjectPath() + "\\Executables\\geckodriver.exe";
}
