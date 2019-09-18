/*
 * Copyright (c) 2018 VMware, Inc. All rights reserved.
 *
 */

package com.sample.amazon.screens;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.sample.amazon.appiumSetup.SuiteSetup;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
public class BaseScreen {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseScreen.class);
	public boolean isTabOpen = false;
	// private JsonParser parse = new JsonParser();

	/**
	 * Returning element.
	 * 
	 * @param xpath locator name from properties.
	 * @return element found by the locator.
	 */
	protected MobileElement getElement(String xpath) {
		WebDriverWait wait = new WebDriverWait(SuiteSetup.driver, 45);
		MobileElement element;
		if (xpath.contains("xpath")) {
			LOGGER.info("Waiting for xpath {}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))));
			element = SuiteSetup.driver.findElement(By.xpath(SuiteSetup.selectorProp.getProperty(xpath)));
			Assert.assertNotNull(element);
		} else if (xpath.contains("acc_id")) {
			LOGGER.info("Waiting for Acc Id :{} ", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				LOGGER.error("Error while waiting for Acc Id : {} ", xpath, e);
			}
			element = SuiteSetup.driver
					.findElement(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath)));
			Assert.assertNotNull(element);
		} else {
			{
				LOGGER.info("Waiting for Id :{} ", SuiteSetup.selectorProp.getProperty(xpath));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					LOGGER.error("Error while waiting for ID.", e);
				}
				element = SuiteSetup.driver.findElement(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath)));
				Assert.assertNotNull(element);
			}
		}
		return element;
	}

	/**
	 * Returns list of mobile elements.
	 * 
	 * @param xpath locator name from properties.
	 * @return list of mobile elements found by the locator.
	 */
	protected List<MobileElement> getElements(String xpath) {
		WebDriverWait wait = new WebDriverWait(SuiteSetup.driver, 30);
		List<MobileElement> elements;

		if (xpath.contains("xpath")) {
			LOGGER.info("Waiting for xpath :{}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))));
			elements = SuiteSetup.driver.findElements(By.xpath(SuiteSetup.selectorProp.getProperty(xpath)));
			Assert.assertNotNull(elements);
		} else if (xpath.contains("acc_id")) {
			LOGGER.info("Waiting for Acc Id :{}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))));
			elements = SuiteSetup.driver
					.findElements(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath)));
			Assert.assertNotNull(elements);
		} else {
			LOGGER.info("Waiting for Id :{}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))));
			elements = SuiteSetup.driver.findElements(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath)));
			Assert.assertNotNull(elements);
		}
		return elements;
	}

	/**
	 * Wait for visibility of the element.
	 * 
	 * @param xpath
	 * 
	 */
	protected void waitUntilVisible(String xpath) {
		if (xpath.contains("xpath")) {
			while (SuiteSetup.driver.findElements(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))).isEmpty()) {
			}
		} else if (xpath.contains("acc_id")) {
			while (SuiteSetup.driver.findElements(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath)))
					.isEmpty()) {
			}
		} else {
			while (SuiteSetup.driver.findElements(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))).isEmpty()) {
			}
		}
	}

	/**
	 * Wait for a elements visibility for specified time.
	 * 
	 * @param xpath locator name from properties.
	 */
	protected void waitUntilVisiblityWithTime(String xpath) {
		WebDriverWait wait = new WebDriverWait(SuiteSetup.driver, 100);
		if (xpath.contains("xpath")) {
			LOGGER.info("Waiting for xpath : {} ", xpath);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))));
		} else if (xpath.contains("acc_id")) {
			LOGGER.info("Waiting for acc_id : {} ", xpath);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))));
		} else {
			LOGGER.info("Waiting for id : {} ", xpath);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))));
		}
	}

	/**
	 * Returns the text of the element.
	 */
	protected String getElementText(String xpath) {
		WebDriverWait wait = new WebDriverWait(SuiteSetup.driver, 45);
		String actualText;
		if (xpath.contains("xpath")) {
			LOGGER.info("Waiting for xpath ", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))));
			actualText = SuiteSetup.driver.findElement(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))).getText();
		} else if (xpath.contains("acc_id")) {
			LOGGER.info("Waiting for Acc Id {}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))));
			actualText = SuiteSetup.driver
					.findElement(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))).getText();
		} else {
			LOGGER.info("Waiting for Id {}", SuiteSetup.selectorProp.getProperty(xpath));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))));
			actualText = SuiteSetup.driver.findElement(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath)))
					.getText();
		}
		return actualText;
	}

	/**
	 * Decodes the passed string using Base64.
	 * 
	 * @param encodedString - the string which is to be decoded.
	 * @return - decoded string.
	 */
	protected String decode(String encodedString) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(encodedString));
	}

	/**
	 * Hides the keyboard on the device.
	 */
	protected void hideKeyboard() {
		SuiteSetup.driver.hideKeyboard();
	}

	/**
	 * Implicit wait
	 */
	public void implicitWait() {
		SuiteSetup.driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
	}

	/**
	 * Clicks on element with matching type of locator.
	 * 
	 * @param xpath       - String indicating locator name provided in the
	 *                    properties.
	 * @param elementName - String to indicate the element being clicked.
	 */
	protected void clickElement(String xpath, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(SuiteSetup.driver, 60);
			if (xpath.contains("xpath")) {
				LOGGER.info("Clicking on element with xpath : {} ", SuiteSetup.selectorProp.getProperty(xpath));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))));
				SuiteSetup.driver.findElement(By.xpath(SuiteSetup.selectorProp.getProperty(xpath))).click();
			} else if (xpath.contains("acc_id")) {
				LOGGER.info("Clicking on element with Acc Id : {} ", SuiteSetup.selectorProp.getProperty(xpath));
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath))));
				SuiteSetup.driver.findElement(MobileBy.AccessibilityId(SuiteSetup.selectorProp.getProperty(xpath)))
						.click();
			} else {
				LOGGER.info("Clicking on element with Id : {} ", SuiteSetup.selectorProp.getProperty(xpath));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))));
				SuiteSetup.driver.findElement(MobileBy.id(SuiteSetup.selectorProp.getProperty(xpath))).click();
			}
			LOGGER.info("Clicked on {}", elementName);
			// ExtentTestCase.getTest().info("Clicked on " + elementName);
		} catch (NoSuchElementException e) {
			LOGGER.info("Error Occured while clicking element:{} ", elementName);
			LOGGER.error("Operation failed!", e);
			throw new NoSuchElementException("Operation Failed while clicking " + elementName + " element");
		} catch (TimeoutException e1) {
			LOGGER.info("Error Occured while clicking element:{} ", elementName);
			LOGGER.error("Operation failed!", e1);
			throw new TimeoutException("Operation Failed while clicking " + elementName + " element");
		} catch (Exception e) {
			LOGGER.info("Error Occured while clicking element:{} ", elementName);
			LOGGER.error("Operation failed!", e);
			throw new RuntimeException("Operation Failed while clicking " + elementName + " element");
		}
	}

	/**
	 * Method to swipe up.
	 */
	protected void swipeUp() {
		TouchAction actions = new TouchAction(SuiteSetup.driver);
		Dimension size = SuiteSetup.driver.manage().window().getSize();
		LOGGER.info("Current Screen width:{}", size.width);
		LOGGER.info("Current Screen height:{}", size.height);
		int startx = (int) (size.width / 2);
		int starty = (int) (size.height * 0.60);
		int endy = (int) (size.height * 0.40);
		System.out.println(size);
		System.out.println("Start swipe up operation");

		try {
			if (SuiteSetup.targetMobileOS.contains("Android")) {
				actions.press(PointOption.point(startx, starty)).waitAction().moveTo(PointOption.point(startx, endy))
						.release().perform();
			} else {
//				actions.press(startx, starty).waitAction(Duration.ofSeconds(5)).moveTo(-(startx - endx), 0).release()
//						.perform();
			}
			Thread.sleep(500);
		} catch (InterruptedException e) {
			LOGGER.error("Couldnot perform action. Error occured.", e);
		}
	}

}
