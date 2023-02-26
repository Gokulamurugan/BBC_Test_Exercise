package com.qa.bbc.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;
import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class PlaywrightFactory {

	Properties prop;
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;


	public BrowserContext initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);
		Boolean headless = Boolean.parseBoolean(prop.getProperty("headless").trim());
		 playwright = Playwright.create();

		switch (browserName.toLowerCase()) {
			case "chromium":
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
				break;
			case "firefox":
				browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
				break;
			case "safari":
				browser =  playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
				break;
			case "chrome":
				browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(headless));
				break;
			case "edge":
				browser = playwright.chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(headless));
				break;

			default:
				System.out.println("please pass the right browser name......");
				break;
		}
        browserContext = browser.newContext();


		return browserContext;

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 *
	 */
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
//		byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
//		String base64Path = Base64.getEncoder().encodeToString(buffer);
		return path;
	}


}
