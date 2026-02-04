package Railway;

import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;

public class BaseTestMethod {
	protected HomePage homePage;
	
	@BeforeMethod
	public void beforeMethod() {
	    System.out.println("Start Test");

	    System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
//	    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
	    Constant.WEBDRIVER = new ChromeDriver();
	    Constant.WEBDRIVER.manage().window().maximize();
	    
	}
	
	@BeforeMethod
	public void init(Method method) {
		homePage = new HomePage();
		homePage.open();
	}
	
	@AfterMethod
	public void afterMethod() {
	    System.out.println("End Test");

	    Constant.WEBDRIVER.quit();
	}
}
