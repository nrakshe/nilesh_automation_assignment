/*
 * Copyright (c) 2018, Inc. All rights reserved.
 *
 */

package com.sample.amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * Contains objects of extent report.
 * 
 * @author mindstix
 *
 */
public class ExtentTestCase {
  static ExtentTest TEST;
  static ExtentReports EXTENT;

  public static ExtentTest getTest() {
    return TEST;
  }

  public static void setTest(ExtentTest test) {
    TEST = test;
  }

  public static ExtentReports getExtent() {
    return EXTENT;
  }

  public static void setExtent(ExtentReports extent) {
    EXTENT = extent;
  }

}
