package com.qa.bbc.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class HomePage {

	Page page;

	// 1. String Locators - OR
	private String signInLink = "//span[text()='Sign in']";
	private String sportPageLnk = "//span[text()='Sport']";
	private String signInDetl = "//span[contains(@class,'AccountText')]";

	// 2. page constructor:
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getHomePageTitle() {
		try {
			String title = page.title();
			System.out.println("page title: " + title);
			return title;
		}catch (PlaywrightException e){
			System.out.println("Homepage title not found: " + e.getMessage());
			throw e;
		}
	}

	public String getHomePageURL() {
		try {
			String url = page.url();
			System.out.println("page url : " + url);
			return url;
		}catch (PlaywrightException e){
			System.out.println("Homepage URL not found " + e.getMessage());
			throw e;
		}
	}

	public LoginPage navigateToSignInPage() {
		try {
			page.click(signInLink);
			return new LoginPage(page);
		}catch (PlaywrightException e){
			System.out.println("sign-in option not found: " + e.getMessage());
			throw e;
		}
	}

	public boolean verifyLogin() {
		try {
			if (page.locator(signInDetl).innerText() != "Sign in") {
				System.out.println("user is logged in successfully....");
				return true;
			} else {
				System.out.println("incorrect user name or password....");
				return false;
			}
		}catch (PlaywrightException e1){
			System.out.println("sign-in failed " + e1.getMessage());
			throw e1;
		}

	}


	public SportPage navigateToSportPage(){
		try {
			page.click(sportPageLnk);
			return new SportPage(page);
		}catch (PlaywrightException e){
			System.out.println("sport page link not found: " + e.getMessage());
			throw e;
		}
	}

}
