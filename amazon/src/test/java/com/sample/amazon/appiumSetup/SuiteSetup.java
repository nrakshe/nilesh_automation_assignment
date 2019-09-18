/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.appiumSetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

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
	@BeforeSuite
	public static void setUp() throws MalformedURLException, InterruptedException {

		appiumIP = System.getProperty("env.INSTANCE_IP");
		LOGGER.info("Instance_IP : {}", appiumIP);
		targetMobileOS = System.getProperty("env.OS");
		LOGGER.info("Platform : {}", targetMobileOS);
		appPath = System.getProperty("env.APP_PATH");
		LOGGER.info("AppPath : {}", appPath);
		suiteName = System.getProperty("env.suiteName");
		LOGGER.info("Suite Name : {}", suiteName);
		osVersion = System.getProperty("env.OS_VERSION");
		LOGGER.info("Platform : {}", osVersion);
		InputStream inputLocators = null;
		InputStream inputCapability = null;
		try {
			inputLocators = new FileInputStream("src/test/resources/" + targetMobileOS + ".properties");
			selectorProp.load(inputLocators);
			LOGGER.info("Locator file loaded : src/test/resources/{}.properties", targetMobileOS);
			inputCapability = new FileInputStream(
					"src/test/resources/Appium" + targetMobileOS + "Capabilities" + ".properties");
			capabilityProp.load(inputCapability);
			LOGGER.info("Appium Capabilities File loaded : src/test/resources/Appium{}Capabilities.properties",
					targetMobileOS);
		} catch (IOException ex) {
			LOGGER.error("Error while loading the property file ", ex);
		} finally {
			IOUtils.closeQuietly(inputLocators);
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (targetMobileOS.contains("Android")) {
			LOGGER.info("Setting driver for android");
			capabilities.setCapability("appium-version", capabilityProp.getProperty("appium_version"));
			capabilities.setCapability("app", appPath);
			capabilities.setCapability("platformVersion", osVersion);
			capabilities.setCapability("platformName", targetMobileOS);
			capabilities.setCapability("deviceName", capabilityProp.getProperty("deviceName"));
			capabilities.setCapability("name", capabilityProp.getProperty("app_name"));
			capabilities.setCapability("fullReset", capabilityProp.getProperty("fullReset"));
			capabilities.setCapability("noReset", capabilityProp.getProperty("noReset"));
			capabilities.setCapability("appPackage", capabilityProp.getProperty("appPackage"));
			capabilities.setCapability("appActivity", capabilityProp.getProperty("appActivity"));
			LOGGER.info("Android driver loaded");

		}
		driver = new AppiumDriver<MobileElement>((new URL("http://" + appiumIP + ":4723/wd/hub")), capabilities);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	public AppiumDriver getDriver() {
		return driver;
	}

}
