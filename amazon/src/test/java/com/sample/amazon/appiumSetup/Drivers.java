/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.appiumSetup;

import io.appium.java_client.AppiumDriver;

public class Drivers extends SuiteSetup {

	protected AppiumDriver driver;

	public Drivers() {
		this.driver = super.getDriver();
	}
}
