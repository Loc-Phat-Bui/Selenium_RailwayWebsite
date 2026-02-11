package Railway;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Datas.Account;
import Guerrillamail.GuerrillaMailPage;

public class BaseTestMethod {
	protected HomePage homePage;
	protected String railwayHandler;
	protected String emailHandler;
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional("firefox") String browser) {
		System.out.println("Start Test on: " + browser);
	    
	    if(browser.equalsIgnoreCase("chrome")) {
	    	System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
		    
		    Constant.WEBDRIVER = new ChromeDriver();
	    } else if(browser.equalsIgnoreCase("firefox")) {
	    	System.setProperty("webdriver.gecko.driver", Constant.FIREFOX_DRIVER_PATH);
	    	
	    	Constant.WEBDRIVER = new FirefoxDriver();
	    } else {
	    	throw new RuntimeException("Unsupported" + browser);
	    }

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
	    Account.AccountInfo account = Account.getAccountInfo("TC00");
	    
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
	    
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
	}
	public void createValidAccount(Account.AccountInfo account) {
	    railwayHandler = Constant.WEBDRIVER.getWindowHandle();
	    
	    RegisterPage registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
	    registerPage.register(account, HomePage.class);
	    
	    Utilities.switchToNewTab(Constant.GUERRILLA_MAIL_URL);
	    
	    emailHandler = Constant.WEBDRIVER.getWindowHandle();
	    GuerrillaMailPage guerrillamalPage = new GuerrillaMailPage();
	    guerrillamalPage.checkGuerillaEmail(account);
	    
	    guerrillamalPage.checkConfirmEmail();
	    
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
	}
	
	 public Account.AccountInfo accountSetup (String tcID) {
		 Account.AccountInfo account =  Account.getAccountInfo(tcID);
		 return account;
	 }
	 public Account.AccountInfo accountSetup (String tcID, boolean createNew) {
		 Account.AccountInfo account;
		 
		 if(createNew) {
			 account =  Account.getAccountInfo(tcID);
			 this.createValidAccount(account);
		 } else {
			 account =  Account.getAccountInfo("TC00");
		 } 
		 return account;
	 }
}
