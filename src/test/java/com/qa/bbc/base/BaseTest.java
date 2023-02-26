package com.qa.bbc.base;

import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import com.qa.bbc.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.qa.bbc.factory.PlaywrightFactory;

public class BaseTest {

	PlaywrightFactory pf;
	Page page;
	BrowserContext browserContext;
	protected Properties prop;

	protected HomePage homePage;
	protected LoginPage loginPage;
	protected SportPage sportPage;
	protected ArticlePage articlePage;
	protected AccountPage accountPage;
	protected DisplayNamePage displayNamePage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		pf = new PlaywrightFactory();

		prop = pf.init_prop();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		browserContext = pf.initBrowser(prop);
		browserContext.tracing().start(new Tracing.StartOptions()
				.setScreenshots(true)
				.setSnapshots(true)
				.setSources(true));
		page = browserContext.newPage();
		page.navigate(prop.getProperty("url").trim());
		homePage = new HomePage(page);
	}



	@AfterTest
	public void tearDown() {
		accountPage = new AccountPage(page);
		accountPage.navigateToAccountAndSignOut();
		browserContext.tracing().stop(new Tracing.StopOptions()
				.setPath(Paths.get("trace.zip")));
		page.context().browser().close();

	}

}
