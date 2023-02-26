package com.qa.bbc.tests;

import com.qa.bbc.base.BaseTest;
import com.qa.bbc.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.qa.bbc.constants.AppConstants.DISPLAY_NAME;

public class VerifyCommentingSignedUserTest extends BaseTest {

	@Test(priority = 1)
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("url"));
	}

	@Test(priority = 3)
	public void navigateSignInPageTest() {
		loginPage = homePage.navigateToSignInPage();
		String actualTitle = loginPage.getSignInPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 4)
	public void SigninTest() {
		homePage = loginPage.SignIn(prop.getProperty("username").trim(),
				prop.getProperty("password").trim());
		Assert.assertTrue(homePage.verifyLogin());
	}

	@Test(priority = 5)
	public void navigateSportPageTest() {
		sportPage = homePage.navigateToSportPage();
		String actualTitle = sportPage.getSportPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.SPORT_PAGE_TITLE);
	}

	@Test(priority = 6)
	public void findArticleWithCommentIconTest() {
		int commentCount = sportPage.findArticleWithCommentIcon();
		Assert.assertTrue(commentCount > 0,"Articles with comment icon found");
	}

	@Test(priority = 7)
	public void navigateToArticlePageTest() {
		articlePage = sportPage.navigateToArticlePage();
		Assert.assertTrue(articlePage.verifyArticlePage());
	}

	@Test(priority = 8)
	public void navigateToCommentsTest() {
		Assert.assertTrue(articlePage.clickOnComments());
		Assert.assertTrue(articlePage.clickOnViewComments());
	}

	@Test(priority = 9)
	public void verifyCommentingEnabledTest() {
		if (articlePage.isSetDisplayeNameVisible() == true) {
			displayNamePage = articlePage.navigateToDisplayName();
			articlePage = displayNamePage.setDisplayName(DISPLAY_NAME);
			Assert.assertTrue(articlePage.clickOnComments());
			Assert.assertTrue(articlePage.clickOnViewComments());
		} else {
			System.out.println("Display Name is already set");
		}
		Assert.assertTrue(articlePage.verifyCommentingEnabled());
	}


}
