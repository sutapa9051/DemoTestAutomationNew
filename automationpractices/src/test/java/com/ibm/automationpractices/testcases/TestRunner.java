package com.ibm.automationpractices.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ibm.automationpractices.utilities.ExcellReading;
import com.ibm.automationpractices.pageobjects.Home_Page;
import com.ibm.automationpractices.utilities.BrowserFactory;
import com.ibm.automationpractices.utilities.CommonUtil;
import com.ibm.automationpractices.utilities.FailRetry;


public class TestRunner {
	String br_name;
	String r_mode;
	WebDriver driver = null;
	String url = null;
	Home_Page hp = null;
	// RegisterPage rp = null;

	@Parameters({ "bn", "rm" })
	@BeforeClass
	public void data(@Optional("chrome") String b, @Optional("local") String r) throws IOException {
		this.br_name = b;
		this.r_mode = r;
		this.url = CommonUtil.getPropertyValue("config", "url");
	}

	@BeforeMethod
	public void precondition() throws IOException {
		/*
		 * System.out.println(this.br_name); System.out.println(this.r_mode);
		 * System.out.println(this.url);
		 */

		this.driver = BrowserFactory.getBrowser(this.br_name, this.r_mode);
		System.out.println(driver);
		BrowserFactory.openUrl(this.url);
		this.hp = PageFactory.initElements(this.driver, Home_Page.class);

	}

	@Test
	public void verifyAddToCart() throws IOException {

		String AddToCartTitle = this.hp.selectItemAddToCart();
		String expectedTitle = CommonUtil.getPropertyValue("homepage", "addToCartTitle");
		Assert.assertEquals(AddToCartTitle, expectedTitle, "pass");

	}

	/*
	 * @Test(priority = 2, dataProvider = "xyz", dataProviderClass =
	 * ExcellReading.class, retryAnalyzer = FailRetry.class) public void
	 * verifyRegisterProcess(String... cd) throws IOException { RegisterPage rp =
	 * this.hp.clickRegisterLink(); String fn = cd[0]; rp.enterFirstName(fn); String
	 * cn = cd[7]; this.rp = PageFactory.initElements(this.driver,
	 * RegisterPage.class); rp.selectCountry(cn);
	 * 
	 * }
	 */
}
