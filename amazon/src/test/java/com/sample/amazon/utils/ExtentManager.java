/*
 * Copyright (c) 2018 Nilesh, All rights reserved.
 *
 */

package com.sample.amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Extent report configurations.
 * 
 * @author mindstix
 *
 */
public class ExtentManager {
  private static ExtentReports extent;
  private static ExtentTest test;
  private static ExtentHtmlReporter htmlReporter;
  private static String filePath = "./extentreport.html";

  /**
   * Returns the object of extent report.
   * 
   * @return
   */
  public static synchronized ExtentReports getExtent() {
    if (extent == null) {
      extent = new ExtentReports();
      extent.attachReporter(getHtmlReporter());
      return extent;
    } else {
      return extent;
    }
  }

  private static ExtentHtmlReporter getHtmlReporter() {
    htmlReporter = new ExtentHtmlReporter(filePath);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setDocumentTitle("VMware vApprove API Automation Report");
    htmlReporter.config().setReportName(System.getProperty("env.suiteName") + " cycle");
    return htmlReporter;
  }

  public static ExtentTest createTest(String name, String description) {
    test = extent.createTest(name, description);
    return test;
  }
}