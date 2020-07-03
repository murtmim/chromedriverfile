
/**
 * This class contains all Selenium and other tool level keywords
 * Author : Test Automation CoE
*/

package com.tcs.saf.base;
import io.appium.java_client.DeviceActionShortcuts;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
//import io.appium.java_client.ScrollsTo;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.util.Calendar;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidCookieDomainException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.sikuli.script.FindFailed;
//import org.sikuli.script.Screen;
//import com.sun.jna.platform.unix.X11.Screen;
public  abstract class BasePage {
	static Dimension size;
	//public static Screen s = new Screen();

	public static enum BY_TYPE {BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME,
		BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME};

		/**
		 * 
		 * @param locator
		 * @param type
		 * @param driver
		 * @return
		 */
		public static By getLocator(String locator, BY_TYPE type,WebDriver driver){
			switch (type) {
			case BY_XPATH:
				return By.xpath(locator);
			case BY_LINKTEXT:
				return By.linkText(locator);
			case BY_ID:
				return By.id(locator);
			case BY_CSSSELECTOR:
				return By.cssSelector(locator);
			case BY_CLASSNAME:
				return By.className(locator);
			case BY_NAME:
				return By.name(locator);
			case BY_PARTIALLINKTEXT:
				return By.partialLinkText(locator);
			case BY_TAGNAME:
				return By.tagName(locator);
			}
			throw new IllegalArgumentException("Invalid By Type, Please provide correct locator type");

		}

		public static By getLocatorList(String locator, BY_TYPE type,WebDriver driver){
			switch (type) {
			case BY_XPATH:
				return By.xpath(locator);
			case BY_LINKTEXT:
				return By.linkText(locator);
			case BY_ID:
				return By.id(locator);
			case BY_CSSSELECTOR:
				return By.cssSelector(locator);
			case BY_CLASSNAME:
				return By.className(locator);
			case BY_NAME:
				return By.name(locator);
			case BY_PARTIALLINKTEXT:
				return By.partialLinkText(locator);
			case BY_TAGNAME:
				return By.tagName(locator);
			}
			throw new IllegalArgumentException("Invalid By Type, Please provide correct locator type");

		}
		/**
		 * This function launches the Application URL from grid.properties
		 */
		/**
		 * 
		 * @param URL
		 * @param driver
		 */
		public static void launchPageURL(String URL,WebDriver driver) {
			if (URL.length() != 0) {
				try {
					driver.get(URL);
					//code added by munira
					driver.manage().window().maximize();
					waitForLoad(driver, 60);
					BaseTest.logger.info("The Base URL:" + URL + "is loaded");
				} catch (UnreachableBrowserException e) {
					BaseTest.logger.error("Unable to load the Base URL: ", e.fillInStackTrace());
					throw new UnreachableBrowserException("Unable to load the Base URL: " + URL);
				}
			} else {
				BaseTest.logger.error("Unable to load the Base URL: " + URL + " Please provide a valid Base URL");
				throw new UnreachableBrowserException(
						"Unable to load the Base URL: " + URL + " Please provide a valid Base URL.");
			}
		}

	/**
	 * 
	 * @param locator_Property
	 * @param driver
	 * @throws Exception
	 */
		public static void highlightElement(WebElement locator_Property,WebDriver driver) throws Exception {
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", locator_Property, "border: 3px solid red;");
			Thread.sleep(400);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", locator_Property, "border:'';");
		}

		/**This function returns the Current Window URL
		 * @param driver
		 * @returnString 
		 * 			- returns the Current Window URL
		 */
		public static String getCurrentURL(WebDriver driver) 
		{
			BaseTest.logger.info("The current Browser URL is returned");
			return driver.getCurrentUrl();
		}

		/**
		 * This function is to navigate the browser to a URL
		 * 
		 * @param URL
		 * 			- URL to which browser has to be navigated
		 * @param driver
		 */
		public static void navigateToURL(String URL,WebDriver driver)
		{
			try
			{
				driver.navigate().to(URL);
			}
			catch(UnreachableBrowserException e)
			{
				BaseTest.logger.error("Unable to launch the URL: "+URL);
				throw new UnreachableBrowserException("Unable to launch the URL: "+URL);
			}
		}
		/**
		 * This function returns the Current Window Title
		 * @param driver
		 * @return String  
		 * 			- returns the Current Window Title
		 */
		public static String getPageTitle(WebDriver driver) 
		{
			BaseTest.logger.info("The Current Window title is " + driver.getTitle());
			return driver.getTitle();
		}


