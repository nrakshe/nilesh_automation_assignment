package com.sample.amazon.test;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;

/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.sample.amazon.appiumSetup.SuiteSetup;
import com.sample.amazon.appiumSetup.TearDown;
import com.sample.amazon.screens.BaseScreen;
import com.sample.amazon.screens.Constants;
import com.sample.amazon.screens.LoginScreen;
import com.sample.amazon.screens.MenuItems;
import com.sample.amazon.utils.ExtentManager;
import com.sample.amazon.utils.ExtentTestCase;
import com.sample.amazon.utils.ScreenshotUtility;



public class TestCases extends BaseScreen {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCases.class);
	private LoginScreen login = new LoginScreen();
	private MenuItems menuItems = new MenuItems();
	private Constants constant=new Constants();
	private ScreenshotUtility screenshotUtilObj = new ScreenshotUtility();
	
	/**
	 * To verify successful login.
	 * @throws InterruptedException 
	 * @throws MalformedURLException 
	 */
	
	@BeforeSuite
	private void setUp() throws MalformedURLException, InterruptedException {
	   ExtentTestCase.setExtent(ExtentManager.getExtent());
		LOGGER.info("started test executed");
		SuiteSetup.setUp();
	}

	@Test(priority = 1, enabled = true)
	private void successfulLoginTest() {
		LOGGER.info("Executing 1st test case.");
		ExtentTestCase.setTest(ExtentTestCase.getExtent().createTest("Login to the app using mobile/email and password"));
		login.verifyLogin(Constants.USER_NAME, Constants.USER_PASSWORD);

	}

	@Test(priority = 2, enabled = true)
	private void menuItems() throws AWTException {
		LOGGER.info("Executing 2nd test case.");
		ExtentTestCase.setTest(ExtentTestCase.getExtent().createTest("Fetch all Menu items"));
		menuItems.menuItems();

	}

	
	@Test(priority = 3, enabled = true)
	private void searchItems() {
		LOGGER.info("Executing 3rd test case.");
		ExtentTestCase.setTest(ExtentTestCase.getExtent().createTest("Sponsored by Samsung items"));
		menuItems.searchList();
	}

	@Test(priority = 4, enabled = true)
	private void searchCountAfterAndBeforeFilter() {
		LOGGER.info("Executing 4th test case.");
		ExtentTestCase.setTest(ExtentTestCase.getExtent().createTest("Results beofre Prime/Non-Prime/Custom filter counts"));
		menuItems.searchCount();
	}

	
	@Test(priority = 5, enabled = true)
	private void addProductInCart() {
		LOGGER.info("Executing 5th test case.");
		ExtentTestCase.setTest(ExtentTestCase.getExtent().createTest("Cart Verification"));
		menuItems.addToCart();
	}
	
	/**
	   * Executes after every test.
	   */
	  @AfterMethod()
	  public void getResult(ITestResult result) {
	    LOGGER.info("In after method.");
	    String screenShotDestination = null;
	    if (result.getStatus() == ITestResult.FAILURE) {
	      LOGGER.info("Test Failed.");
	      ExtentTestCase.getTest().fail(Status.FAIL + ", Test Case Failed is " + result.getName());
	      ExtentTestCase.getTest().fail(Status.FAIL + ", Test Case Failed is " + result.getThrowable());
	      screenShotDestination = captureScreenShot();
	      screenshotUtilObj.onTestFailure(result);
	      try {
	        ExtentTestCase.getTest().log(Status.FAIL,
	            "Test Case Failed. Please find screenshot below."
	                + " Right click and download the image to open." + "&lt;br&gt;&lt;br&gt;"
	                + ExtentTestCase.getTest().addScreenCaptureFromPath(screenShotDestination));
	      } catch (IOException e) {
	        LOGGER.error("Error while performing actions on file.", e);
	      }
	      LOGGER.info("Screenshot Displayed in Extent Report");
	    } else if (result.getStatus() == ITestResult.SKIP) {
	      LOGGER.info("Test skipped.");
	      ExtentTestCase.getTest().skip(Status.SKIP + ", '" + result.getName() + "' Test Case Skipped");
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	      LOGGER.info("Test Passed.");
	      ExtentTestCase.getTest().pass(Status.PASS + ", '" + result.getName() + "' Test Case Passed.");
	    }
	    ExtentTestCase.getExtent().flush();
	  }


	@AfterSuite()
	private void tear() {
		LOGGER.info("After test executed");
		TearDown.teardown();
	}

}
