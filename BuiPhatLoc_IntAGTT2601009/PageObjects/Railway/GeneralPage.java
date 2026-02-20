package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ActionUtilities;
import Common.WaitUtilities;
import Constant.Constant;
import RailwayEnum.Button;
import RailwayEnum.TabContent;
import RailwayEnum.TabMenu;
import RailwayEnum.TextBox;
import RailwayEnum.ValidationError;

public class GeneralPage {
	private String tabElementXpath = "//div[@id='%s']//a[contains(@href,'%s')]";
	private String txtboxElementXpath = "//input[@id='%s']";
	private String btnElementXpath	=	"//input[@value='%s']";
	private String labelValidationErrorXpath = "//label[contains(@for,'%s') and contains(@class,'validation-error')]";
	/* 
	** Locators
	*/
	// ------ Static Locator ------ //
	private final By lblWelomeMessage = By.xpath("//div[@class='account']/strong");
	private final By lblErrorMSG = By.xpath("//p[@class='message error']");
	// ------ Dynamic Locator ------ //
	private By getTabLocator(TabMenu tabMenu) {
        String tmpXpath = String.format(tabElementXpath, tabMenu.getDiv(), tabMenu.getHref());
        return By.xpath(tmpXpath);
    }

    private By getTabLocator(TabContent tabContent) {
        String tmpXpath = String.format(tabElementXpath, tabContent.getDiv(), tabContent.getHref());
        return By.xpath(tmpXpath);
    }

    private By getTxtBoxLocator(TextBox txtBox) {
        String tmpXpath = String.format(txtboxElementXpath, txtBox.getValue());
        return By.xpath(tmpXpath);
    }

    private By getBtnLocator(Button btnName) {
        String tmpXpath = String.format(btnElementXpath, btnName.getValue());
        return By.xpath(tmpXpath);
    }
    
    private By getLabelValidationErrorLocator(ValidationError labelName) {
        String tmpXpath = String.format(labelValidationErrorXpath, labelName.getValue());
        return By.xpath(tmpXpath);
    }
	/* 
	** Elements
	*/
	// General elements
	protected WebElement getTabWebElement(TabMenu tabMenu) {
        return WaitUtilities.waitForElementClickable(getTabLocator(tabMenu));
    }

    protected WebElement getTabWebElement(TabContent tabContent) {
        return WaitUtilities.waitForElementClickable(getTabLocator(tabContent));
    }

    protected WebElement getTxtBoxWebElement(TextBox txtBox) {
        return WaitUtilities.waitForElementVisible(getTxtBoxLocator(txtBox));
    }

    protected WebElement getBtnWebElement(Button btnName) {
        return WaitUtilities.waitForElementClickable(getBtnLocator(btnName));
    }

    protected WebElement getLabelValidationErrorWebElement(ValidationError labelName) {
        return WaitUtilities.waitForElementVisible(getLabelValidationErrorLocator(labelName));
    }

    protected WebElement getLblErrorMSGWebElement() {
        return WaitUtilities.waitForElementVisible(lblErrorMSG);
    }
	// Specific elements
	protected WebElement getLblWelcomeMessageWebElement() {
		return Constant.WEBDRIVER.findElement(this.lblWelomeMessage);
	}
	/* 
	** Methods
	*/
	// ------ Check Functions ------ //
	public boolean checkTabElementAvailable(TabMenu tabMenu) {
        return !Constant.WEBDRIVER.findElements(getTabLocator(tabMenu)).isEmpty();
    }

    public boolean checkTabElementAvailable(TabContent tabContent) {
        return !Constant.WEBDRIVER.findElements(getTabLocator(tabContent)).isEmpty();
    }

    public boolean checkPageURL(TabMenu tabMenu) {
        WebElement tab = getTabWebElement(tabMenu);
        String expectedHref = tab.getAttribute("href");
        String pageURL = Constant.WEBDRIVER.getCurrentUrl();
        return pageURL.contains(expectedHref);
    }
	
	// ------ Goto Page by clicking Tab Menu/Content link ------ //
	public <T> T gotoTabPage (TabMenu tabMenu, Class<T> returnPage) {
		ActionUtilities.click_Action(this.getTabWebElement(tabMenu));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	public <T> T gotoTabPage (TabContent tabContent, Class<T> returnPage) {
		ActionUtilities.click_Action(this.getTabWebElement(tabContent));
		
		try {
			return returnPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create instance of " + returnPage);
		}
	}
	
	// ------ Get text from the website ------ //
	public String getWelcomeMessageString() {
        return this.getLblWelcomeMessageWebElement().getText();
    }

    public String getLabelValidationErrorText(ValidationError labelName) {
        return getLabelValidationErrorWebElement(labelName).getText();
    }
}
