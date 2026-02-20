package AutomationExercise;

import org.testng.annotations.Test;

import AutomationExerciseEnum.NavBar;
import Common.ActionUtilities;
import Constant.Constant;

public class SeleniumExam extends TestBase{
	private final boolean createAccount = true;
	private final boolean useDefaultAccount = false;
	private String actualString;
	private String expectedString;
	private boolean cleanup = false;
	
	@Test
	public void TC_01() {
		System.out.println("TC_01 - Verify user can submit a product review successfully");
		account = this.accountSetup("TC_01", useDefaultAccount);
		
		System.out.println("Pre-Condition: User is not required to be logged in");
		
		System.out.println("Step: 1 Navigate to the URL");
		System.out.println("Step: 2 Click Products");
		ActionUtilities.removeAds();
		productPage = homePage.gotoPageByNavbar(NavBar.PRODUCT, ProductPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			productPage = homePage.gotoPageByNavbar(NavBar.PRODUCT, ProductPage.class);
		}
		
		System.out.println("Step: 3 Select any product by clicking View Product on a random item");
		
		ActionUtilities.removeAds();
		productPage = productPage.viewProductDetail(ProductPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			productPage = productPage.viewProductDetail(ProductPage.class);
		}
		
		System.out.println("Step: 4 Verify that the “Write Your Review” section is displayed");
		
		softAssert.assertTrue(productPage.isReviewSectionDisplayed(), "Review Section is not displayed");
		
		System.out.println("Step: 5 Enter valid Name, Email, and Review text");
		System.out.println("Step: 6 Click Submit");
		ActionUtilities.removeAds();
		productPage = productPage.writeYourReview(account, ProductPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			productPage = productPage.writeYourReview(account, ProductPage.class);
		}
		
		System.out.println("Step: 7 Verify the success message “Thank you for your review.” is displayed.");
		softAssert.assertTrue(productPage.isMsgThankYouDisplayed(), "Thank you message is not displayed");
		actualString = productPage.getText_MsgThankYou();
		expectedString = "Thank you for your review.";
		softAssert.assertEquals(actualString, expectedString, "Incorrect Message Content");
		
		softAssert.assertAll();
	}
	
	@Test
	public void TC_02() {
		System.out.println("TC_02 - Verify delivery & billing addresses match registration details");
		System.out.println("Step: 1 Navigate to the URL");
		System.out.println("Step: 2 Click Signup / Login");
		System.out.println("Step: 3 Create a new account with random email (name_<timestamp>@test.com)");
		System.out.println("Step: 4 Click Continue");
		ActionUtilities.removeAds();
		account = this.accountSetup(TC_02_ID, createAccount);
		
		System.out.println("Step: 5 Add a random product to the cart");
		System.out.println("Step: 6 In the confirmation popup, click View Cart");
		ActionUtilities.removeAds();
		cartPage = homePage.addProductToCart(CartPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			cartPage = homePage.addProductToCart(CartPage.class);
		}
		
		System.out.println("Step: 7 Click Proceed to Checkout");
		ActionUtilities.removeAds();
		cartPage = cartPage.proceedToCheckout(CartPage.class);
		if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
		{
			cartPage = cartPage.proceedToCheckout(CartPage.class);
		}
		
		System.out.println("Step: 8 Verify that the Delivery Address matches the address entered during registration");
		ActionUtilities.removeAds();
		softAssert.assertTrue(cartPage.isDeliveryInfoMatch(account));
		
		softAssert.assertAll();
		
		if(cleanup) {
			System.out.println("Cleaning Up");
			ActionUtilities.removeAds();
			homePage = cartPage.gotoPageByNavbar(NavBar.DELETE, HomePage.class);
			if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
			{
				homePage = cartPage.gotoPageByNavbar(NavBar.DELETE, HomePage.class);
			}
			ActionUtilities.removeAds();
			homePage = homePage.clickContinue(HomePage.class);
			if(Constant.WEBDRIVER.getCurrentUrl().contains("#google_vignette"))
			{
				homePage = homePage.clickContinue(HomePage.class);
			}
		}
	}
}
