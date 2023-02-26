package com.qa.bbc.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import static com.qa.bbc.constants.AppConstants.*;

public class ArticlePage  {

	private Page page;

	// 1. String Locators - OR
	private String commentIcon = "//a[@class='icon-scroll']";
	private String viewComments = "//button[@class='view-comments-button']";
	private String joinConversation    = "//span[text()='Join the conversation']";
	private String sigInArticle = "//span[text()='Join the conversation']/ancestor::span//*[text()='Sign in']";
	private String setDisplayName = "//span[text()='Join the conversation']/ancestor::span//*[text()='Create a display name']";
	private String CommentInput = "//span[text()='Join the conversation']/ancestor::span//textarea[@class='comments-input-box']";
	private String postBtn = "//input[@type='submit']";

	// 2. page constructor:
	public ArticlePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public boolean verifyArticlePage() {
		try{
			page.locator(commentIcon).waitFor();
			if(page.locator(commentIcon).isVisible()) {
				System.out.println("Article page is loaded successfully");
				return true;
			}else {
				System.out.println("Article page is not loaded");
				return false;
			}
		}catch (PlaywrightException e) {
			System.out.println("article page not found: " + e.getMessage());
			throw e;
		}

	}

	public boolean clickOnComments() {
		try {
			page.click(commentIcon);
			if(page.locator(viewComments).isVisible())
			{
				System.out.println("Moved to commenting section");
				return true;
			}else {
				System.out.println("Commenting section is not visible");
				return false;
			}
		}catch (PlaywrightException e) {
			System.out.println("comment icon not found: " + e.getMessage());
			throw e;
		}
	}

	public boolean clickOnViewComments() {
		try {
			page.click(viewComments);
			if (page.locator(joinConversation).isVisible()) {
				System.out.println("Join Conversation is visible");
				return true;
			} else {
				System.out.println("Join Conversation is invisible");
				return false;
			}
		} catch (PlaywrightException e) {
			System.out.println("comment icon not found: " + e.getMessage());
			throw e;
		}
	}

	public boolean verifySignInIsPrompted() {
		try {
			return (page.locator(sigInArticle).isVisible());
		}catch (PlaywrightException e) {
			System.out.println("login page is not loaded: " + e.getMessage());
			throw e;
		}

	}

	public LoginPage navigateToLoginPage() {
		try {
			page.click(sigInArticle);
			return new LoginPage(page);
		}catch (PlaywrightException e) {
			System.out.println("login page is not loaded: " + e.getMessage());
			throw e;
		}
	}

	public boolean verifyCommentingEnabled() {
		try {
			page.locator(CommentInput).fill(ENTER_COMMENT);
			boolean commentEnabledStatus = page.locator(postBtn).isEnabled();
			return commentEnabledStatus;
		}
		catch (PlaywrightException e) {
			System.out.println("Commenting is not enabled: " + e.getMessage());
			throw e;
		}
	}

	public boolean isSetDisplayeNameVisible()
	{
		try{
			if(page.locator(setDisplayName).isVisible())
			{
				return true;
			}
			else{
				return false;
			}
		}catch (PlaywrightException e) {
			System.out.println("set DisplayName Name error: " + e.getMessage());
			throw e;
		}
	}

	public DisplayNamePage navigateToDisplayName() {
		try {
			page.click(setDisplayName);
			return new DisplayNamePage(page);
		}catch (PlaywrightException e){
			System.out.println("sign-in option not found: " + e.getMessage());
			throw e;
		}
	}

}
