/*package com.tcs.saf.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class FlexFlashWebDriver  {

	private final WebDriver webDriver;
	private final String flashObjectId;
	
	*//**
	 * @param flashObjectId
	 *            -This argument is for passing the flash object id
	 *//*
	
	public FlexFlashWebDriver(final WebDriver webDriver,final String flashObjectId) {       
		this.webDriver = webDriver;
		this.flashObjectId = flashObjectId;
	}
	
	private Selenium selenium;
	
	*//**
	 * This method checks the visibility of Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	
	public boolean isFlexVisible(String objectId) {
		final String result = callObject("getFlexVisible", objectId, "");
		
		if (!"true".equals(result) && !"false".equals(result)) {
			System.out.println(String.format("Visibility of '%s' returned an unexpected value: %s", objectId, result));
		}
		
		return Boolean.parseBoolean(result);
	}
	
	*//**
	 * This method checks if the Flex checkbox is checked 
	 * @param checkBoxId
	 *            -This argument is for checking if the checkbox is checked or not
	 *//*
	public boolean isFlexCheckboxChecked(String checkBoxId) {
		return Boolean.parseBoolean(callObject("getFlexCheckBoxChecked", checkBoxId, ""));
	}
	
	*//**
	 * This method selects a Flex checkbox
	 * @param checkBoxId
	 *            -This argument is for selecting a checkBox
	 *//*
	public String selectFlexCheckbox(String checkBoxId, boolean value) {
		return callObject("doFlexCheckBox", checkBoxId, Boolean.toString(value));
	}
	
	*//**
	 * This method selects an Index
	 * @param selectionFieldId
	 *            -This argument is for passing id for the field selected
	 * @param index
	 *            -This argument is for passing the index number
	 *//*
	public void flexSelectIndex(String selectionFieldId, int index) {
		callObject("doFlexSelectIndex", selectionFieldId, Integer.toString(index));
	}
	
	*//**
	 * This method checks if the Flex object is enabled
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public boolean isFlexEnabled(String objectId) {
		final String state = callObject("getFlexEnabled", objectId, "");
		
		if (!"true".equals(state) && !"false".equals(state)) {
			System.out.println(String.format("Enabled state of '%s' returned an unexpected value: %s", objectId, state));
		}
		return Boolean.parseBoolean(state);
	}
	
	*//**
	 * This method checks existence of the Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public boolean flexExists(String objectId) {
		return Boolean.parseBoolean(callObject("getFlexExists", objectId, ""));
	}
	
	*//**
	 * This method clicks on Flex alert button
	 * @param alertButton
	 *            -This argument is for passing name of alertButton
	 *//*
	public String clickFlexAlert(String alertButton) {
		return callObject("doFlexAlertResponse", alertButton, alertButton);
	}
	
	*//**
	 * This method enters date into a date field
	 * @param dateField
	 *            -This argument is for passing locator for dateField
	 * @param dateAsText
	 *            -This argument is for passing the date as text
	 *//*
	public void enterFlexDate(String dateField, String dateAsText) {
		callObject("doFlexDate", dateField, dateAsText);		
	}
	
	*//**
	 * This method gets text from a Flex object
	 * @param textFieldId
	 *            -This argument is for passing the object id of textField
	 *//*
	public String getFlexText(String textFieldId) {
		return callObject("getFlexText", textFieldId, "");
	}
	
	*//**
	 * This method checks if a Flex alert is visible
	 *//*
	public boolean isFlexAlertVisible() {
		return Boolean.parseBoolean(callObject("getFlexAlertPresent", "", ""));
	}
	
	*//**
	 * This method is used to capture screenshot
	 * @param saveToFile
	 *            -This argument is for passing the destination path of the file name for screenshot to be saved
	 *//*	
	public void flexCaptureScreenshot(String saveToFile) {
		selenium.captureScreenshot(saveToFile);
	}
	
	*//**
	 * This method selects a field
	 * @param selectionFieldId
	 *            -This argument is for passing the object id of selectionField
	 * @param itemToSelect
	 *            -This argument is passing the item to be selected          
	 *//*
	public String flexSelect(String selectionFieldId, String itemToSelect) {
		return callObject("doFlexSelect", selectionFieldId, itemToSelect);
	}
	
	*//**
	 * This method clicks the Menu bar of Flex application with an optional button label
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument passes the button label of Menu bar          
	 *//*
	public String flexClickMenuBarUIComponent(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexClickMenuBarUIComponent", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method clicks the Menu bar of Flex application
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String flexClickMenuBarUIComponent(final String objectId) {
		return flexClickMenuBarUIComponent(objectId, "");
	}
	
	*//**
	 * This method clicks on a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param objectId
	 *            -This argument is for passing the button label if present
	 *//*
	public String flexClick(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexClick", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method clicks on a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String flexClick(final String objectId) {
		return flexClick(objectId,"");
	}
	
	*//**
	 * This method clicks on a data grid item
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String ClickDataGridItem(final String objectId) {
		return flexClick(objectId, "");
	}
	
	*//**
	 * This method clicks on  a data grid item with an optional button label
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String flexClickDataGridItem(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexClickDataGridItem", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method clicks on a data grid as a UI component
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String flexClickDataGridUIComponent(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexClickDataGridUIComponent", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method clicks on a data grid as a UI component
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String flexClickDataGridUIComponent(final String objectId) {
		return flexClickDataGridItem(objectId, "");
	}
	
	*//**
	 * This method selects the Flex object by label of a combo box
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String flexSelectComboByLabel(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexSelectComboByLabel", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method selects the Flex object by label of a combo box
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String doFlexSelectComboByLabel(final String objectId) {
		return flexClick(objectId, "");
	}
	
	*//**
	 * This method selects a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String FlexSelect(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexSelect", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method selects a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String FlexSelect(final String objectId) {
		return FlexSelect(objectId, "");
	}
	
	*//**
	 * This method gets the refreshed tool tip id of Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String FlexRefreshIDToolTips(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexRefreshIDToolTips", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method gets the refreshed tool tip id of Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String FlexRefreshIDToolTips(final String objectId) {
		return FlexRefreshIDToolTips(objectId, "");
	}
	
	*//**
	 * This method performs double click action
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String FlexDoubleClick(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexDoubleClick", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method performs double click action
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String FlexDoubleClick(final String objectId) {
		return FlexDoubleClick(objectId, "");
	}
	
	*//**
	 * This method is used to set focus on Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String FlexSetFocus(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexSetFocus", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method is used to set focus on Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String FlexSetFocus(final String objectId) {
		return FlexSetFocus(objectId, "");
	}
	
	*//**
	 * This method performs mouse move action
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String FlexMouseMove(final String objectId, final String optionalButtonLabel) {       
		return callObject("doFlexMouseMove", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method performs mouse move action
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String FlexMouseMove(final String objectId) {
		return FlexMouseMove(objectId, "");
	}
	
	*//**
	 * This method is used to type into a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 * @param optionalButtonLabel
	 *            -This argument is for passing the optional button label
	 *//*
	public String flexType(final String objectId, final String optionalButtonLabel)  {    
		return callObject("doFlexType", objectId, optionalButtonLabel);
	}
	
	*//**
	 * This method is used to type into a Flex object
	 * @param objectId
	 *            -This argument is for passing the object id
	 *//*
	public String flexType(final String objectId) {
		return flexType(objectId, "");
	}
	
	*//**
	 * This method gets the data field label for grid row
	 * @param dataGridId
	 *            -This argument is for passing the data grid id
	 * @param field
	 *            -This argument is for passing the field object
	 * @param row
	 *            -This argument is for passing the row number
	 *//*
	public String getFlexDataFieldLabelForGridRow(String dataGridId, String field, int row) {
		return callObject("getFlexDataGridFieldLabelForGridRow", dataGridId, field, Integer.toString(row));
	}
	
	*//**
	 * This method gets the data grid row count
	 * @param dataGridId
	 *            -This argument is for passing the data grid id
	 *//*
	public int getFlexDataGridRowCount(String dataGridId ) {
		return Integer.parseInt(callObject("getFlexDataGridRowCount", dataGridId));
	}
	
	*//**
	 * This method gets the selection index of Flex object
	 * @param selectionFieldId
	 *            -This argument is for passing the selection field id
	 *//*
	public int getFlexSelectionIndex(String selectionFieldId) {
		return Integer.parseInt(callObject("getFlexSelectionIndex", selectionFieldId, ""));
	}
	
	*//**
	 * This method calls the object and passes values to the function specified
	 * @param functionName
	 *            -This argument is for passing the function name
	 * @param args
	 *            -This argument is for passing any number of parameters
	 *//*
	private String callObject(final String functionName, final String... args) {
		final Object result =((JavascriptExecutor)webDriver).executeScript(makeJsFunction(functionName, args),new Object[0]);
		System.out.println("result:"+result);
		return result != null ? result.toString() : null;
	}
	
	*//**
	 * This method performs the execution of the function specified
	 * @param functionName
	 *            -This argument is for passing the function name
	 * @param args
	 *            -This argument is for passing any number of parameters
	 *//*
	private String makeJsFunction(final String functionName, final String... args) {
		final StringBuffer functionArgs = new StringBuffer();
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (i > 0) {
					functionArgs.append(",");
				}
				functionArgs.append(String.format("'%1$s'", args[i]));
			}
		}
		System.out.println("fun_Args:"+functionArgs);
		System.out.println(String.format("return document.%1$s.%2$s(%3$s);",flashObjectId,functionName,functionArgs));
		return String.format("return document.%1$s.%2$s(%3$s);",flashObjectId,functionName,functionArgs);
	}
}
*/