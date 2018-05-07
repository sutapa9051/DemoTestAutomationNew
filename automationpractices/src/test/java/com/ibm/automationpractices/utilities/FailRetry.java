package com.ibm.automationpractices.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer {
	int maxCount=2;
	int min=0;
	public boolean retry(ITestResult a) {
		if(min<maxCount)
		{
			min++;
			return true;
		}
		return false;
	}
}
