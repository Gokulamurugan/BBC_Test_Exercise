package com.qa.bbc.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.testng.Assert;

import static com.qa.bbc.constants.AppConstants.SIGNOUT_PAGE_TITLE;

public class AccountPage {

	private Page page;

	// 1. String Locators - OR
	private String accountDetl = "//*[@id='idcta-username']";
	private String signOut= "//span[text()='Sign out']";

	// 2. page constructor:
	public AccountPage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public void navigateToAccountAndSignOut() {
		try {
			page.click(accountDetl);
			page.click(signOut);
			String title = page.title();
			Assert.assertEquals(title, SIGNOUT_PAGE_TITLE);
		} catch (PlaywrightException e) {
			System.out.println("signout failed: " + e.getMessage());
			throw e;
		}
	}

}
