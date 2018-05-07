package com.ibm.automationpractices.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtil {
 
	public static String getPropertyValue(String filename, String key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\Testdata\\" + filename + ".properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}

	public static String getConConstantDate() {
		SimpleDateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT);
		Date d = new Date();
		return df.format(d);

	}
	
	public static void getScreenShot(WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("\\Screenshots\\"+getConConstantDate()+".png");
		FileUtils.copyFile(src, dest);
	}

}
