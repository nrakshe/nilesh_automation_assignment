/*
 * Copyright (c) 2018, All rights reserved.
 *
 */

package com.sample.amazon.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;



/**
 * Helper method to take the screenshots and test failure.
 * 
 * @author Mindstix Labs
 */
public final class ScreenshotUtility implements ITestListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotUtility.class);

  public static AppiumDriver<MobileElement> driver = null;
  // This method will execute before starting of Test suite.
  public void onStart(ITestContext tr) {
    LOGGER.info("In onStart method of ScreenUtility class");
  }

  // This method will execute, Once the Test suite is finished.
  public void onFinish(ITestContext tr) {
    LOGGER.info("In onFinish method of ScreenUtility class");
  }

  /**
   * This method will execute only when the test is pass.
   */
  public void onTestSuccess(ITestResult tr) {
    // captureScreenShot(tr, "pass");
    LOGGER.info("Test Passed. {}", tr.getMethod());
    // closeApp();
  }

  /**
   * This method will execute only on the event of fail test.
   */
  public void onTestFailure(ITestResult tr) {
    LOGGER.info("Test Failed. {} ", tr.getMethod());
    restartApp();
  }

  // This method will execute before the main test start (@Test)
  public void onTestStart(ITestResult tr) {

  }

  // This method will execute only if any of the main test(@Test) get skipped
  public void onTestSkipped(ITestResult tr) {
    LOGGER.info("In onTestSkipped method of ScreenUtility class");
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
    LOGGER.info("In onTestFailedButWithinSuccessPercentage method of ScreenUtility class");
  }

  /**
   * Method to capture screenshot.
   */
  public void captureScreenShot(ITestResult result, String status) {
    // AndroidDriver driver=ScreenshotOnPassFail.getDriver();
    String destDir = "";
    String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "."
        + result.getMethod().getMethodName();
    // To capture screenshot.
    
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
    // If status = fail then set folder name "screenshots/Failures"
    if (status.equalsIgnoreCase("fail")) {
      destDir = "screenshots/Failures";
    } else if (status.equalsIgnoreCase("pass")) {
      destDir = "screenshots/Success";
    }
    // To create folder to store screenshots
    new File(destDir).mkdirs();
    // Set file name with combination of test class name + date time.
    String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

    try {
      // Store file at destination folder location
      FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
    } catch (IOException e) {
      LOGGER.error("Input-Output exception occured.",e);
    }
  }

  public void closeApp() {
    LOGGER.info("Closing the App.");
    driver.close();
    
  }

  /**
   * Restarts app.
   */
  public void restartApp() {
    LOGGER.info("Restarting the App.");
    driver.closeApp();
    driver.launchApp();
  }}