		/**
		 * This function checks whether the Current Window URL is same as the
		 * Expected
		 * 
		 * @param expectedURL
		 *           	 - URL expected in the Current Window
		 * @param driver
		 * @return boolean 
		 * 				- returns true if the CurrentWindow URL matches the
		 *         		  expectedURL, else returns false
		 */
		public static boolean isURLAsExpected(String expectedURL,WebDriver driver) 
		{
			BaseTest.logger.info("The Current URL:" + getCurrentURL(driver) + "; Expected URL:"
					+ expectedURL);
			return expectedURL.equals(getCurrentURL(driver));
		}

		/**
		 * This function is to load the previous URL in the browser history.
		 * @param driver
		 */
		public static void navigateBack(WebDriver driver) 
		{
			try 
			{
				driver.navigate().back();
				BaseTest.logger.info("The page is navigated backwards");
			} 
			catch (WebDriverException e) 
			{
				BaseTest.logger.error("The page cannot be navigated backward",e.fillInStackTrace());
				throw new WebDriverException("The page cannot be navigated backward");
			}
		}
		/**
		 * This function loads the URL which is forward in the browser's history.
		 * Does nothing if we are on the latest page viewed.
		 * 
		 */
		public static void navigateForward(WebDriver driver) 
		{
			try 
			{
				driver.navigate().forward();
				BaseTest.logger.info("The page is navigated forward");
			} 
			catch (UnreachableBrowserException e) 
			{
				BaseTest.logger.error("The page cannot be navigated forward",e.fillInStackTrace());
				throw new UnreachableBrowserException("The page cannot be navigated forward");
			}
		}
		/**
		 * This function refresh the current page
		 * 
		 */
		public static void refreshPage(WebDriver driver) 
		{
			try 
			{
				driver.navigate().refresh();
				BaseTest.logger.info("The page is refreshed");
			} 
			catch (UnreachableBrowserException e) 
			{
				BaseTest.logger.error("The page cannot be refreshed",e.fillInStackTrace());
				throw new UnreachableBrowserException("The page cannot be refreshed");
			}
		}

