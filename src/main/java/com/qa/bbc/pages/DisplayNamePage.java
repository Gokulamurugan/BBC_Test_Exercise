package com.qa.bbc.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class DisplayNamePage {

	private Page page;

	// 1. String Locators - OR
	private String displayNameTxt = "//input[@id='displayName-input']";
	private String submitBtn = "//button[@id='submit-button']";
	private String continueBtn = "//a[@id='return-to-ptrt']";

	// 2. page constructor:
	public DisplayNamePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public ArticlePage setDisplayName(String displayName) {
		try {
			page.locator(displayNameTxt).fill(displayName);
			page.click(submitBtn);
			page.click(continueBtn);
		}catch (PlaywrightException e) {
			System.out.println("Display name page error: " + e.getMessage());
			throw e;
		}
		return new ArticlePage(page);
	}

}
