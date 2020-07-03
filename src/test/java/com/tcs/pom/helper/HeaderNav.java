/*
package com.tcs.pom.helper;

import java.util.LinkedHashMap;
import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.tcs.pom.pages.NavHeaderHomePage;
import com.tcs.saf.base.BasePage;
import com.tcs.saf.base.BasePage.BY_TYPE;
import com.tcs.saf.base.BaseTest;

*/
/**
 * This is a sample page object class. Here the End User can define the
 * functional definitions.
 * 
 * @author TCS Automation CoE
 *
 *//*

public class HeaderNav extends BaseTest implements NavHeaderHomePage  {

	public static ExtentReports report;
	public static ExtentTest test;
	static boolean status_Step = false;
	static By locator;
	static String verifyText;
	static int counter = 0;

	*/
/**
	 * 
	 * @return
	 *//*

	public boolean headerComponentValidations() {
		try {
			locator = BasePage.getLocator(NavHeaderHomePage.homeLandingPage, BY_TYPE.BY_CSSSELECTOR, getDriver());
			status_Step = BasePage.isElementVisible(locator, getDriver());
			if (!status_Step) {
				reportFailure(getDriver(), test, "Verify whether TCS.com home page is loaded",
						"TCS.com home page is not properly loaded and hence stopping the execution");
			} else {
				reportPass(test, "Verify whether TCS.com home page is loaded",
						"TCS.com home page is loaded in the Web Browser");
			}
		} catch (Exception ex) {
			reportFailure(getDriver(), test, "Verify whether TCS.com home page is loaded",
					execution_Format.toString().trim());
		}
		return status_Step;
	}

	*/
/**
	 * 
	 * @return
	 *//*

	public boolean headerMenuButtonValidation() {
		try {
			locator = BasePage.getLocator(NavHeaderHomePage.menuButtonHomePage, BY_TYPE.BY_CSSSELECTOR, getDriver());
			status_Step = BasePage.isElementVisible(locator, getDriver());
			if (!status_Step) {
				reportFailure(getDriver(), test, "Verify whether Header Nav Menu button is displayed",
						"Header Nav Menu button is not displayed in the page and hence stopping the execution");
			} else {
				reportPass(test, "Verify whether Header Nav Menu button is displayed",
						"Header Nav Menu button is displayed in the TCS home page");
				BasePage.click(locator, getDriver());
			}
		} catch (Exception ex) {
			reportFailure(getDriver(), test, "Verify whether Header Nav Menu button is displayed",
					ex.toString().trim());
		}
		return status_Step;
	}

	*/
