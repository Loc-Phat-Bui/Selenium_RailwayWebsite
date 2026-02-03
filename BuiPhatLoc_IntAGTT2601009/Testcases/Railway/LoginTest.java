package Railway;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LoginTest {
	
	@BeforeMethod
	public void beforeMethod() {
	    System.out.println("Pre-condition");

	    System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
//	    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
	    Constant.WEBDRIVER = new ChromeDriver();
	    Constant.WEBDRIVER.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
	    System.out.println("Post-condition");

	    Constant.WEBDRIVER.quit();
	}
	
	@Test
	public void TC01() {
		System.out.println("TC01 - User can login to Railway Website with valid Username and Password");
		HomePage homePage = new HomePage();
		homePage.open();
		
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(Constant.USERNAME, Constant.PASSWORD);
		Assert.assertTrue(homePage.isLogoutDisplayed() && homePage.isChangePasswordDisplayed(), "Logout and Change Password not displayed when user login");

//		Depend on Window/Browser size (width)
//		String actualString = homePage.getWelcomeMessageString();
//		String expectedString = "Welcome " + Constant.USERNAME;		
//		Assert.assertEquals(actualString, expectedString, "Welcome message is not displayed as expected");
	}
}
