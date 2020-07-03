package com.tcs.pom.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tcs.pom.pages.FlightBookingPage;
import com.tcs.saf.base.BaseTest;
import com.tcs.saf.base.BasePage;

import org.openqa.selenium.WebDriver;

public class FlightBookingImpl extends BaseTest implements FlightBookingPage {

    public static ExtentReports report;
    public static ExtentTest test;
    static boolean status_Step = false;
    static By locator;
    //static String verifyText;
    //static int counter = 0;
    
    public boolean newtourslogin(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

        try {
        	
            /*Providing Username on Login Page*/
            test.log(LogStatus.INFO, "Entering User Name");
            //System.err.println("Inside Login error message");
            locator = BasePage.getLocator(Username, BasePage.BY_TYPE.BY_NAME, getDriver());
            String username = dataSet.get("Username");
            BasePage.type(locator, username, getDriver());

            /*Providing Password on Login Page*/
            test.log(LogStatus.INFO, "Entering Password");
            locator = BasePage.getLocator(Password, BasePage.BY_TYPE.BY_NAME, getDriver());
            String pass = dataSet.get("Password");
            BasePage.type(locator, pass, getDriver());
            
            /*Clicking on Login Button*/
            test.log(LogStatus.INFO, "Clicking on Login Button");
            locator = BasePage.getLocator(Login, BasePage.BY_TYPE.BY_NAME, getDriver());        
            BasePage.click(locator, getDriver());
            Thread.sleep(5000);
            BaseTest.reportPass(driver, test, "Verify Logging Successful", "Login Successful");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return status_Step;
        }


public boolean flightfinder(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

    try {
    	
        /*Selecting Triptype*/        
    	test.log(LogStatus.INFO, "Selecting Trip Type");                
    	locator = BasePage.getLocatorList(Triptype, BasePage.BY_TYPE.BY_NAME, getDriver());
    	List Listradio = driver.findElements(locator);
    	String Triptype = dataSet.get("Triptype");
    	int dataint = Integer.valueOf(Triptype);
        WebElement element = (WebElement) Listradio.get(dataint);        
        element.click();
        
        /*Selecting Passenger count*/
        test.log(LogStatus.INFO, "Selecting Passenger count");
        locator = BasePage.getLocator(Passengercount, BasePage.BY_TYPE.BY_NAME, getDriver());
        String passcount = dataSet.get("Passcount");
        BasePage.selectDropDownByValue(locator, passcount, getDriver());
                
        /*Selecting Departing from place*/
        test.log(LogStatus.INFO, "Selecting Departing from place");
        locator = BasePage.getLocator(DepartingFrom, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Departingfrom = dataSet.get("Departingfrom");
        BasePage.selectDropDownByVisibleText(locator, Departingfrom, getDriver());
        
        /*Selecting Departing from Month*/
        test.log(LogStatus.INFO, "Selecting Departure month");
        locator = BasePage.getLocator(DepartingMonth, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Departingmonth = dataSet.get("DepartingMonth");
        BasePage.selectDropDownByVisibleText(locator, Departingmonth, getDriver());
        
        /*Selecting Departure date*/
        test.log(LogStatus.INFO, "Selecting Departure Date");
        locator = BasePage.getLocator(DepartingDate, BasePage.BY_TYPE.BY_NAME, getDriver());
        String DepartureDate = dataSet.get("DepartingDate");
        BasePage.selectDropDownByValue(locator, DepartureDate, getDriver());
        
        /*Selecting Arriving in place*/
        test.log(LogStatus.INFO, "Selecting Arriving in place");
        locator = BasePage.getLocator(Arrivingin, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Arrivingin = dataSet.get("Arrivingin");
        BasePage.selectDropDownByVisibleText(locator, Arrivingin, getDriver());
        
        /*Selecting Arriving Month*/
        test.log(LogStatus.INFO, "Selecting Arriving month");
        locator = BasePage.getLocator(ArrivingMonth, BasePage.BY_TYPE.BY_NAME, getDriver());
        String ArrivingMonth = dataSet.get("ArrivingMonth");
        BasePage.selectDropDownByVisibleText(locator, ArrivingMonth, getDriver());
        
        /*Selecting Arriving date*/
        test.log(LogStatus.INFO, "Selecting Arriving Date");
        locator = BasePage.getLocator(ArrivingDate, BasePage.BY_TYPE.BY_NAME, getDriver());
        String ArrivingDate = dataSet.get("ArrivingDate");
        BasePage.selectDropDownByValue(locator, ArrivingDate, getDriver());

        /*Selecting Service Class*/        
    	test.log(LogStatus.INFO, "Selecting Service Class");                
    	locator = BasePage.getLocatorList(Serviceclass, BasePage.BY_TYPE.BY_NAME, getDriver());
    	List Listservice = driver.findElements(locator);
    	String Serviceclass = dataSet.get("ServiceClass");
    	int servint = Integer.valueOf(Serviceclass);
        WebElement serviceclass = (WebElement) Listservice.get(servint);        
        serviceclass.click();
        
        /*Selecting Airline Preference*/
        test.log(LogStatus.INFO, "Selecting Airline Preference");
        locator = BasePage.getLocator(AirlinePref, BasePage.BY_TYPE.BY_NAME, getDriver());
        String AirlinePref = dataSet.get("AirlinePref");
        BasePage.selectDropDownByVisibleText(locator, AirlinePref, getDriver());
        
        /*Clicking on Continue Button*/
        test.log(LogStatus.INFO, "Clicking on Continue Button");
        locator = BasePage.getLocator(findFlights, BasePage.BY_TYPE.BY_NAME, getDriver());        
        BasePage.click(locator, getDriver());
        Thread.sleep(5000);
        BaseTest.reportPass(driver, test, "Entering Details for finding flights", "Flight details entered correctly.");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return status_Step;
    }

public boolean selectflight(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

    try {
    	
        /*Selecting Departure Flight*/        
    	test.log(LogStatus.INFO, "Selecting Departure Flight");                
    	locator = BasePage.getLocatorList(DepartureFlight, BasePage.BY_TYPE.BY_NAME, getDriver());
    	List ListDeparture = driver.findElements(locator);
    	String DepartureFlight = dataSet.get("DepartureFlight");
    	int dataint = Integer.valueOf(DepartureFlight);
        WebElement element = (WebElement) ListDeparture.get(dataint);        
        element.click();
        
        /*Selecting Arrival Flight*/        
    	test.log(LogStatus.INFO, "Selecting Arrival Flight");                
    	locator = BasePage.getLocatorList(ArrivalFlight, BasePage.BY_TYPE.BY_NAME, getDriver());
    	List ListArrival = driver.findElements(locator);
    	String ArrivalFlight = dataSet.get("ArrivalFlight");
    	int intarrival = Integer.valueOf(ArrivalFlight);
        WebElement Arrival = (WebElement) ListArrival.get(intarrival);        
        Arrival.click();
                
        /*Clicking on Continue Button*/
        test.log(LogStatus.INFO, "Clicking on Continue Button");
        locator = BasePage.getLocator(reserveFlights, BasePage.BY_TYPE.BY_NAME, getDriver());        
        BasePage.click(locator, getDriver());
        Thread.sleep(5000);
        BaseTest.reportPass(driver, test, "Selecting flights", "Flight selected successfully.");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return status_Step;
    }

public boolean bookflight(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

    try {
    	
        /*Entering Passenger1 First name*/        
    	test.log(LogStatus.INFO, "Entering Passenger1 First name");                
    	locator = BasePage.getLocator(Pass1_Firstname, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass1_Firstname = dataSet.get("Pass1_Firstname");
        BasePage.type(locator, Pass1_Firstname, getDriver());
        
        /*Entering Passenger1 Last name*/        
    	test.log(LogStatus.INFO, "Entering Passenger1 Last name");                
    	locator = BasePage.getLocator(Pass1_Lastname, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass1_Lastname = dataSet.get("Pass1_Lastname");
        BasePage.type(locator, Pass1_Lastname, getDriver());
        
        /*Selecting Passenger1 Meal Preference*/
        test.log(LogStatus.INFO, "Selecting Passenger1 Meal Preference");
        locator = BasePage.getLocator(Pass1_MealPref, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass1_MealPref = dataSet.get("Pass1_MealPref");
        BasePage.selectDropDownByVisibleText(locator, Pass1_MealPref, getDriver());
        //BasePage.selectDropDownByValue(locator, Pass1_MealPref, getDriver());
        
        /*Entering Passenger2 First name*/        
    	test.log(LogStatus.INFO, "Entering Passenger2 First name");                
    	locator = BasePage.getLocator(Pass2_Firstname, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass2_Firstname = dataSet.get("Pass2_Firstname");
        BasePage.type(locator, Pass2_Firstname, getDriver());
        
        /*Entering Passenger2 Last name*/        
    	test.log(LogStatus.INFO, "Entering Passenger2 Last name");                
    	locator = BasePage.getLocator(Pass2_Lastname, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass2_Lastname = dataSet.get("Pass2_Lastname");
        BasePage.type(locator, Pass2_Lastname, getDriver());
        
        /*Selecting Passenger2 Meal Preference*/
        test.log(LogStatus.INFO, "Selecting Passenger2 Meal Preference");
        locator = BasePage.getLocator(Pass2_MealPref, BasePage.BY_TYPE.BY_NAME, getDriver());
        String Pass2_MealPref = dataSet.get("Pass2_MealPref");
        BasePage.selectDropDownByVisibleText(locator, Pass2_MealPref, getDriver());
        //BasePage.selectDropDownByValue(locator, Pass2_MealPref, getDriver());
        
        /*Selecting Credit Card Type*/
        test.log(LogStatus.INFO, "Selecting Credit Card Type");
        locator = BasePage.getLocator(CreditCardType, BasePage.BY_TYPE.BY_NAME, getDriver());
        String CreditCardType = dataSet.get("CreditCardType");
        BasePage.selectDropDownByVisibleText(locator, CreditCardType, getDriver());
        
        /*Entering Credit Card Number*/        
    	test.log(LogStatus.INFO, "Entering Credit Card Number");                
    	locator = BasePage.getLocator(CreditCardNumber, BasePage.BY_TYPE.BY_NAME, getDriver());
        String CreditCardNumber = dataSet.get("CreditCardNumber");
        BasePage.type(locator, CreditCardNumber, getDriver());
    	
        /*Clicking on Secure Purchase Button*/
        test.log(LogStatus.INFO, "Clicking on Secure Purchase Button");
        locator = BasePage.getLocator(buyFlights, BasePage.BY_TYPE.BY_NAME, getDriver());        
        BasePage.click(locator, getDriver());
        Thread.sleep(5000);
        BaseTest.reportPass(driver, test, "Booking flights", "Flight is booked successfully.");
        //Verifying and capturing Flight confirmation number
        locator = BasePage.getLocator(Confirmnumber, BasePage.BY_TYPE.BY_XPATH, getDriver());
        String Cnfmnumbertemp = BasePage.getText(locator, getDriver());
        String[] arrSplit = Cnfmnumbertemp.split("#");
        String Confirmnumber = arrSplit[1].trim();        
        System.out.println("Flight Confirmation number is " +Confirmnumber);
        BaseTest.reportPass(driver, test, "Booking flights - Confirmation number", "Flight Confirmation number is "+Confirmnumber);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return status_Step;
    }
public boolean newtourslogout(LinkedHashMap<String, String> dataSet, WebDriver driver,ExtentTest test) {

    try {

        /*Clicking on SIGN-OFF*/
        test.log(LogStatus.INFO, "Clicking on SIGN-OFF");
        //System.err.println("Inside Login error message");
        locator = BasePage.getLocator(SignOff, BasePage.BY_TYPE.BY_LINKTEXT, getDriver());
        BasePage.click(locator, getDriver());
        Thread.sleep(5000);
        BaseTest.reportPass(driver, test, "Verify Logout Successful", "User is logged off successfully");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return status_Step;
    }



/*public void loginButton(){

    locator = BasePage.getLocator(loginBtn, BasePage.BY_TYPE.BY_XPATH,getDriver());
    BasePage.click(locator,getDriver());
    }*/
}

