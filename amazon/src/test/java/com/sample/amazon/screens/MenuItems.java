/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.screens;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;

public class MenuItems extends BaseScreen {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);

	private List<MobileElement> menulist = null;
	private List<MobileElement> searchlist = null;
	private List<String> hash_Set = new ArrayList<String>();

	public void menuItems() {

		menulist = getElements("menuList_id");

		for (MobileElement ele : menulist) {
			LOGGER.info("Elements menu list : {} ", ele.getText());

		}
	}

	public void fetchSearchResult() {
		getElement("search_id").click();
		getElement("search_id").sendKeys(Constants.Search_item);
		getElement("lenovotab_id").click();
		LOGGER.info("Elements searched");
		searchlist = getElements("searchlist_xpath");
		for (MobileElement ele : searchlist) {
			LOGGER.info("Search elements list : {} ", ele.getText());

		}
	}

	public void searchList() {
		getElement("search_id").click();
		getElement("search_id").sendKeys(Constants.Search_item);
		getElement("lenovotab_id").click();
		searchlist = getElements("searchList_xpath");
		for (MobileElement ele : searchlist) {
			String product = ele.getText();
			LOGGER.info("Search elements list : {} ", ele.getText());
			if (!product.contains("Delivery by Amazon") && !product.contains("%")) {
				LOGGER.info(" Product Name : {}", product);
				swipeUp();
				hash_Set.add(product);
			}
		}
		LOGGER.info(" SET : {} ", hash_Set);
	}

	public void searchCount() {
		String resultcount = null;
		getElement("search_id").click();
		getElement("search_id").sendKeys(Constants.Search_item);
		getElement("lenovotab_id").click();
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("BEFORE FILTER : {} ", resultcount);
		getElement("prime_toggle_button_xpath").click();
		LOGGER.info("Clicked on Prime filter");
		waitUntilVisiblityWithTime("results_count_xpath");
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("AFTER PRIME FILTER : {} ", resultcount);
		clickOnHighToLow();
		clickOnDeviceFeatures("gps_xpath", "GPS");
		waitUntilVisiblityWithTime("results_count_xpath");
		resultcount = getElementText("results_count_xpath");
		LOGGER.info("AFTER PRIME AND CUSTOM FILTER : {} ", resultcount);

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
		swipeUp();
		waitUntilVisiblityWithTime("device_features_xpath");
		getElement("device_features_xpath");
		clickElement(features_xpath, features_xpath);
	}

	public void addToCart() {
		String cartCount=null;
		waitUntilVisiblityWithTime("search_id");
		getElement("search_id").click();
		LOGGER.info("Clicked on Search");
		getElement("search_id").sendKeys(Constants.product1);
		getElement("FirstProduct_id").click();
		getElement("productclick_xpath").click();
		waitUntilVisiblityWithTime("addTocart_xpath");
		getElement("addTocart_xpath").click();
		LOGGER.info("Added item in the cart");
		waitUntilVisiblityWithTime("getCountFromCard_id");
		 cartCount = getElementText("getCountFromCard_id");
		 Assert.assertEquals("1", cartCount);
		 LOGGER.info("Verified Value");


	}

}
