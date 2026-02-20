package AutomationExercise;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import AutomationExerciseData.AccountData;
import AutomationExerciseData.AccountData.Account;
import AutomationExerciseEnum.NavBar;
import Common.ActionUtilities;
import Constant.Constant;

public class TestBase {
	protected SoftAssert softAssert;
	
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected SignUpPage signUpPage;
	protected ProductPage productPage;
	protected CartPage cartPage;
	
	protected Account account;
	
	protected String TC_02_ID;
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional("chrome") String browser) {
		System.out.println("Start Test on: " + browser);
	    
	    if(browser.equalsIgnoreCase("chrome")) {
	    	System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
		    
	    	ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");

	        Constant.WEBDRIVER = new ChromeDriver(options);
	        
	        TC_02_ID = "TC_02_Chrome";
	    } else if(browser.equalsIgnoreCase("firefox")) {
	    	System.setProperty("webdriver.gecko.driver", Constant.FIREFOX_DRIVER_PATH);
	    	
	    	FirefoxOptions options = new FirefoxOptions();
	        options.addPreference("dom.disable_open_during_load", true);
	        
	        Constant.WEBDRIVER = new FirefoxDriver(options);
	        
	        TC_02_ID = "TC_02_Firefox";
	    } else {
	    	throw new RuntimeException("Unsupported" + browser);
	    }

	    Constant.WEBDRIVER.manage().window().maximize();
	    
	    homePage = new HomePage().open();
		
		softAssert = new SoftAssert();
	}
	
	@AfterMethod
	public void afterMethod() {
	    System.out.println("End Test");

	    Constant.WEBDRIVER.quit();
	}
	
	public Account accountSetup(String tcID, boolean createAccount) {
		if(createAccount) {
			this.createAccount(tcID);
			return AccountData.getAccountInfo(tcID);
		} else {
			return AccountData.getAccountInfo("TC_00");
		}
	}
	
	public void createAccount(String tcID) {
		Account tmpAccount = AccountData.getAccountInfo(tcID);
		
		ActionUtilities.removeAds();
		loginPage = homePage.gotoPageByNavbar(NavBar.LOGIN, LoginPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			loginPage = homePage.gotoPageByNavbar(NavBar.LOGIN, LoginPage.class);
		}
		
		ActionUtilities.removeAds();
		signUpPage = loginPage.signup(tmpAccount, SignUpPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			signUpPage = loginPage.signup(tmpAccount, SignUpPage.class);
		}
		
		ActionUtilities.removeAds();
		homePage = signUpPage.createAccount(tmpAccount, HomePage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			homePage = signUpPage.createAccount(tmpAccount, HomePage.class);
		}
		
		ActionUtilities.removeAds();
		homePage = homePage.clickContinue(HomePage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			homePage = homePage.clickContinue(HomePage.class);
		}
	}
}
