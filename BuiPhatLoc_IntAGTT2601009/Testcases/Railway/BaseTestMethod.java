package Railway;

import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Account.Account;
import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Guerrillamail.GuerrillaMailPage;

public class BaseTestMethod {
	protected HomePage homePage;
	
	@BeforeMethod
	public void beforeMethod() {
	    System.out.println("Start Test");

	    System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
//	    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
	    Constant.WEBDRIVER = new ChromeDriver();
	    Constant.WEBDRIVER.manage().window().maximize();
	    
	    
		homePage = new HomePage();
		homePage.open();
	}
	
	@AfterMethod
	public void afterMethod() {
	    System.out.println("End Test");

	    Constant.WEBDRIVER.quit();
	}
	
	public void createValidAccount() {
	    Account.AccountInfo account = Account.getAccountInfo("TC1");
	    
	    String railwayHandle = Constant.WEBDRIVER.getWindowHandle();
	    
	    Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
	    
	    String emailHandle = Constant.WEBDRIVER.getWindowHandle();
	    GuerrillaMailPage guerrillamalPage = new GuerrillaMailPage();
	    guerrillamalPage.checkGuerillaEmail(account);
	    
	    
	    Constant.WEBDRIVER.switchTo().window(railwayHandle);
	    RegisterPage registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
	    registerPage.register(account, HomePage.class);
	    
	    
	    Constant.WEBDRIVER.switchTo().window(emailHandle);
	    guerrillamalPage.checkConfirmEmail();
	    
//	    Constant.WEBDRIVER.switchTo().window(railwayHandle);
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
	}
	public void createValidAccount(Account.AccountInfo account) {
	    String railwayHandle = Constant.WEBDRIVER.getWindowHandle();
	    
	    Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
	    
	    String emailHandle = Constant.WEBDRIVER.getWindowHandle();
	    GuerrillaMailPage guerrillamalPage = new GuerrillaMailPage();
	    guerrillamalPage.checkGuerillaEmail(account);
	    
	    
	    Constant.WEBDRIVER.switchTo().window(railwayHandle);
	    RegisterPage registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
	    registerPage.register(account, HomePage.class);
	    
	    
	    Constant.WEBDRIVER.switchTo().window(emailHandle);
	    guerrillamalPage.checkConfirmEmail();
	    
//	    Constant.WEBDRIVER.switchTo().window(railwayHandle);
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
	}
	
}
