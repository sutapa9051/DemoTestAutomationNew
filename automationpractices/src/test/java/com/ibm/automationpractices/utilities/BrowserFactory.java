package com.ibm.automationpractices.utilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BrowserFactory {

	private static WebDriver driver = null;

    

	private static void getLocalBrowser(String bn) {
		if (bn.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "..\\BrowserExes\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (bn.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "..\\BrowserExes\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (bn.equalsIgnoreCase("internet explorer")) {
			System.setProperty("webdriver.ie.driver", "..\\BrowserExes\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

	}

 private static void getRemoteBrowser(String bn) throws IOException {
		if (bn.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "..\\BrowserExes\\geckodriver.exe");
			FirefoxOptions fop = new FirefoxOptions();
			String node2 = CommonUtil.getPropertyValue("config", "clientMachin2");
			driver = new RemoteWebDriver(new URL(node2), fop);

		} else if (bn.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "..\\BrowserExes\\chromedriver.exe");
			ChromeOptions cop = new ChromeOptions();
			// cop.addArguments(Platform.WINDOWS);
			cop.addArguments("--start-maximized");

			String node1 = CommonUtil.getPropertyValue("config", "clientMachine1");
			driver = new RemoteWebDriver(new URL(node1), cop);

		} else if (bn.equalsIgnoreCase("internet explorer")) {
			System.setProperty("webdriver.ie.driver", "..\\BrowserExes\\IEDriverServer.exe");
			// DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			// dc.setBrowserName("internet explorer");
			// dc.setPlatform(Platform.WINDOWS);
			// driver = new InternetExplorerDriver(dc);

		}

	} 

   public static WebDriver getBrowser(String bn, String rm) throws IOException {
		if (rm.equalsIgnoreCase("remote")) {
			getRemoteBrowser(bn);
		} else if (rm.equalsIgnoreCase("local")) {
			getLocalBrowser(bn);
		}
		return driver;
	}

	public static WebDriver getBrowser() {
		return driver;

	}

	public static void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
	}

}