		/**
		 * 
		 * @param driver
		 * @param time
		 */
		public static void waitForLoad(WebDriver driver,int time) {
			boolean status = false;
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			long timeTaken;
		    ExpectedCondition<Boolean> pageLoadCondition = new
		        ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) {
		                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		            }
		        };
		    WebDriverWait wait = new WebDriverWait(driver, time);
		    do {
		    	
				try {
					wait.until(pageLoadCondition);
					if(wait.until(pageLoadCondition)==true){	
						status = true;
					}else{
						status = false;
					}
					} catch (NoSuchElementException e) {
						status = false;
					}
					endTime = Calendar.getInstance();
					timeTaken = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
					if (timeTaken > time){
						timeTaken = timeTaken-time;	
						startTime = Calendar.getInstance();
					}
			} while(status == false && timeTaken <= time);		   		   
		}

		/**
		 * This function is to make the driver wait explicitly for a condition to be
		 * satisfied
		 * 
		 * @param locator
		 *            - By object of the element whose visibility/presence/clickability has to be checked
		 */
		public static void addExplicitWait(By locator, String condition, int inttimeoutinseconds, WebDriver driver )
		{

			WebDriverWait webDriverWait = new WebDriverWait(driver, inttimeoutinseconds, 250L);
			try
			{
				if(condition.equalsIgnoreCase("visibility"))
				{
					webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
					BaseTest.logger.info("Driver waits explicitly until the element with"+locator.
							toString().replace("By."," ")+" is visible");
				}
				else if(condition.equalsIgnoreCase("clickable"))
				{
					webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
					BaseTest.logger.info("Driver waits explicitly until the element with"+locator.
							toString().replace("By."," ")+" is clickable");
				}
				else if(condition.equalsIgnoreCase("presence"))
				{
					webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
					BaseTest.logger.info("Driver waits explicitly until the element with"+locator.
							toString().replace("By."," ")+" is present");
				}
				else
					BaseTest.logger.error("Condition String should be visibility or clickable or presence");
			}
			catch(NoSuchElementException e)
			{
				BaseTest.logger.error("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
			catch(UnsupportedCommandException e)
			{
				BaseTest.logger.error("The condition given to check the elemnt with"
						+ locator.toString().replace("By.", " ")
						+ " is invalid",e.fillInStackTrace());
				throw new NoSuchElementException("The condition given to check the elemnt with"
						+ locator.toString().replace("By.", " ")
						+ " is invalid",e.fillInStackTrace());
			}
		}
		/**
		 * This function clicks on the element which can be located by the By Object
		 * 
		 * @param locator
		 *            - By object to locate the element to be clicked
		 */
		public static void click(By locator, WebDriver driver)  
		{
			try
			{
				driver.findElement(locator).click();
				BaseTest.logger.info("The element with"
						+ locator.toString().replace("By.", " ")
						+ " is clicked");
			}
			catch(NoSuchElementException e)
			{
				BaseTest.logger.error("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}

		/**
		 * This function is to pass a text into an input field within UI
		 * 
		 * @param locator
		 *            - By object to locate the input field to which text has to be
		 *            send
		 * @param value
		 *            - Text value which is to be send to the input field
		 *            
		 * @param driver           
		 */
		public static void type(By locator, String value, WebDriver driver)
		{
			try 
			{
				driver.findElement(locator).clear();
				driver.findElement(locator).sendKeys(value);
				BaseTest.logger.info("String " + value + " is send to element with"
						+ locator.toString().replace("By.", " "));
			} 
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}

		/**
		 * This function is to get the visible text of an element within UI
		 * 
		 * @param locator
		 *            - By object to locate the element from which the text has to
		 *            be taken
		 * @param driver          
		 * @return String - returns the innertext of the specified element
		 */
		public static String getText(By locator, WebDriver driver) 
		{
			String text = null;
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			try 
			{
				/*BaseTest.logger.info("The value on the field with"
						+ locator.toString().replace("By.", " ")
						+ " is obtained");*/
				text = driver.findElement(locator).getText();
			} 
			catch (NullPointerException ex){
				WebElement element = driver.findElement(locator);
				text = (String) jse.executeScript("return arguments[0].text", element);
			}
			catch (NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to get the text. The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("Unable to get the text. The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
			return text;
		}


		/**
		 * This function is to select a dropdown option using its index
		 * 
		 * @param locator
		 *            - By object to locate the dropdown which is to be selected
		 * @param driver           
		 * @param index
		 *            - index of the dropdown option to be selected
		 */
		public static void selectDropDownByIndex(By locator, int index, WebDriver driver)
		{
			try 
			{
				Select dropDown = new Select(driver.findElement(locator));
				dropDown.selectByIndex(index);
				BaseTest.logger.info("The dropdown option with index " + index
						+ " is selected");
			} 
			catch (NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to select the dropdown; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}


		/**
		 * This function is to select the dropdown options that have a value
		 * matching the argument
		 * 
		 * @param locator
		 *            - By object to locate the dropdown which is to be selected
		 * @param value
		 *            - value to match against the dropdown option to be selected
		 * @param driver           
		 */
		public static void selectDropDownByValue(By locator, String value,WebDriver driver)
		{
			try 
			{
				Select dropDown = new Select(driver.findElement(locator));
				dropDown.selectByValue(value);
				BaseTest.logger.info("The dropdown option with value " + value
						+ " is selected");
			} 
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("Unable to select the dropdown; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}

		/**
		 * This function is to select the dropdown options that displays text
		 * matching the argument
		 * 
		 * @param locator
		 *            - By object to locate the dropdown which is to be selected
		 * @param visibleText
		 *            - visible text to match against the dropdown option to be
		 *            selected
		 * @param driver           
		 */
		public static void selectDropDownByVisibleText(By locator, String visibleText,WebDriver driver) 
		{
			try
			{
				Select dropDown = new Select(driver.findElement(locator));
				dropDown.selectByVisibleText(visibleText);
				BaseTest.logger.info("The dropdown option with text " + visibleText
						+ " is selected");
			} 
			catch (NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to select the dropdown; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}


		/**
		 * This function is to move the mouse pointer to the specified location
		 * 
		 * @param locator
		 *            - By object to locate the element to which mouse pointer has
		 *            to be moved
		 *@param driver            
		 */
		public static void mouseOver(By locator,WebDriver driver) 
		{
			try
			{
				new Actions(driver).moveToElement(driver.findElement(locator))
				.build().perform();
				BaseTest.logger.info("Mouse hover is performed on element with"
						+ locator.toString().replace("By.", " "));
			} 
			catch (NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to perform MouseOver; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		} 

		/**
		 * This function is to click on an element by moving the mouse pointer 
		 * to the specified location or to read the tip of a mouse 
		 * 
		 * @param locator 
		 * 				- By object to locate the element to which mouse pointer has 
		 * 				to be moved
		 *@param driver 
		 */
		public static void moveMouseTipAndClick(By locator,WebDriver driver)
		{
			try
			{
				WebElement element = driver.findElement(locator);
				Locatable hoverItem = (Locatable) element;
				Mouse mouse = ((HasInputDevices) driver).getMouse();
				mouse.mouseMove(hoverItem.getCoordinates());
				mouse.click(hoverItem.getCoordinates());
			}
			catch(NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to perform click; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}


		/**
		 * This function performs a click-and-hold at the location of the source
		 * element; moves to the location of the target element, then releases the
		 * mouse.
		 * 
		 * @param initialElementLocator
		 *            - By object of the initial location of the source webelement
		 * @param finalElementLocator
		 *            - By object of the final location where the webelement has to
		 *              be moved
		 * @param driver             
		 */
		public static void dragAndDrop(By initialElementLocator, By finalElementLocator, WebDriver driver)
		{
			try 
			{
				Actions builder = new Actions(driver);
				Action dragAndDrop = builder
						.clickAndHold(driver.findElement(initialElementLocator))
						.moveToElement(driver.findElement(finalElementLocator))
						.release(driver.findElement(finalElementLocator))
						.build();
				dragAndDrop.perform();
				BaseTest.logger.info("The element is draged from"
						+ initialElementLocator.toString().replace("By.", " ")
						+ " to"
						+ finalElementLocator.toString().replace("By.", " "));
			} 
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("Unable to perform dragAndDrop;The element is not draged from"
						+ initialElementLocator.toString().replace("By.", " ")
						+ " to"
						+ finalElementLocator.toString().replace("By.", " "),e.fillInStackTrace());
				throw new NoSuchElementException("Unable to perform dragAndDrop;The element is not draged from"
						+ initialElementLocator.toString().replace("By.", " ")
						+ " to"
						+ finalElementLocator.toString().replace("By.", " "));
			}
		}
		/**
		 * This function is to perform double click on a webelement
		 * 
		 * @param locator
		 *            - By object of the webelement on which double click has to be
		 *            performed
		 *@param driver            
		 */
		public static void doubleClick(By locator,WebDriver driver)
		{
			try
			{
				Actions builder = new Actions(driver);
				builder.doubleClick(driver.findElement(locator));
				BaseTest.logger.info("The element with" + locator.toString().replace("By.", " ")
						+ " is right clicked");
			}
			catch(NoSuchElementException e)
			{
				BaseTest.logger.error("Unable to perform doubleClick; The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
		}
		/**
		 * This function is to maximize the Current Browser Window
		 * @param driver
		 * 
		 */
		public static void maximizeWindow(WebDriver driver) 
		{
			try 
			{
				driver.manage().window().maximize();
				BaseTest.logger.info("Window is maximized");
			} 
			catch (UnreachableBrowserException e) 
			{
				BaseTest.logger.error("Unable to maximize the window: ",e.fillInStackTrace());
				throw new NoSuchElementException("Unable to maximize the window");
			}
		}




		/**
		 * This function is to add a time delay
		 * 
		 * @param time
		 *            - time duration in MilliSeconds
		 */
		public static void delay(int time) throws InterruptedException 
		{
			try 
			{
				Thread.sleep(time);
				BaseTest.logger.info("Delay for " + time + " MilliSeconds is added");
			} 
			catch (InterruptedException e) 
			{
				BaseTest.logger.error("Issue in adding extra delay",e.fillInStackTrace());
				throw new InterruptedException("Issue in adding extra delay");
			} 
		}
		/**
		 * This function is to get the current window handle
		 * 
		 * @return windowHandle 
		 * 				- returns the handle of current browser window
		 * 
		 * @param driver
		 */
		public static String getWindowHandle(WebDriver driver) 
		{
			String windowHandle = driver.getWindowHandle();
			BaseTest.logger.info("The current window handle " + windowHandle
					+ " is returned");
			try 
			{
				windowHandle = driver.getWindowHandle();
				BaseTest.logger.info("The current window handle "+windowHandle+" is returned");
			} 
			catch (WebDriverException e) 
			{
				BaseTest.logger.error("Unable to returm the window handle: ",e.fillInStackTrace());
				throw new WebDriverException("Unable to returm the window handle");
			}
			return windowHandle;
		}


		/**
		 * This function is to switch the driver from Current Window to newly opened Window
		 * @param driver
		 */
		public static void switchToWindow(WebDriver driver) 
		{
			try 
			{
				for (String windowHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(windowHandle);
				}
				BaseTest.logger.info("The window is switched");
			} 
			catch (NoSuchWindowException e) 
			{
				BaseTest.logger.error("Unable to switch the window: ",e.fillInStackTrace());
				throw new NoSuchWindowException("Unable to switch the window");
			}
		}


		/**
		 * This function is to switch into a frame using frame index
		 * 
		 * @param index
		 *            - index of the frame to which driver has to be switched into
		 * @param driver           
		 */
		public static void switchToFrameByIndex(int index,WebDriver driver) {
			try 
			{
				driver.switchTo().frame(index);
				BaseTest.logger.info("The driver is switched into frame");
			} 
			catch (NoSuchFrameException e) 
			{
				BaseTest.logger.error("Unable to switch into frame: ",e.fillInStackTrace());
				throw new NoSuchFrameException("Unable to switch into frame");
			}
		}

		/**
		 * This function is to switch into a frame using the frame name
		 * 
		 * @param frameName
		 *            - name of the frame to which driver has to be switched into
		 *  @param          
		 */
		public static void switchToFrameByName(String frameName,WebDriver driver) 
		{
			if (!frameName.equalsIgnoreCase(null)) 
			{
				try 
				{
					driver.switchTo().frame(frameName);
					BaseTest.logger.info("The driver is switched to frame: " + frameName);
				} 
				catch (NoSuchFrameException e) 
				{
					BaseTest.logger.error("Unable to switch into frame:" ,e.fillInStackTrace());
					throw new NoSuchFrameException("Unable to switch into frame");
				}
			}
			BaseTest.logger.info("Unable to switch into frame as framename is null");
		}

		/**
		 * This function is to switch into a frame; frame is located as a webelemet
		 * 
		 * @param locator
		 *            - By object of the webelemet using which frame can be located
		 * @param driver           
		 */
		public static void switchToFrameByWebElement(By locator,WebDriver driver) 
		{
			try 
			{
				driver.switchTo().frame(driver.findElement(locator));
				BaseTest.logger.info("The driver is switched to frame with"
						+ locator.toString().replace("By.", " "));
			} 
			catch (NoSuchFrameException e) 
			{
				BaseTest.logger.error("Unable to switch into frame: ",e.fillInStackTrace());
				throw new NoSuchFrameException("Unable to switch into frame");
			}
		}

		/**
		 * This function is to click on a webelemet using JavascriptExecutor
		 * 
		 * @param locator 
		 * 			- By object of the webelement which is to be clicked
		 * @param driver
		 */
		public static void clickUsingJavascriptExecutor(By locator,WebDriver driver)
		{
			try 
			{
				JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;;
				WebElement webElement = driver.findElement(locator);
				javaScriptExecutor.executeScript("arguments[0].click();", webElement);
				BaseTest.logger.info("The element with"
						+locator.toString().replace("By."," ")
						+" is clicked");
			} 
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}

		}

		/**
		 * This function is to scroll the browser window to a webelement 
		 * using JavascriptExecutor
		 * 
		 * @param locator 
		 * 				- By object of the webelement to which the window has to be scrolled
		 * @param driver
		 */
		public static void scrollToElementUsingJavascriptExecutor(By locator,WebDriver driver)
		{
			try
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement webElement = driver.findElement(locator);
				js.executeScript("arguments[0].scrollIntoView(true);",webElement);
				BaseTest.logger.info("Browser window is scrolled to element with"
						+locator.toString().replace("By."," "));
			}
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("Unable to scroll: ",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
			catch (MoveTargetOutOfBoundsException e) 
			{
				BaseTest.logger.error("Unable to scroll: ",e.fillInStackTrace());
				throw new MoveTargetOutOfBoundsException("Target element provided with"
						+locator.toString().replace("By."," ")+"is invalid");
			}

		}


		/**
		 * This function is to check whether a webelement is visible or not
		 * 
		 * @param locator 
		 * 				- By object of the webelement which is to be checked
		 * @return boolean 
		 * 				- returns true if the specified webelement is visible,
		 * 				  else it will return false
		 * @param driver
		 */
		
		public static boolean isElementVisible(By locator,WebDriver driver)
		{
			try{
				if(driver.findElement(locator)!=null)
				{
				highlightElement(driver.findElement(locator), driver);
				BaseTest.logger.info("The element with" 
						+ locator.toString().replace("By.", " ")+ " is visible");				
				}
				return true;
			}
			catch(Exception e) 
			{
				BaseTest.logger.info("The element with" 
						+ locator.toString().replace("By.", " ")+ " is not visible");
				return false; 
			}
		}

		/**
		 * This function is to check whether a webelement is enabled or not
		 * 
		 * @param locator
		 * 				- By object of the webelement which is to be checked
		 * @return boolean 
		 * 				- returns true if the specified webelement is enabled, 
		 * 				  else it will return false
		 * @param driver
		 */
		public static boolean isElementEnabled(By locator,WebDriver driver) 
		{
			try{
				if(driver.findElement(locator).isEnabled()==true)
				{
				
				BaseTest.logger.info("The element with" 
						+ locator.toString().replace("By.", " ")+ " is enable");
				}
			}
			catch(Exception e) 
			{
				BaseTest.logger.info("The element with" 
						+ locator.toString().replace("By.", " ")+ " is not enable");
			}return false; 
		}
		


		/**
		 * This function is to check whether the Current Window Title is as expected
		 * 
		 * @param expectedTitle 
		 * 				- Title expected in the Current Window
		 * @return boolean 
		 * 				- returns true if the CurrentTitle matches the expectedTitle, 
		 * 				  else it will return false
		 * @param driver
		 */
		public static boolean isTitleAsExpected(String expectedTitle,WebDriver driver)
		
		{
			try{
				if(expectedTitle.equals(getPageTitle(driver))==true)
				{
				
					BaseTest.logger.info("The current window title is "+getPageTitle(driver)
							+" same as "+expectedTitle);
				}
			}
			catch(Exception e) 
			{
				BaseTest.logger.info("The current window title is "+getPageTitle(driver)
						+" whereas the expected is "+expectedTitle);
			}return false; 
		}
		

		/**
		 * This function is to check whether there is any alert present.
		 * 
		 * 
		 * @return boolean 
		 * 				- returns true if alert is present, 
		 * 				  else it will return false
		 * @param driver
		 */

		public static boolean isAlertPresent(WebDriver driver)
		{
			try{
				driver.switchTo().alert();
				BaseTest.logger.info("An alert box is present");
				return true;
			}
			catch(Exception e)
			{
				BaseTest.logger.error("There is no alert present ", e.fillInStackTrace());
				return false;
			}
		}


		/**
		 * This function is to get a cookie with a specific name
		 * 
		 * @param cookieName
		 *            	- Name of the cookie which is to be returned
		 * @return Cookie 
		 * 				- Returns the cookie value for the name specified, or null
		 *         			if no cookie found with the given name
		 * @param driver        
		 */
		public static Cookie getCookie(String cookieName,WebDriver driver)
		{
			BaseTest.logger.info("The cookie:"+cookieName +" is obtained");
			return driver.manage().getCookieNamed(cookieName);
		}


		/**
		 * This function is to delete a cookie from the browser's "cookie jar"
		 * The domain of the cookie will be ignored.
		 * 
		 * @param cookieName 
		 * 				- name of the cookie which is to be deleted.
		 * @param driver
		 */
		public static void deleteCookieNamed(String cookieName,WebDriver driver)
		{
			if(!cookieName.equalsIgnoreCase(null)) 
			{
				try 
				{
					BaseTest.logger.info("The cookie:"+cookieName +" is deleted");
					driver.manage().deleteCookieNamed(cookieName);
				}
				catch (InvalidCookieDomainException e) 
				{
					BaseTest.logger.error("Unable to delete the cookie: ",e.fillInStackTrace());
					throw new InvalidCookieDomainException("The cookie with name "
							+ cookieName+ " cannot be deleted");
				}
			}
			else
				BaseTest.logger.info("Cookie Name is null; Unable to delete");
		}

		/**
		 * This function is to delete all the cookies for the current domain
		 * @param driver
		 */
		public static void deleteAllCookie(WebDriver driver)
		{
			try 
			{
				driver.manage().deleteAllCookies();
				BaseTest.logger.info("All cookies are deleted");
			} 
			catch (InvalidCookieDomainException e)
			{
				BaseTest.logger.error("Unable to delete cookies: ",e.fillInStackTrace());
				throw new InvalidCookieDomainException("Unable to delete cookies");
			}
		}

		/**
		 * This function is to perform a right click on a particular webelement
		 * @param locator - By object of the Webelement on which right click 
		 * 					operation has to be performed
		 * @param driver
		 */
		public static void rightClick(By locator,WebDriver driver)
		{
			try
			{
				WebElement webElement = driver.findElement(locator);
				Actions action= new Actions(driver);
				action.contextClick(webElement).build().perform();
				BaseTest.logger.info("The element with "
						+locator.toString().replace("By."," ")+" is right clicked");
			}
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("Unable to scroll: ",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}
			catch (UnsupportedCommandException e) 
			{
				BaseTest.logger.error("Unable to scroll: ",e.fillInStackTrace());
				throw new UnsupportedCommandException("Command used by the webdriver is unsupported");
			}
		} 

		/**
		 * This function is to move the Webelement to an offset from the 
		 * top-left corner of the Webelement
		 * 
		 * @param locator 
		 * 				- By object to locate the Webelement which is to be moved
		 * @param xOffset 
		 * 				- xOffset by which the Webelement will be moved 
		 * 				  from the current position towards x-axis
		 * @param yOffset 
		 * 				- yOffset by which the Webelement will be moved 
		 * 				  from the current position towards y-axis
		 * @param driver
		 */
		public static void moveToElement(By locator,int xOffset,int yOffset,WebDriver driver)
		{
			try
			{
				Actions builder = new Actions(driver);
				builder.moveToElement(driver.findElement(locator), xOffset, yOffset);
				BaseTest.logger.info("Element with "+locator.toString().replace("By."," ")
						+" " +"is moved "+xOffset+" along x-axis and"
						+yOffset+" along y-axis");
			}
			catch (MoveTargetOutOfBoundsException e) 
			{
				BaseTest.logger.error("Unable to move the element from current position",e.fillInStackTrace());
				throw new MoveTargetOutOfBoundsException("Target provided x:"+xOffset
						+"and y:"+yOffset+"is invalid");
			}
			catch (NoSuchElementException e) 
			{
				BaseTest.logger.error("Unable to move the element from current position",e.fillInStackTrace());
				throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
			}

		}


		/**
		 * This function is to handle the alert; Will Click on OK button
		 * First get a handle to the open alert, 
		 * prompt or confirmation and then accept the alert.
		 * @param driver
		 * 
		 */
		public static void acceptAlert(WebDriver driver)
		{
			try 
			{
				Alert alertBox=driver.switchTo().alert();
				alertBox.accept();
				BaseTest.logger.info("The alert is accepted");
			} 
			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Unable to access the alert: ",e.fillInStackTrace());
				throw new NoAlertPresentException("Alert not present");
			}
		}

		/**
		 * This function is to handle the alert; Will Click on Cancel button
		 * First get a handle to the open alert, 
		 * prompt or confirmation and then dismiss the alert.
		 * 
		 * @param driver
		 * 
		 */
		public static void dismissAlert(WebDriver driver)
		{
			try 
			{
				Alert alertBox=driver.switchTo().alert();
				alertBox.dismiss();
				BaseTest.logger.info("The alert is dismissed");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Unable to access the alert: ",e.fillInStackTrace());
				throw new NoAlertPresentException("Alert not present");
			}
		}

		/**
		 * This function is to get the text which is present on the Alert.
		 * 
		 * @return String
		 * 			 - returns the text message which is present on the Alert.
		 * @param driver
		 */
		public static String getAlertMessage(WebDriver driver)
		{	
			String alertMessage = null;
			try 
			{
				Alert alertBox=driver.switchTo().alert();
				BaseTest.logger.info("The text "+alertBox.getText()+" within popup is returned");
				alertMessage = alertBox.getText();
			} 
			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Unable to access the alert:",e.fillInStackTrace());
				throw new NoAlertPresentException("Alert not present");
			}
			return alertMessage;
		}

		/**
		 * This function closes the Current Browser Window
		 * @param driver
		 */
		public static void closeCurrentWindow(WebDriver driver)
		{
			driver.close();
			BaseTest.logger.info("Driver window is closed");
		}
		/**
		 * This function is to perform sikuli click action
		 * @throws FindFailed 
		 * 
		 */
		/*public static String sikuliclick(String img) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.click(img);
				BaseTest.logger.info("Performed Sikuli Click Action");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path",e.fillInStackTrace());

			}
			return msg;
		}*/

		/**
		 * This function is to perform sikuli input action
		 * @throws FindFailed 
		 * 
		 */

		/*public static String sikulitype(String img,String input) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.find(img); 
				s.type(img,input);
				BaseTest.logger.info("Sikuli Input action is performed");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path: ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}*/

		/**
		 * This function is to perform Sikuli RightClick action
		 * @throws FindFailed 
		 * 
		 */

		/*public static String SikuliRightClick(String img) throws FindFailed   
		{
			String msg=null;
			try 
			{
				s.rightClick(img);
				BaseTest.logger.info("Right Click Action Is Performed");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}*/
		/**
		 * This function is to perform Sikuli DoubleClick action
		 * @throws FindFailed 
		 * 
		 */
		/*public static String SikuliDoubleClick(String img) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.doubleClick(img);
				BaseTest.logger.info("Double Click Action Is Performed");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}
		*//**
		 * This function is to verify an image present
		 * @throws FindFailed 
		 * 
		 *//*
		public static String SikuliImageExist(String img) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.exists(img);
				BaseTest.logger.info("Image is found");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}*/
		/**
		 * This function is to Drag and Drop an image present
		 * @throws FindFailed 
		 * 
		 */
		/*public static String SikuliDragandDrop(String Source,String Destination) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.dragDrop(Source,Destination);
				BaseTest.logger.info("Source Image is Dragged and dropped to Destination");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}
		*//**
		 * This function is to Paste a text in image
		 * @throws FindFailed 
		 * 
		 *//*
		public static String SikuliPaste(String img,String TextToBePassed) throws FindFailed   *//************************ Sikuli***//*
		{
			String msg=null;
			try 
			{
				s.paste(img,TextToBePassed);
				BaseTest.logger.info("Value is passed into SikuliImage");
			} 

			catch (NoAlertPresentException e) 
			{
				BaseTest.logger.error("Please provide a valid image path ",e.fillInStackTrace());
				throw new NoAlertPresentException("Please provide a valid image path");
			}
			return msg;
		}*/
		// Native android operations
		/**
		 * This function is hide keypad opened in android device
		 * 
		 */

		public static void hidekey(WebDriver driver) {
			((DeviceActionShortcuts) driver).hideKeyboard();
			
		}

		/**
		 * Method to perform
		 * 
		 * @param element
		 *            -webelement where to perform swipe action
		 * @param right
		 *            -width point at right of screen
		 * @param left
		 *            -widthpoint at left of screen
		 */
		public static void swipewithtouch_righttoleft(By locator, int right,
				int left) {
			TouchAction action = new TouchAction(null);
			action.longPress((WebElement) locator).moveTo(right, left).release().perform();
		}

		/**
		 * Method to perform
		 * 
		 * @param element
		 *            -webelement where to perform swipe action
		 * @param right
		 *            -width point at right of screen
		 * @param left
		 *            -widthpoint at left of screen
		 */
		public static void swipewithtouch_lefttoright(By locator, int left,
				int right,AndroidDriver<MobileElement> driver) {
			TouchAction action = new TouchAction((MobileDriver) driver);
			action.longPress((WebElement) locator).moveTo(left, right).release().perform();
		}

		/**
		 * 
		 * @param driver
		 * @return size of the webdriver screen
		 */
		public static Dimension getsize(AndroidDriver<MobileElement> driver) {
			size = driver.manage().window().getSize();
			return size;
		}

		/**
		 * 
		 * @param mobile
		 *            driver
		 * @return leftstartingpoint
		 */
		public static int getleftpoint(AndroidDriver<MobileElement> driver) {
			size = getsize(driver);
			int leftpoint = (int) (size.width * 0.80);
			return leftpoint;
		}

		/**
		 * 
		 * @param mobile
		 *            driver
		 * @return right starting point
		 */
		public static int getrightpoint(AndroidDriver<MobileElement> driver) {
			size = getsize(driver);
			int rightpoint = (int) (size.width * 0.20);
			return rightpoint;
		}

		/**
		 * 
		 * @param element
		 *            where to scroll
		 * @return 
		 * 
		 */
		
		//@SuppressWarnings("unchecked")
/*		public static void scrolltoelement(String locator,WebDriver driver) {
			((ScrollsTo<WebElement>) driver).scrollTo(locator);*/
	
		//}

}







