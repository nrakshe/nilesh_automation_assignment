/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */
package com.sample.amazon.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.sample.amazon.appiumSetup.TearDown;
import com.sample.amazon.screens.BaseScreen;
import com.sample.amazon.screens.Constants;
import com.sample.amazon.screens.LoginScreen;
import com.sample.amazon.screens.MenuItems;


public class TestCases extends BaseScreen {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCases.class);
	private LoginScreen login = new LoginScreen();
	private MenuItems menuItems = new MenuItems();
	private Constants constant=new Constants();

	/**
	 * To verify successful login.
	 */

	@Test(groups = { "smoke", "regression" }, priority = 1, enabled = false)
	private void successfulLoginTest() {
		LOGGER.info("Executing 1st test case.");
		login.verifyLogin(Constants.USER_NAME, Constants.USER_PASSWORD);

	}

	@Test(groups = { "smoke", "regression" }, priority = 2, enabled = false)
	private void menuItems() {
		LOGGER.info("Executing 2nd test case.");
		menuItems.menuItems();

	}

	@Test(groups = { "smoke", "regression" }, priority = 3, enabled = false)
	private void sponseredBySamsungProducts() {
		LOGGER.info("Executing 3rd test case.");
		menuItems.fetchSearchResult();

	}

	@Test(groups = { "smoke", "regression" }, priority = 4, enabled = false)
	private void searchItems() {
		LOGGER.info("Executing 4th test case.");
		menuItems.searchList();
	}

	@Test(groups = { "smoke", "regression" }, priority = 5, enabled = false)
	private void searchCountAfterAndBeforeFilter() {
		LOGGER.info("Executing 5th test case.");
		menuItems.searchCount();
	}

	@Test(groups = { "smoke", "regression" }, priority = 6, enabled = true)
	private void addProductInCart() {
		LOGGER.info("Executing 6th test case.");
		menuItems.addToCart();
	}

	@AfterSuite()
	private void tear() {
		System.out.println("tear down");
		LOGGER.info("After test executed");
		TearDown.teardown();
	}

}
