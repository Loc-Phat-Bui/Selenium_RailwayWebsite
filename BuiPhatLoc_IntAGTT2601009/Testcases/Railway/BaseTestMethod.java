package Railway;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Utilities;
import Constant.Constant;
import Constant.Macros;
import Datas.Account;
import Guerrillamail.GuerrillaMailPage;

public class BaseTestMethod {
	protected HomePage homePage;
	protected String railwayHandler;
	protected String emailHandler;
	
	@BeforeMethod
	public void beforeMethod() {
	    System.out.println("Start Test");

	    System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
	    
	 // Configure Chrome Options to prevent popups and improve stability
	    ChromeOptions options = new ChromeOptions();
	    
	    // Block popups and notifications
	    options.addArguments("--disable-popup-blocking");
	    options.addArguments("--disable-notifications");
	    
	    // Prevent automated detection
	    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	    options.setExperimentalOption("useAutomationExtension", false);
	    
	    // Disable infobars
	    options.addArguments("--disable-infobars");
	    
	    // Set page load strategy
	    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	    
	    // Add user agent to appear more like real browser
	    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36");
	    
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
	
}
