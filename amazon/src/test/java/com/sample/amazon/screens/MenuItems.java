/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sample.amazon.utils.ExtentTestCase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MenuItems extends BaseScreen {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);

	private List<MobileElement> menulist = null;
	private List<MobileElement> searchlist = null;
	private List<String> hash_Set = new ArrayList<String>();
	public static AppiumDriver<MobileElement> driver = null;
	public Map<String, String> filterNumber = new HashMap<>();

	public void menuItems() {
		
		waitUntilVisiblityWithTime("closeLanguage_xpath");
		getElement("closeLanguage_xpath").click();
		//getElement("search_id").click();
		waitUntilVisiblityWithTime("menuList_xpath");
		menulist = getElements("menuList_xpath");
		for (MobileElement ele : menulist) {
			LOGGER.info("Menu list Elements : {} ", ele.getText());
			
			ExtentTestCase.getTest().log(Status.INFO,
			          "Menu List Pinted : " + ele.getText());
		}
	}

	public void searchList() {
		getElement("search_id").click();
		getElement("search_id").sendKeys(Constants.Search_item + "\n");
		waitUntilVisiblityWithTime("samsungList_xpath");
		searchlist = getElements("searchlist_xpath");
		for (MobileElement ele : searchlist) {
			String product = ele.getText();
			LOGGER.info("Search elements list : {} ", ele.getText());
			if (product.contains("Tab") && product.contains("Galaxy")) {
				LOGGER.info(" Product Name : {}", product);
				hash_Set.add(product);
			}
		}
		LOGGER.info(" SET : {} ", hash_Set);
		ExtentTestCase.getTest().log(Status.INFO,
		          "Menu List Pinted : " + hash_Set);
	}

	public void searchCount() {
		String resultcount = null;
		// getElement("search_id").click();
		// getElement("search_id").sendKeys(Constants.Search_item+"\n");
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("BEFORE FILTER : {} ", resultcount);
		filterNumber.put("Before Filter", resultcount);
		ExtentTestCase.getTest().log(Status.INFO,
		          "Before Filter: " + resultcount);
		getElement("prime_toggle_button_xpath").click();
		LOGGER.info("Clicked on Prime filter");
		waitUntilVisiblityWithTime("results_count_xpath");
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("AFTER PRIME FILTER : {} ", resultcount);
		filterNumber.put("After prime filter", resultcount);
		ExtentTestCase.getTest().log(Status.INFO,
		          "After Prime Filter : " + resultcount);
		clickOnHighToLow();
		waitUntilVisiblityWithTime("results_count_xpath");
		clickOnDeviceFeatures("gps_xpath", "GPS");
		LOGGER.info("GPS selected");
		waitUntilVisiblityWithTime("results_count_xpath");

		clickOnDeviceFeatures("music_player_xpath", "Music Player");
		LOGGER.info("Music selected");
		waitUntilVisiblityWithTime("results_count_xpath");

		clickOnDeviceFeatures("dual_sim_xpath", "Dual SIM");
		LOGGER.info("DualSim selected");
		waitUntilVisiblityWithTime("results_count_xpath");
		clickOnDeviceFeatures("hotspot_xpath", "Hotspot");
		LOGGER.info("Hotspot selected");
		waitUntilVisiblityWithTime("results_count_xpath");
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("After prime and custom filter : {} ", resultcount);
		filterNumber.put("After prime and custom filter : {}", resultcount);
		ExtentTestCase.getTest().log(Status.INFO,
		          "After prime and custom filter : " + resultcount);
		
		filterNumber.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

	}

	private void clickOnHighToLow() {
		getElement("filter_id").click();
		waitUntilVisiblityWithTime("sort_by_xpath");
		getElement("sort_by_xpath").click();
		waitUntilVisiblityWithTime("low_to_high_xpath");
		getElement("low_to_high_xpath").click();
	}

	private void clickOnDeviceFeatures(String features_xpath, String features) {
		waitUntilVisiblityWithTime("filter_id");
		getElement("filter_id").click();
		// swipeUp();
		waitUntilVisiblityWithTime("device_features_xpath");
		getElement("device_features_xpath").click();
		clickElement(features_xpath, features_xpath);
	}

	public void addToCart() {
		String cartCount = null;
		waitUntilVisiblityWithTime("search_id");
		getElement("search_id").click();
		LOGGER.info("Clicked on Search");
		getElement("search_id").sendKeys(Constants.product1 + "\n");
		getElement("productclick_xpath").click();
		waitUntilVisiblityWithTime("addTocart_xpath");
		getElement("addTocart_xpath").click();
		LOGGER.info("Added item in the cart");
		ExtentTestCase.getTest().log(Status.INFO,
		        "Item added in the cart");
		waitUntilVisiblityWithTime("getCountFromCard_id");

		cartCount = getElementText("getCountFromCard_id");
		ExtentTestCase.getTest().log(Status.INFO,
		          "After prime and custom filter : " + cartCount);
		
		Assert.assertEquals("1", cartCount);
		LOGGER.info("Verified Value");

	}

}
