package AutomationExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AutomationExerciseData.AccountData.Account;
import AutomationExerciseEnum.Brand;
import Common.ActionUtilities;
import Common.WaitUtilities;
import Constant.Constant;

public class ProductPage extends GeneralPage {
	/*
	** LOCATORS
	*/
	private final By brandPolo = By.xpath("//div[@class='brands-name']//a[contains(@href,'Polo')]");
	private final By brandHM = By.xpath("//div[@class='brands-name']//a[contains(@href,'H&M')]");
	private final By brandMadame = By.xpath("//div[@class='brands-name']//a[contains(@href,'Madame')]");
	private final By brandMastHarbour = By.xpath("//div[@class='brands-name']//a[contains(@href,'Mast & Harbour')]");
	private final By brandBabyhug = By.xpath("//div[@class='brands-name']//a[contains(@href,'Babyhug')]");
	private final By brandAllenSolly = By.xpath("//div[@class='brands-name']//a[contains(@href,'Allen Solly Junior')]");
	private final By brandKookieKids = By.xpath("//div[@class='brands-name']//a[contains(@href,'Kookie Kids')]");
	private final By brandBiba = By.xpath("//div[@class='brands-name']//a[contains(@href,'Biba')]");

	private final By viewProductMenTshirt = By.xpath("//p[contains(text(),'Men Tshirt')]/ancestor::div[contains(@class,'product-image-wrapper')]//a[contains(@href,'product_details')]");
	private final By addcartProductMenTshirt = By.xpath("//div[contains(@class,'features_items')]//div[contains(@class,'productinfo')]//p[contains(text(),'Men Tshirt')]/following-sibling::a[contains(@class,'add-to-cart')]");
	
	private final By reviewSection = By.xpath("//a[contains(@href,'reviews') and contains(text(), 'Write Your Review')]/ancestor::div[contains(@class, 'category-tab shop-details-tab')]");
	private final By inputReviewName = By.xpath("//input[contains(@id,'name')]");
	private final By inputReviewEmail = By.xpath("//input[contains(@id,'email')]");
	private final By textareaReviewContent = By.xpath("//textarea[contains(@id,'review')]");
	private final By buttonReviewSubmit = By.xpath("//button[contains(@id,'button-review')]");
	private final By msgThankYou = By.xpath("//span[contains(text(),'Thank you for your review.')]");
	/*
	** ELEMENTS
	*/
	protected WebElement getElement_BrandPolo() {
		return WaitUtilities.waitForElementClickable(brandPolo);
	}
	protected WebElement getElement_BrandHM() {
		return WaitUtilities.waitForElementClickable(brandHM);
	}
	protected WebElement getElement_BrandMadame() {
		return WaitUtilities.waitForElementClickable(brandMadame);
	}
	protected WebElement getElement_BrandMastHarbour() {
		return WaitUtilities.waitForElementClickable(brandMastHarbour);
	}
	protected WebElement getElement_BrandBabyhug() {
		return WaitUtilities.waitForElementClickable(brandBabyhug);
	}
	protected WebElement getElement_BrandAllenSolly() {
		return WaitUtilities.waitForElementClickable(brandAllenSolly);
	}
	protected WebElement getElement_BrandKookieKids() {
		return WaitUtilities.waitForElementClickable(brandKookieKids);
	}
	protected WebElement getElement_BrandBiba() {
		return WaitUtilities.waitForElementClickable(brandBiba);
	}
	
	protected WebElement getElement_ViewMenTshirt() {
		return WaitUtilities.waitForElementClickable(viewProductMenTshirt);
	}
	protected WebElement getElement_AddcartMenTshirt() {
		return WaitUtilities.waitForElementClickable(addcartProductMenTshirt);
	}
	
	protected WebElement getElement_InputReviewName() {
		return WaitUtilities.waitForElementClickable(inputReviewName);
	}
	protected WebElement getElement_InputReviewEmail() {
		return WaitUtilities.waitForElementClickable(inputReviewEmail);
	}
	protected WebElement getElement_TextareaReviewContent() {
		return WaitUtilities.waitForElementClickable(textareaReviewContent);
	}
	protected WebElement getElement_ButtonReviewSubmit() {
		return WaitUtilities.waitForElementClickable(buttonReviewSubmit);
	}
	
	/*
	** METHODS
	*/
	public <T> T selectBrand(Brand brand, Class<T> returnPage) {
		WebElement webElement = null;
		
		switch (brand) {
		case POLO:
			webElement = getElement_BrandPolo();
			break;
		case H_M:
			webElement = getElement_BrandHM();
			break;
		case MADAME:
			webElement = getElement_BrandMadame();
			break;
		case MAST_HARBOUR:
			webElement = getElement_BrandMastHarbour();
			break;
		case BABYHUG:
			webElement = getElement_BrandBabyhug();
			break;
		case ALLEN_SOLLY_JUNIOR:
			webElement = getElement_BrandAllenSolly();
			break;
		case KOOKIE_KIDS:
			webElement = getElement_BrandKookieKids();
			break;
		case BIBA:
			webElement = getElement_BrandBiba();
			break;
		default:
			throw new IllegalArgumentException("Unexpected brand: " + brand);
		}
		
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(webElement);
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public <T> T viewProductDetail(Class<T> returnPage) {
		
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(getElement_ViewMenTshirt());
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isReviewSectionDisplayed() {
		if(Constant.WEBDRIVER.findElements(reviewSection).isEmpty()) return false;
		return true;
	}
	
	public <T> T writeYourReview(Account account, Class<T> returnPage) {
		
		ActionUtilities.removeAds();
		
		ActionUtilities.sendKeyAction(getElement_InputReviewName(), account.getName());
		ActionUtilities.sendKeyAction(getElement_InputReviewEmail(), account.getEmail());
		ActionUtilities.sendKeyAction(getElement_TextareaReviewContent(), account.getReview());
		
		ActionUtilities.clickAction(getElement_ButtonReviewSubmit());
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	public boolean isMsgThankYouDisplayed() {
		return Constant.WEBDRIVER.findElement(msgThankYou).isDisplayed();
	}
	public String getText_MsgThankYou() {
		return Constant.WEBDRIVER.findElement(msgThankYou).getText();
	}
	
	public <T> T addProductToCart(Class<T> returnPage) {
		
		ActionUtilities.removeAds();
		
		ActionUtilities.clickAction(getElement_AddcartMenTshirt());
		
		ActionUtilities.clickAction(getElement_CartmodalViewCart());
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	} 
}