/*
 * Copyright (c) 2019 Nilesh, All rights reserved.
 *
 */

package com.sample.amazon.screens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginScreen extends BaseScreen{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);
	//private ExcelReader excel = new ExcelReader();
	

	
	public void verifyLogin(String username , String password) {
		waitUntilVisiblityWithTime("signin_id");
		getElement("signin_id").click();
    	LOGGER.info("clicked on Login Button");
    	waitUntilVisiblityWithTime("continue_id");
    	getElement("email_id").click();
    	LOGGER.info("'Clicked on Email/Mobile field ");
    	getElement("email_id").sendKeys(decode(username));
    	LOGGER.info("Entered username");
    	hideKeyboard();
    	getElement("continue_id").click();
    	LOGGER.info("Clicked on Continue button");
    	waitUntilVisiblityWithTime("LoginButton_id");
    	getElement("password_id").sendKeys(decode(password));
    	LOGGER.info("Entered Password");
    	hideKeyboard();	
    	getElement("LoginButton_id").click();
    	LOGGER.info("Successfully signing in.");
    	//sExtentTestCase.getTest().log(Status.INFO, "Successfully signing in.");
	}
	

}
