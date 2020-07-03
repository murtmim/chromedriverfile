package com.tcs.saf.base;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tcs.saf.utilities.DatabaseConnection;
import com.tcs.saf.utilities.Log;
import com.tcs.saf.utilities.TestDataProvider;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;

/**
 * @author Automation CoE TCS
 * 
 */
public abstract class BaseTest {
	public static String version, devicename, app_pack, app_act, reportscreenshotpath, m_browser,
			screen_path, databaseName, databasePassword, databaseServerIP, databaseType, databaseUserName,
			testDataSource, externalSheetPath, SikuliImagepath, webdriverServerHostName, webdriverServerPortName,
			webdriverUrl,execution_Format,testfeature,webdriverServerHostNameAppium,webdriverServerPortNameAppium;
	public static DatabaseConnection databaseConnection;
	public static int timeOut;
	public Properties prop;
	public FileInputStream input;
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public TestDataProvider testDataProvider;	
	public LinkedHashMap<String, String> mapDataSheet = new LinkedHashMap<String, String>();
	protected static LinkedHashMap<String, String> testDataSheet;
	public String testName,testBrowser;
	public static String reportpath;
	public static WebDriver getDriver() {
        return webDriver.get();
    }	 
    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }	
	public static ResourceBundle globalProperties,gridProperties;
	public static final Logger logger = Log.getInstance(Thread.currentThread().getStackTrace()[1].getClassName());
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	public static WebDriver driver;

	public int rowCount;
	/**
	 * 
	 * @return rowcount as int
	 */
	public int getRowCount() {
		return rowCount;
	}
	/**
	 * 
	 * @param rowCount
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * 
	 */
	public static void getGlobalProperties() {
		globalProperties = ResourceBundle.getBundle("global");
		reportpath = new File("").getAbsolutePath().toString().trim()+"\\ExtentReport\\";
		//System.out.println("Report path is " + reportpath);
		//externalSheetPath = globalProperties.getString("external_sheet_path");
		execution_Format = globalProperties.getString("execution_Format");		
		externalSheetPath = new File("").getAbsolutePath().toString().trim()+"/src/test/resources/";
		timeOut = Integer.parseInt(globalProperties.getString("time_out"));
		if (globalProperties.containsKey("test_data_source")) {
			testDataSource = globalProperties.getString("test_data_source");
		}
		if (testDataSource.equalsIgnoreCase("excel")) {
			externalSheetPath = new File("").getAbsolutePath().toString().trim()+"/src/test/resources/";
			//version = globalProperties.getString("android_version");
			//devicename = globalProperties.getString("device_name");
			//app_pack = globalProperties.getString("app_package");
			//app_act = globalProperties.getString("app_activity");
			m_browser = globalProperties.getString("mBrowser_name");
			if (externalSheetPath.equals("")) {
				logger.error("Please provide a valid sheet path");
				Assert.fail();
			}
		} else if (testDataSource.equalsIgnoreCase("database")) {

			databaseName = globalProperties.getString("database_name");
			databaseType = globalProperties.getString("database_type");
			databaseUserName = globalProperties.getString("database_username");
			databasePassword = globalProperties.getString("database_password");
			databaseServerIP = globalProperties.getString("database_ip");
		} else {
			logger.error("Please provide a valid test data source value");
			Assert.fail();
		}
	}

	/**
	 * This function reads Grid properties
	 */
	public static void getGridProperties() {
		gridProperties = ResourceBundle.getBundle("grid");
		webdriverServerHostName = gridProperties.getString("webdriver_hostname");
		webdriverServerPortName = gridProperties.getString("webdriver_port");
		webdriverServerHostNameAppium = gridProperties.getString("webdriver_hostname_Appium");
		webdriverServerPortNameAppium = gridProperties.getString("webdriver_port_Appium");
	}


	/**
	 * This function retrieve the Base URL from Grid properties
	 * 
	 * @return - Returns the Application's Base URL
	 */
	public static String getBaseURL() {
		return webdriverUrl;
	}

	/**
	 * This method will return the browser names specified in global properties
	 * file
	 * 
	 * @return array of browser names
	 */
	public static String[] getBrowserName() {
		String[] browserArray = null;
		if (globalProperties.containsKey("browser_name")) {
			String browsers = globalProperties.getString("browser_name");
			if (browsers.contains(",")) {
				browserArray = browsers.trim().split(",");
			} else {
				browserArray = browsers.trim().split(" ");
			}
			for (String browser : browserArray) {
				if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("InternetExplorer")
						|| browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Android")
						|| browser.equalsIgnoreCase("PhantomJS")|| browser.equalsIgnoreCase("Android_Chrome")) {
					return browserArray;
				} else {
					logger.error(
							"Browser name specified in global properties file is invalid. Please give InternetExplorer,Chrome ,Firefox,Android or PhantomJS");

				}
			}

		} else {
			logger.error("No browser name specified for the test execution");

		}
		return browserArray;
	}

	public static String getGlobalBrowserFlag() {
		return globalProperties.getString("use_global_browser_forExcel");

	}

