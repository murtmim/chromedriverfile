package com.tcs.saf.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;

import com.relevantcodes.extentreports.ExtentTest;
import com.tcs.pom.helper.FlightBookingImpl;
import com.tcs.pom.helper.Login;
import com.tcs.saf.base.BasePage;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
//import com.tcs.pom.helper.HeaderNav;
import com.tcs.saf.base.BrowserSetUp;
import com.tcs.saf.exceptions.DataSheetException;
import com.tcs.saf.exceptions.InvalidBrowserException;
import com.tcs.saf.utilities.TestDataProvider;

public class FlightBooking extends FlightBookingImpl{

    static By locator;

    public static ExtentReports report;
    public static ExtentTest test;
    boolean exe_Status = false;
    

    public FlightBooking()
    {
        this.testDataProvider = new TestDataProvider();
        getGridProperties();
        getGlobalProperties();
    }

    public FlightBooking(String testName, String browser, LinkedHashMap<String, String> mapDataSheet)
    {
        this.testName = testName;
        this.testDataProvider = new TestDataProvider();
        this.testBrowser = browser;
        this.mapDataSheet = mapDataSheet;
    }

    @Factory(dataProvider = "dataSheet")
    public Object[] testCreator(LinkedHashMap<String, String> mapDataSheet) {
        return new Object[] { new FlightBooking(this.getClass().getSimpleName(),mapDataSheet.get("Browser"), mapDataSheet) };
    }

    @DataProvider(name="dataSheet")
    public  Object[][] getTestData() throws BiffException, IOException, InvalidBrowserException, DataSheetException{
        return testDataProvider.getTestDataFromExcel(externalSheetPath, this.getClass().getSimpleName());
    }

    @BeforeMethod
    public void beforemethod() throws MalformedURLException{
        webDriver = BrowserSetUp.setMyBrowser(this.testBrowser,testName,mapDataSheet,execution_Format);
        report = new ExtentReports(reportpath+ "FlightbookingTest.html",false);
        test = TestReportGenerator(report,this.getClass().getSimpleName(),"Test Description");
    }

    @Test
    public void FlightbookingTest() throws Exception{
        
			try {
				System.out.println("Start of First test case");
				Reporter.log("Execution of test case using : " + BrowserSetUp.getValue("TestCaseName", mapDataSheet, logger));
				
				
				//FlightBookinglaunchPageURLImpl fb= new FlightBookingImpl();
				BasePage.launchPageURL(mapDataSheet.get("URL"), getDriver());
				newtourslogin(mapDataSheet, getDriver(), test);
				Thread.sleep(1000);
				flightfinder(mapDataSheet, getDriver(), test);
				selectflight(mapDataSheet, getDriver(), test);
				bookflight(mapDataSheet, getDriver(), test);
				newtourslogout(mapDataSheet, getDriver(), test);
				System.out.println("End of first test case");
				test.log(LogStatus.PASS,"PASS");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				test.log(LogStatus.FAIL,"FAIL");
				e.printStackTrace();
			}
		
    }
    
    @AfterMethod
	public void afterMethod() throws IOException{
		//driver.close();
		//driver.quit();
		test.log(LogStatus.PASS,"Browser Closed Successfully");
		report.endTest(test);
		report.flush();
		report.close();
	}
}