/**
	 * 
	 * @param dataSet
	 * @return
	 *//*

	public boolean headerMenuLinksValidation(LinkedHashMap<String, String> dataSet) {
		try {
			locator = BasePage.getLocator(NavHeaderHomePage.navOptionsList, BY_TYPE.BY_CSSSELECTOR, getDriver());
			List<WebElement> menuList = getDriver().findElements(locator);
			if (menuList.size() == 5) {
				counter = 1;
				for (WebElement e : menuList) {
					if (e.getText().equals(null) || e.getText().equals("")) {
						verifyText = "";
						verifyText = ((JavascriptExecutor) getDriver())
								.executeScript("return arguments[0].innerText;", e).toString();
						if (verifyText.equals(dataSet.get("MenuLink" + counter))) {
							BasePage.highlightElement(e, getDriver());
							reportPass(test,
									"Verify whether " + dataSet.get("MenuLink" + counter)
											+ " link is displayed in the header section",
									dataSet.get("MenuLink" + counter) + " link is displayed in the Header");
							e.findElement(By.tagName("a")).click();
							Thread.sleep(400);
							headerSubLinksValidation(dataSet, NavHeaderHomePage.industriesOPtions, "ValidSub");
							backNavigation(1);
							Thread.sleep(400);
						} else {
							reportFailure(getDriver(), test,
									"Verify whether " + dataSet.get("MenuLink" + counter)
											+ " link is displayed in the header section",
									dataSet.get("MenuLink" + counter) + " link is not displayed in the Header");
						}

					} else {
						verifyText = "";
						verifyText = e.getText();
						if (verifyText.equals(dataSet.get("MenuLink" + counter))) {
							BasePage.highlightElement(e, getDriver());
							reportPass(test,
									"Verify whether " + dataSet.get("MenuLink" + counter)
											+ " link is displayed in the header section",
									dataSet.get("MenuLink" + counter) + " link is displayed in the Header");
							e.findElement(By.tagName("a")).click();
							Thread.sleep(400);
							if (counter == 1) {
								headerSubLinksValidation(dataSet, NavHeaderHomePage.industriesOPtions, "IndustriesSub");
								backNavigation(counter - 1);
								Thread.sleep(400);
								counter++;
							} else if (counter == 2) {
								headerSubLinksValidation(dataSet, NavHeaderHomePage.servicesAndTechnologies,
										"ServicesSub");
								backNavigation(counter - 1);
								Thread.sleep(400);
								counter++;
							} else if (counter == 3) {
								headerSubLinksValidation(dataSet, NavHeaderHomePage.productsAndPlatforms, "ProductSub");
								backNavigation(counter - 1);
								Thread.sleep(400);
								counter++;
							} else if (counter == 4) {
								headerSubLinksValidation(dataSet, NavHeaderHomePage.industriesOPtions, "AboutSub");
								backNavigation(counter - 1);
								Thread.sleep(400);
								break;
							}
						} else {
							reportFailure(getDriver(), test,
									"Verify whether " + dataSet.get("MenuLink" + counter)
											+ " link is displayed in the header section",
									dataSet.get("MenuLink" + counter) + " link is not displayed in the Header");
						}
					}

				}
				headerMenuButtonValidation();
			} else {

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			reportFailure(getDriver(), test,
					"Verify whether " + dataSet.get("MenuLink" + counter) + " link is displayed in the header section",
					ex.toString().trim());
		}
		return status_Step;
	}

	*/
/**
	 * 
	 * @param dataSet
	 * @param locator_Value
	 * @param dataSheet_Prefix
	 * @return
	 *//*

	public boolean headerSubLinksValidation(LinkedHashMap<String, String> dataSet, String locator_Value,
			String dataSheet_Prefix) {
		int v = 1;
		try {
			locator = BasePage.getLocator(locator_Value, BY_TYPE.BY_CSSSELECTOR, getDriver());
			List<WebElement> menuList = getDriver().findElements(locator);
			for (WebElement e : menuList) {
				verifyText = "";
				verifyText = e.getText();
				if (verifyText.equals("")) {
				} else {
					if (!dataSet.get(dataSheet_Prefix + v).toString().trim().equals("")) {
						if (verifyText.trim().equals(dataSet.get(dataSheet_Prefix + v).toString().trim())) {
							BasePage.highlightElement(e, getDriver());
							reportPass(test,
									"Verify whether " + dataSet.get(dataSheet_Prefix + v).toString().trim()
											+ " link is displayed in the header section",
									dataSet.get(dataSheet_Prefix + v).toString().trim()
											+ " link is displayed in the Header");
							v++;
						} else {
							break;
						}

					} else {
						break;
					}
				}

			}

		} catch (Exception ex) {
		}
		return status_Step;
	}

	*/
/**
	 * 
	 * @param count
	 * @throws Exception
	 *//*

	public void backNavigation(int count) throws Exception {
		locator = BasePage.getLocator(NavHeaderHomePage.backButtonMenuHeader, BY_TYPE.BY_CSSSELECTOR, getDriver());
		List<WebElement> menuList = getDriver().findElements(locator);
		try {
			BasePage.highlightElement(menuList.get(count), getDriver());
			menuList.get(count).click();
		} catch (Exception e) {
		}

	}
}
*/
