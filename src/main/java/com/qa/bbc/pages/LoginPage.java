package com.qa.bbc.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class LoginPage {

	private Page page;

	// 1. String Locators - OR
	private String emailId = "//input[@type='email']";
	private String password = "//input[@type='password']";
	private String sigInBtn = "//button[@type='submit']";
	private String pageTitle = "//h1/span[text()='Sign in']";

	// 2. page constructor:
	public LoginPage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getSignInPageTitle() {
		try {
			String title = page.locator(pageTitle).innerText();
			System.out.println("Sign-in page title is " +title);
			return title;
		}catch (PlaywrightException e){
			System.out.println("login page not found: " + e.getMessage());
			throw e;
		}
	}

	public HomePage SignIn(String appUserName, String appPassword) {
		try {
			page.fill(emailId, appUserName);
			page.fill(password, appPassword);
			page.click(sigInBtn);
			return new HomePage(page);
		}catch (PlaywrightException e){
			System.out.println("sign-in failed: " + e.getMessage());
			throw e;
		}
	}

	public ArticlePage ArtSignIn(String appUserName, String appPassword) {
		try {
			SignIn(appUserName,appPassword);
			return new ArticlePage(page);
		}catch (PlaywrightException e){
			System.out.println("sign-in failed: " + e.getMessage());
			throw e;
		}
	}

}
