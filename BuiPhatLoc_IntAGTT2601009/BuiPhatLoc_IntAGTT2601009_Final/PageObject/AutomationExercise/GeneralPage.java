package AutomationExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import AutomationExerciseEnum.NavBar;
import Common.ActionUtilities;
import Common.WaitUtilities;
import Constant.Constant;

public class GeneralPage {
	/*
	** LOCATORS
	*/
	private final By navbarHome = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/') and contains(text(),'Home')]");
	private final By navbarProducts = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/products') and contains(text(),' Products')]");
	private final By navbarCart = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/view_cart') and contains(text(),'Cart')]");
	private final By navbarLogin = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/login') and contains(text(),'Login')]");
	private final By navbarTestCase = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/test_cases') and contains(text(),'Test Cases')]");
	private final By navbarAPI = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'api_list') and contains(text(),'API Testing')]");
	private final By navbarVideo = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'www.youtube.com') and contains(text(),'Video Tutorials')]");
	private final By navbarContact = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/contact_us') and contains(text(),'Contact us')]");
	private final By navbarLogout = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/logout') and contains(text(),'Logout')]");
	private final By navbarDeleteAccount = By.xpath("//div[contains(@class,'shop-menu')]//a[contains(@href,'/delete_account') and contains(text(),'Delete Account')]");
	
	private final By buttonContinue = By.xpath("//a[contains(@data-qa,'continue-button')]");
	
	private final By cartmodalViewCart = By.xpath("//div[contains(@id,'cartModal')]//a[contains(@href,'/view_cart')]");
	/*
	** ELEMENTS
	*/
	protected WebElement getNavbarElement_Home() {
		return WaitUtilities.waitForElementClickable(navbarHome);
	}
	protected WebElement getNavbarElement_Products() {
		return WaitUtilities.waitForElementClickable(navbarProducts);
	}
	protected WebElement getNavbarElement_Cart() {
		return WaitUtilities.waitForElementClickable(navbarCart);
	}
	protected WebElement getNavbarElement_Login() {
		return WaitUtilities.waitForElementClickable(navbarLogin);
	}
	protected WebElement getNavbarElement_TestCase() {
		return WaitUtilities.waitForElementClickable(navbarTestCase);
	}
	protected WebElement getNavbarElement_API() {
		return WaitUtilities.waitForElementClickable(navbarAPI);
	}
	protected WebElement getNavbarElement_Video() {
		return WaitUtilities.waitForElementClickable(navbarVideo);
	}
	protected WebElement getNavbarElement_Contact() {
		return WaitUtilities.waitForElementClickable(navbarContact);
	}
	protected WebElement getNavBarElement_Logout() {
		return WaitUtilities.waitForElementClickable(navbarLogout);
	}
	protected WebElement getNavBarElement_DeleteAccount() {
		return WaitUtilities.waitForElementClickable(navbarDeleteAccount);
	}
	
	protected WebElement getElement_ButtonContinue() {
		return WaitUtilities.waitForElementClickable(buttonContinue);
	}
	protected WebElement getElement_CartmodalViewCart() {
		return WaitUtilities.waitForElementClickable(cartmodalViewCart);
	}
	/*
	** METHODS
	*/
	public <T> T gotoPageByNavbar(NavBar navBar, Class<T> returnPage) {
		WebElement webElement = null;
		
		switch (navBar) {
		case HOME:
			webElement = getNavbarElement_Home();
			break;
		case PRODUCT:
			webElement = getNavbarElement_Products();
			break;
		case CART:
			webElement = getNavbarElement_Cart();
			break;
		case LOGIN:
			webElement = getNavbarElement_Login();
			break;
		case TESTCASE:
			webElement = getNavbarElement_TestCase();
			break;
		case APITEST:
			webElement = getNavbarElement_API();
			break;
		case VIDEO:
			webElement = getNavbarElement_Video();
			break;
		case CONTACT:
			webElement = getNavbarElement_Contact();
			break;
		case LOGOUT:
			webElement = getNavBarElement_Logout();
			break;
		case DELETE:
			webElement = getNavBarElement_DeleteAccount();
			break;
		default:
			break;
		}
		
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(webElement);
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T clickContinue(Class<T> returnPage) {
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(getElement_ButtonContinue());
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
}
