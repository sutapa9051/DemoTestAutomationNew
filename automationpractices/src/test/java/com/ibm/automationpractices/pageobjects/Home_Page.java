package com.ibm.automationpractices.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page {

	private WebDriver driver=null;
	public Home_Page(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="//*[@id='homefeatured']/li[6]/div/div[2]/div[2]/a[1]")
	private WebElement cartElement;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[1]/h2")
	private WebElement addedToCart;
	
	public String selectItemAddToCart()
	{
		cartElement.click();
		String addToCartText = addedToCart.getText();
		System.out.println(addedToCart.getText());
		System.out.println(addToCartText);
		return addToCartText;
	}
	
}
