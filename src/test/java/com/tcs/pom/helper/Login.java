package com.tcs.pom.helper;

import java.util.LinkedHashMap;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;

import com.tcs.pom.pages.LoginPage;
import com.tcs.saf.base.BaseTest;
import com.tcs.saf.base.BasePage;
import org.openqa.selenium.WebDriver;

public class Login extends BaseTest implements LoginPage {

    public static ExtentReports report;
    public static ExtentTest test;
    static boolean status_Step = false;
    static By locator;
    //static String verifyText;
    //static int counter = 0;

public boolean Login(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

    try {

        /*Selecting LoginAs - Entity on Login Page*/
    	test.log(LogStatus.INFO, "Selecting LoginAs-Entity");
        locator = BasePage.getLocator(SelectEntity, BasePage.BY_TYPE.BY_ID, getDriver());
        String SelEntity = dataSet.get("SelectEntity");
        BasePage.selectDropDownByVisibleText(locator, SelEntity, getDriver());

        /*Entering Entity name on Login Page*/
        test.log(LogStatus.INFO, "Entering Entity Name");
        locator = BasePage.getLocator(EntityName, BasePage.BY_TYPE.BY_NAME, getDriver());
        String EntityName = dataSet.get("EntityName");
        BasePage.type(locator, EntityName, getDriver());
        
        /*Entering User ID on Login Page*/
        test.log(LogStatus.INFO, "Entering UserID");
        locator = BasePage.getLocator(UserID, BasePage.BY_TYPE.BY_NAME, getDriver());
        String UserID = dataSet.get("UserID");
        BasePage.type(locator, UserID, getDriver());
        
        /*Entering Password on Login Page*/
        test.log(LogStatus.INFO, "Entering Password");
        locator = BasePage.getLocator(Password, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Password = dataSet.get("Password");
        BasePage.type(locator, Password, getDriver());
        
        /*Clicking on Login Button*/
        test.log(LogStatus.INFO, "Clicking on Login Button");
        locator = BasePage.getLocator(LoginBtn, BasePage.BY_TYPE.BY_ID, getDriver());        
        BasePage.click(locator, getDriver());
        Thread.sleep(5000);
        BaseTest.reportPass(driver, test, "Verify Logging Successful", "Login Successful");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return status_Step;
    }


}

