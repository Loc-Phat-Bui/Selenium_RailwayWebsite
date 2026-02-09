package Guerrillamail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.SafetyUtilities;
import Common.Utilities;
import Common.WaitUtilities;
import Datas.Account;

public class GuerrillaMailPage {
	// Locators
	private final By btnMailName = By.xpath("//span[@id='inbox-id']");
	private final By txtboxMailName = By.xpath("//span[@id='inbox-id']/input");
	private final By btnSetMail = By.xpath("//button[@class='save button small']");
	private final By selectMailHost = By.xpath("//select[@id='gm-host-select']");
	private final By chkboxScrambleAddress = By.xpath("//input[@id='use-alias']");
	private final By tabEmail = By.xpath("//a[@href='/inbox']");
	private final By mailConfirmMail = By.xpath("//td[contains(text(),'thanhletraining03@gmail.com')]/..//td[contains(text(),'Please confirm')]/..");
	private final By mailResetPWMail = By.xpath("//td[contains(text(),'thanhletraining03@gmail.com')]/..//td[contains(text(),'Please reset')]/..");
	private final By mailConfirmMailLink = By.xpath("//a[contains(@href,'http://www.saferailway.somee.com')]");
	
	// Elements
	protected WebElement getBtnMailNameWebElement() {
		return WaitUtilities.waitForElementClickable(btnMailName);
	}
	protected WebElement getTxtboxMailNameWebElement() {
	    return WaitUtilities.waitForElementVisible(txtboxMailName);
	}
	protected WebElement getBtnSetMailWebElement() {
	    return WaitUtilities.waitForElementClickable(btnSetMail);
	}
	protected WebElement getSelectMailHostWebElement() {
	    return WaitUtilities.waitForElementVisible(selectMailHost);
	}
	protected WebElement getChkboxScrambleAddressWebElement() {
	    return WaitUtilities.waitForElementClickable(chkboxScrambleAddress);
	}
	protected WebElement getTabEmailWebElement() {
	    return WaitUtilities.waitForElementClickable(tabEmail);
	}
	protected WebElement getMailConfirmMailWebElement() {
	    return WaitUtilities.waitForElementVisible(mailConfirmMail, 60);
	}
	protected WebElement getMailConfirmMailLinkWebElement() {
	    return WaitUtilities.waitForElementClickable(mailConfirmMailLink, 60);
	}
	protected WebElement getMailResetPWMailWebElement() {
	    return WaitUtilities.waitForElementClickable(mailResetPWMail, 60);
	}
	
	// Methods
	public void checkGuerillaEmail(Account.AccountInfo account) {
		String[] splitedEmail = Utilities.splitEmail(account.getUsername());
		
		SafetyUtilities.safeClick(getBtnMailNameWebElement());
		SafetyUtilities.safeSendkey(getTxtboxMailNameWebElement(), splitedEmail[0]);
		SafetyUtilities.safeClick(getBtnSetMailWebElement());
		SafetyUtilities.safeSelect(getSelectMailHostWebElement(), splitedEmail[1]);
		SafetyUtilities.safeCheckChkBox(getChkboxScrambleAddressWebElement(), false);
	}
	
	public void checkConfirmEmail() {
		SafetyUtilities.safeClick(getMailConfirmMailWebElement());
		SafetyUtilities.safeClick(getMailConfirmMailLinkWebElement());
	}
	
	public void checkResetEmail() {
		SafetyUtilities.safeClick(getMailResetPWMailWebElement());
		SafetyUtilities.safeClick(getMailConfirmMailLinkWebElement());
	}
}