public static String captureScreenshot(WebDriver driver) {
		reportscreenshotpath = new File("").getAbsolutePath().toString().trim()+"/ExtentReport/screenshot/";
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = reportscreenshotpath+ System.nanoTime() + ".png";
			File Destination = new File(Dest);
			FileUtils.copyFile(source, Destination);
			System.out.println("Screenshot taken");
			return Dest;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
			return e.getMessage();
		}

	}
	/**
	 * 
	 * @return
	 */
	public static String getbasedir() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		return s;

	}

	/**
	 * This function is to set system info to extent report
	 * 
	 * 
	 * @param driver-webdriver
	 */

	@SuppressWarnings("unchecked")
	public static void addsysinfo(WebDriver driver) {
		@SuppressWarnings("rawtypes")
		Map sysInfo = new HashMap();
		sysInfo.put("Environment", "Test");
		sysInfo.put("Browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());

	}
	
	/**
	 * 
	 * @param tags
	 * @param test
	 * @param driv
	 */
	public static void runCucumberTests(String tags,String test){
		
		
		test = new File("").getAbsolutePath()+"/src/test/resources/features/"+test+".feature";
		System.out.println("feature file name::"+ test);
		List<String> CucumberOpts = new ArrayList<String>(); 
		String timepattern = "yyyyMMdd-hhmmss";
		String timestamp = new SimpleDateFormat(timepattern).format(new Date());
		if (tags.isEmpty()) {
			CucumberOpts.add ("-p");
			CucumberOpts.add("json:target/cucumber-report-" + timestamp + ".json");
			CucumberOpts.add("--glue");
			CucumberOpts.add("com.tcs.saf.teststeps");
			CucumberOpts.add(test);
			System.out.println("test name"+test);
		} else {
			CucumberOpts.add ("-p");
			CucumberOpts.add("json:target/cucumber-report-" + timestamp + ".json");
			CucumberOpts.add("--glue");
			CucumberOpts.add("com.tcs.saf.teststeps");
			CucumberOpts.add(test);
			CucumberOpts.add("--tags");
			CucumberOpts.add(tags);
		}
		String[] argv = (String[]) CucumberOpts.toArray( new String[CucumberOpts.size()] );
 		RuntimeOptions runtimeOptions = new RuntimeOptions(new ArrayList<String>(asList(argv)));
 		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 		ResourceLoader resourceLoader = new MultiLoader(classLoader);
 		ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader,classLoader);
 		Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions); 		
 		try {
			runtime.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		}
	
	
	

	/**
	 * 
	 * @param report
	 * @param testNameOfCurrentThread
	 * @param desc
	 * @return
	 */
	public static ExtentTest TestReportGenerator(ExtentReports report, String testNameOfCurrentThread, String desc) {
		ExtentTest test = report.startTest(testNameOfCurrentThread, desc);
		return test;
	}
	
	
	public String getValue(String key) {
		if (getMapDataSheet().containsKey(key)) {			

			return getMapDataSheet().get(key);
		} else {
			logger.info("Column heading '" + key
					+ "' is not present in your data source \n Please Check the Column Headings of your TestCase's data in your data source (Excel / Database)");
			throw new NullPointerException("Column heading " + key
					+ "is not present in your data source \n Please Check the Column Headings of your TestCase's data in your data source (Excel / Database)");
		}

	}
	/**
	 * 
	 * @return
	 */
	public LinkedHashMap<String, String> getMapDataSheet() {
		return testDataSheet;
	}
	/**
	 * 
	 * @param mapDataSheet
	 */
	public void setMapDataSheet(LinkedHashMap<String, String> mapDataSheet) {
		testDataSheet = new LinkedHashMap<String, String>();
		this.testDataSheet = mapDataSheet;
	}
	
	/**
	 * 
	 * @param driver
	 * @param test
	 * @param testStep
	 */
	public static void reportFailure(WebDriver driver,ExtentTest test,String expected_Result,String actual_result){
		String screenshot_path = captureScreenshot(driver);
		String image = test.addScreenCapture(screenshot_path);
		test.log(LogStatus.FAIL,expected_Result+", Actual Result :"+actual_result, image);
	}
	/**
	 * 
	 * @param test
	 * @param expected_Result
	 * @param actual_result
	 */
	public static void reportPass(WebDriver driver,ExtentTest test,String expected_Result,String actual_result){
		test.log(LogStatus.PASS,expected_Result,actual_result);
	}
	
}