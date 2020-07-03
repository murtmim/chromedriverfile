package com.tcs.saf.base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeOptions;
import jxl.JXLException;
import jxl.read.biff.BiffException;
import jxl.write.biff.RowsExceededException;

public class BrowserSetUp {
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 
	 * @param browser
	 * @param logger
	 * @param testName
	 * @param mapDataSheet
	 * @return
	 * @throws MalformedURLException
	 */
	public static ThreadLocal<WebDriver> setMyBrowser(String browser, String testName,
			LinkedHashMap<String, String> mapDataSheet, String executionFormat) throws MalformedURLException {
		ResourceBundle browserProperties = getBrowserProperties();
		if (!(browser == null)) {
			String formattedTime = timeFormat.format(new Date()).toString();
			BaseTest.logger.info("\n" + "\nTest Case Name            :  " + testName + "\nExecution using Data Row  :"
					+ mapDataSheet.toString() + "\nStart time                :  " + formattedTime
					+ "\n-------------------------------------------------------------------------------------------------------------------------------------");
			if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("InternetExplorer")
					|| browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Android")
					|| browser.equalsIgnoreCase("Android_Chrome") || browser.equalsIgnoreCase("PhantomJS")) {
				URL remoteServerUrl = new URL("http://" + BaseTest.webdriverServerHostName + ":"
						+ BaseTest.webdriverServerPortName + "/wd/hub");
				URL remoteServerUrlAppium = new URL("http://" + BaseTest.webdriverServerHostNameAppium + ":"
						+ BaseTest.webdriverServerPortNameAppium + "/wd/hub");
				BaseTest.logger.info("Browser type is:" + browser);
				DesiredCapabilities driverCapability = null;
				if ("InternetExplorer".equalsIgnoreCase(browser)) {
					try {
						driverCapability = DesiredCapabilities.internetExplorer();
						driverCapability.setCapability("browserName", "internet explorer");
						driverCapability.setCapability("acceptSslCerts", "true");
						driverCapability.setCapability("javascriptEnabled", "true");
						driverCapability.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						System.setProperty("webdriver.ie.driver", browserProperties.getString("webdriver_ie_driver"));
						BaseTest.logger.info("getWebDriver - Setting webdriver.ie.driver system property as: "
								+ System.getProperty("webdriver.ie.driver"));
						if (executionFormat.equals("grid")) {
							webDriver.set(new RemoteWebDriver(remoteServerUrl, driverCapability));
						} else {
							webDriver.set(new InternetExplorerDriver(driverCapability));
						}
					} catch (IllegalStateException e) {
						BaseTest.logger.error(
								"The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file",
								e.fillInStackTrace());
						throw new IllegalStateException(
								"The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file");

					}
				} else if ("Firefox".equalsIgnoreCase(browser)) {
					driverCapability = DesiredCapabilities.firefox();
					driverCapability.setCapability("browserName", "firefox");
					if (executionFormat.equals("grid")) {
						webDriver.set(new RemoteWebDriver(remoteServerUrl, driverCapability));
					} else {
						webDriver.set(new FirefoxDriver(driverCapability));
					}

				} else if ("Chrome".equalsIgnoreCase(browser)) {
					try {
						driverCapability = DesiredCapabilities.chrome();
						driverCapability.setCapability("browserName", "chrome");
						System.setProperty("webdriver.chrome.driver",
								browserProperties.getString("webdriver_chrome_driver"));
						driverCapability.setCapability("acceptSslCerts", "true");
						driverCapability.setCapability("javascriptEnabled", "true");
						ChromeOptions cop = new ChromeOptions();
						cop.merge(driverCapability);
						cop.addArguments("start-maximized"); // open Browser in maximized mode
						cop.addArguments("disable-infobars"); // disabling infobars
						cop.addArguments("--disable-extensions"); // disabling extensions
						cop.addArguments("--disable-gpu"); // applicable to windows os only
						cop.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
						cop.addArguments("--no-sandbox"); // Bypass OS security model
						//cop.setHeadless(true);
						if (executionFormat.equals("grid")) {
							webDriver.set(new RemoteWebDriver(remoteServerUrl, driverCapability));
						} else {
							webDriver.set(new ChromeDriver(cop));
						}

					} catch (IllegalStateException e) {
						BaseTest.logger.error(
								"The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file",
								e.fillInStackTrace());
						throw new IllegalStateException(
								"The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file");
					}
					driverCapability.setCapability("acceptSslCerts", true);
					driverCapability.setJavascriptEnabled(true);
				} else if ("PhantomJS".equalsIgnoreCase(browser)) {
					System.setProperty("phantomjs.binary.path", browserProperties.getString("phantomjs_binary_path"));
					driverCapability = DesiredCapabilities.phantomjs();
					driverCapability.setJavascriptEnabled(true);
					driverCapability.setCapability("takesScreenshot", false);
					if (executionFormat.equals("grid")) {
						webDriver.set(new RemoteWebDriver(remoteServerUrl, driverCapability));
					} else {
						// webDriver.set(new ChromeDriver(driverCapability));
					}

				} else if ("Android".equalsIgnoreCase(browser)) {
					driverCapability = DesiredCapabilities.android();
					driverCapability.setCapability("deviceName", browserProperties.getString("device_name"));
					driverCapability.setCapability("platformName", "Android");
					driverCapability.setCapability("platformVersion", browserProperties.getString("android_version"));
					driverCapability.setCapability("appPackage", browserProperties.getString("app_package"));
					driverCapability.setCapability("appActivity", browserProperties.getString("app_activity"));
					if (executionFormat.equals("grid")) {
						webDriver.set(new AndroidDriver<MobileElement>(remoteServerUrl, driverCapability));
					} else {
						webDriver.set(new AndroidDriver<MobileElement>(remoteServerUrlAppium, driverCapability));

					}

				} else if ("Android_Chrome".equalsIgnoreCase(browser)) {
					try {

						driverCapability = DesiredCapabilities.android();
						driverCapability.setCapability("deviceName", browserProperties.getString("device_name"));
						driverCapability.setCapability("platformName", "Android");
						driverCapability.setCapability("platformVersion",
								browserProperties.getString("android_version"));
						driverCapability.setCapability(CapabilityType.BROWSER_NAME, "chrome");
						if (executionFormat.equals("grid")) {
							webDriver.set(new RemoteWebDriver(remoteServerUrl, driverCapability));
						} else {
							webDriver.set(new RemoteWebDriver(remoteServerUrlAppium, driverCapability));
						}
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (browser.equalsIgnoreCase(null)) {
					BaseTest.logger
							.error("DesiredCapabilities is null , Unable to instantiate new webDriver. Unrecognised browser identifier. "
									+ browser
									+ "/n possible causes, \n\t1.'EXECUTION STATUS' column in data sheet is empty for all fields\n\t2.'EXECUTION STATUS' column in data sheet is 'N' for all fields");

				} else {
					BaseTest.logger
							.error("DesiredCapabilities is null , Unable to instantiate new webDriver. Unrecognised browser identifier. "
									+ browser);
				}
				BaseTest.logger.info("Test initialization");
				BaseTest.logger.info("Calling startWebDriverClient for browser: " + browser);
			} else {
				BaseTest.logger.error("Browser name" + " '" + browser + "' "
						+ "is invalid:Please give InternetExplorer,Chrome,Firefox,Android or PhantomJS");

			}
		}
		return webDriver;
	}

	/**
	 * 
	 * @return
	 */
	public static ResourceBundle getBrowserProperties() {
		ResourceBundle browserProperties = null;
		browserProperties = ResourceBundle.getBundle("global");
		return browserProperties;

	}

	/**
	 * 
	 * @param key
	 * @param mapDataSheet
	 * @param logger
	 * @return
	 */
	public static String getValue(String key, LinkedHashMap<String, String> mapDataSheet, Logger logger) {
		if (mapDataSheet.containsKey(key)) {
			return mapDataSheet.get(key);
		} else {
			logger.info("Column heading '" + key
					+ "' is not present in your data source \n Please Check the Column Headings of your TestCase's data in your data source (Excel / Database)");
			throw new NullPointerException("Column heading " + key
					+ "is not present in your data source \n Please Check the Column Headings of your TestCase's data in your data source (Excel / Database)");
		}

	}

	/**
	 * 
	 * @param browser
	 * @param logger
	 * @param test
	 * @param report
	 * @param driver
	 * @throws SQLException
	 * @throws BiffException
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws JXLException
	 * 
	 * 
	 */

	public static void terminateTest(ITestResult testResult, String browser, ExtentTest test, ExtentReports report,
			WebDriver driver) throws SQLException, BiffException, IOException, RowsExceededException, JXLException {
		if (!(browser == null)) {
			if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("InternetExplorer")
					|| browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Android")
					|| browser.equalsIgnoreCase("Android_Chrome") || browser.equalsIgnoreCase("PhantomJS")) {
				if (BaseTest.testDataSource.equalsIgnoreCase("database")) {
					while (BaseTest.databaseConnection != null)
						BaseTest.databaseConnection.closeDatabaseConnectivity();
				}
				if (BaseTest.testDataSource.equalsIgnoreCase("excel")) {

				}
			} else {
				BaseTest.logger.error("Invalid browser name");
			}
			report.endTest(test);
			report.flush();
			System.out.println("Test Execution generated");
			driver.close();
			System.out.println("Test Browser closed and Execution Completed");
			driver.quit();
		} else {
			report.endTest(test);
			report.flush();
		}
		String formattedTime = timeFormat.format(new Date()).toString();
		BaseTest.logger.info("In afterMethod - Performing test cleanup , Closing webdriver instance\n"
				+ "End time         :       " + formattedTime
				+ "\n---------------------------------------------------------------------------------------------------\n"
				+

				"-----------------------------------------------------------------------------------------------------\n");
	}
}
