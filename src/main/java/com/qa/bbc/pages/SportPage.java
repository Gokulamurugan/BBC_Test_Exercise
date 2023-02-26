package com.qa.bbc.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import java.util.List;

public class SportPage {

	private Page page;

	// 1. String Locators - OR
	private String commentIcon = "//span[@data-testid='participate:comments']/..";
	private String articleName = "//span[@data-testid='participate:comments']/ancestor::li//p/span";

	// 2. page constructor:
	public SportPage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getSportPageTitle() {
		try {
			String title = page.title();
			System.out.println("page title: " + title);
			return title;
		}catch (PlaywrightException e){
			System.out.println("sport page not found: " + e.getMessage());
			throw e;
		}
	}

	public int findArticleWithCommentIcon() {
		try {
			Locator commentList = page.locator(commentIcon);
			System.out.println("total articles with comment icon : " + commentList.count());
			List<String> commentsCount = commentList.allTextContents();
			Locator article_ele = page.locator(articleName);
			List<String> article_list = article_ele.allTextContents();
			for(int i =0; i<commentsCount.size(); i++){
				System.out.println(String.format("%s : Comment Count - %s",article_list.get(i),commentsCount.get(i)));
			}
			return commentList.count();
		}catch (PlaywrightException e){
			System.out.println("comment icon not found: " + e.getMessage());
			throw e;
		}
	}

	public ArticlePage navigateToArticlePage() {
		try {
			page.locator(articleName).first().click();
			return new ArticlePage(page);
		}catch (PlaywrightException e){
			System.out.println("article with comment icon not found: " + e.getMessage());
			throw e;
		}
	}

}
