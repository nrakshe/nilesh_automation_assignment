/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */

package com.sample.amazon.appiumSetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Tear down all and closed all the driver, simulator or emulator.
 *
 *
 */
public class TearDown {

  private static final Logger LOGGER = LoggerFactory.getLogger(TearDown.class);

  /**
   * Close the the driver after Execution.
   */
  public static void teardown() {
    SuiteSetup.driver.quit();
    LOGGER.info("app is closed");
  }

}