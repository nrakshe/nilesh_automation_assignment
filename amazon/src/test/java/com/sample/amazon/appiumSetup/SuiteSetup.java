/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.appiumSetup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.utils.IOUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

/**
 * Setup required before running the suite appium setup.
 * 
 *
 */



public class SuiteSetup {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuiteSetup.class);
	public static AppiumDriver<MobileElement> driver = null;
	public static final Properties prop = new Properties();
	public static final Properties selectorProp = new Properties();
	public static Properties vmwareEnvProps = new Properties();
	private static final Properties capabilityProp = new Properties();
	public static String targetMobileOS;
	private static String appiumIP;
	public static String suiteName;
	private static String appPath;
	public static String osVersion;

	/**
	 * Creating a session with the capabilities.
	 * 
	 * @throws MalformedURLException
	 *
	 * @throws InterruptedException
	 * 
	 */
	
	@BeforeTest
	
	public static void setUp() throws MalformedURLException, InterruptedException {

		InputStream inputLocators = null;
		InputStream inputCapability = null;
		try {
			inputLocators = new FileInputStream("src/test/resources/" + "Android" + ".properties");
			selectorProp.load(inputLocators);
			LOGGER.info("Locator file loaded : src/test/resources/{}.properties", targetMobileOS);
			inputCapability = new FileInputStream(
					"src/test/resources/Appium" + "Android" + "Capabilities" + ".properties");
			capabilityProp.load(inputCapability);
			LOGGER.info("Appium Capabilities File loaded : src/test/resources/Appium{}Capabilities.properties",
					targetMobileOS);
		} catch (IOException ex) {
			LOGGER.error("Error while loading the property file ", ex);
		} finally {
			IOUtils.closeQuietly(inputLocators);
		}
		
		LOGGER.info("capability file loaded");
		DesiredCapabilities capabilities = new DesiredCapabilities();

			LOGGER.info("Setting driver for android");
			capabilities.setCapability("appium-version", capabilityProp.getProperty("appium_version"));
			capabilities.setCapability("platformVersion", capabilityProp.getProperty("platformVersion"));
			capabilities.setCapability("platformName", capabilityProp.getProperty("platformName"));
			capabilities.setCapability("deviceName", capabilityProp.getProperty("deviceName"));
			capabilities.setCapability("name", capabilityProp.getProperty("app_name"));
			capabilities.setCapability("fullReset", capabilityProp.getProperty("fullReset"));
			capabilities.setCapability("noReset", capabilityProp.getProperty("noReset"));
			capabilities.setCapability("appPackage", capabilityProp.getProperty("appPackage"));
			capabilities.setCapability("appActivity", capabilityProp.getProperty("appActivity"));
			capabilities.setCapability("appActivity", capabilityProp.getProperty("appActivity"));
			capabilities.setCapability("app", capabilityProp.getProperty("apkPath"));
			LOGGER.info("Android driver loaded");

		
		
		driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	public AppiumDriver getDriver() {
		return driver;
	}

}
