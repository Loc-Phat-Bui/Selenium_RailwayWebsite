package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Datas.Account;
import Guerrillamail.GuerrillaMailPage;

public class TestBase {
	protected HomePage homePage;
	protected String railwayHandler;
	protected String emailHandler;
	
	protected LoginPage loginPage;
	protected RegisterPage registerPage;
	protected ResetPasswordPage resetPasswordPage;
	protected BookTicketPage bookTicketPage;
	protected MyTicketPage myTicketPage;
	protected TimetablePage timetablePage;
	protected TicketPricePage ticketPricePage;
	
	protected String actualString;
	protected String expectedString;
	protected String departDate;
	
	protected Account.AccountInfo account;
	
	protected SoftAssert softAssert = new SoftAssert();
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional("chrome") String browser) {
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
		
		softAssert = new SoftAssert();
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
	    registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
	    registerPage.register(account, HomePage.class);
	    
	    Constant.WEBDRIVER.switchTo().window(emailHandle);
	    guerrillamalPage.checkConfirmEmail();
	    
	    for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
	        Constant.WEBDRIVER.switchTo().window(handle);
	    }
	}
	public void createValidAccount(Account.AccountInfo account) {
	    railwayHandler = Constant.WEBDRIVER.getWindowHandle();
	    
	    registerPage = homePage.gotoTabPage(Macros.TAB_MENU_REGISTER, RegisterPage.class);
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
		 Account.AccountInfo tmpAccount =  Account.getAccountInfo(tcID);
		 return tmpAccount;
	 }
	 public Account.AccountInfo accountSetup (String tcID, boolean createNew) {
		 Account.AccountInfo tmpAccount;
		 
		 if(createNew) {
			 tmpAccount =  Account.getAccountInfo(tcID);
			 this.createValidAccount(tmpAccount);
		 } else {
			 tmpAccount =  Account.getAccountInfo("TC00");
		 } 
		 return tmpAccount;
	 }
}
