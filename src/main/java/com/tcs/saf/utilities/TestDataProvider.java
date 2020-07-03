package com.tcs.saf.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import com.tcs.saf.exceptions.DataSheetException;
import com.tcs.saf.exceptions.DatabaseConnectivityException;
import com.tcs.saf.exceptions.InvalidBrowserException;
import com.tcs.saf.base.BaseTest;

/**
 * @author TCS
 * 
 */
public class TestDataProvider {

	/**
	 * @param tableName
	 *            - name of the database tableName from which data needs to be
	 *            fetched
	 * @param testCaseName
	 *            - name of the test case
	 * @return Object Array of HashMap which contains key value as column
	 *         heading and its corresponding value as value pair
	 * @throws DatabaseConnectivityException
	 * @throws SQLException
	 * @throws InvalidBrowserException 
	 */
	@DataProvider(name = "dataBase")
	public Object[][] getDatabaseData(String tableName, String testCaseName)
			throws DatabaseConnectivityException, SQLException, InvalidBrowserException {

		DatabaseConnection dbConnection = new DatabaseConnection(
				BaseTest.databaseType, BaseTest.databaseName,
				BaseTest.databaseUserName, BaseTest.databasePassword,BaseTest.databaseServerIP);
		dbConnection.getConnection();
		Object[][] dataMap = (Object[][]) null;

		// DatabaseConnection dbConnection = new DatabaseConnection();
		dataMap = dbConnection.getDataFromDatabase(tableName, testCaseName);
		return dataMap;
	}

	
	@DataProvider(name = "dataBase")
	public Object[][] getDataFromSingleRow(String tableName, String testCaseName, int rowNo)
			throws DatabaseConnectivityException, SQLException, InvalidBrowserException {

		DatabaseConnection dbConnection = new DatabaseConnection(
				BaseTest.databaseType, BaseTest.databaseName,
				BaseTest.databaseUserName, BaseTest.databasePassword,BaseTest.databaseServerIP);
		dbConnection.getConnection();
		Object[][] dataMap = (Object[][]) null;

		// DatabaseConnection dbConnection = new DatabaseConnection();
		dataMap = dbConnection.getDataFromSingleRow(tableName, testCaseName,rowNo);

		return dataMap;
	}

	
	/**
	 * This method will return a 2-dimensional object array with hashmap data in
	 * each object. The data fetched is from specified column name of a
	 * table,where test case name is that of class name for a specific row
	 * 
	 * @param tableName
	 * @param testCaseName
	 * @param rowNo
	 * @param columnHeading
	 * @return
	 * @throws DatabaseConnectivityException
	 * @throws SQLException
	 * @throws InvalidBrowserException 
	 */
	@DataProvider(name = "dataBase")
	public Object[][] getDataFromColumnRow(String tableName,
			String testCaseName, int rowNo, String columnHeading)
			throws DatabaseConnectivityException, SQLException, InvalidBrowserException {

		DatabaseConnection dbConnection = new DatabaseConnection(
				BaseTest.databaseType, BaseTest.databaseName,
				BaseTest.databaseUserName, BaseTest.databasePassword,BaseTest.databaseServerIP);
		dbConnection.getConnection();
		Object[][] dataMap = (Object[][]) null;

		// DatabaseConnection dbConnection = new DatabaseConnection();
		dataMap = dbConnection.getDataFromColumnRow(tableName, testCaseName, rowNo,
				columnHeading);
		return dataMap;
	}

	/**
	 * This method will return a 2-dimensional object array with hashmap data in
	 * each object. The data fetched is from specified column name of a database
	 * table.
	 * 
	 * @param tableName
	 * @param testCaseName
	 * @param columnHeading
	 * @return
	 * @throws DatabaseConnectivityException
	 * @throws SQLException
	 * @throws InvalidBrowserException 
	 */
	@DataProvider(name = "dataBase")
	public Object[][] getDataFromColumnHeading(String tableName,
			String testCaseName, String columnHeading)
			throws DatabaseConnectivityException, SQLException, InvalidBrowserException {

		DatabaseConnection dbConnection = new DatabaseConnection(
				BaseTest.databaseType, BaseTest.databaseName,
				BaseTest.databaseUserName, BaseTest.databasePassword,BaseTest.databaseServerIP);
		dbConnection.getConnection();
		Object[][] dataMap = (Object[][]) null;

		// DatabaseConnection dbConnection = new DatabaseConnection();
		dataMap = dbConnection.getColumnDataFromDatabase(tableName,
				testCaseName, columnHeading);
		return dataMap;
	}

	/**
	 * This method will return a 2-dimensional object array with hashmap data in
	 * each object. The data is fetched for a test case name from row values
	 * specified between from row no and to row now. Both row numbers are
	 * inclusive.
	 * 
	 * @param tableName
	 * @param testCaseName
	 * @param fromRowNo
	 * @param toRowNo
	 * @return
	 * @throws DatabaseConnectivityException
	 * @throws SQLException
	 * @throws InvalidBrowserException 
	 */
	@DataProvider(name = "dataBase")
	public Object[][] getDataFromRows(String tableName, String testCaseName,
			int fromRowNo, int toRowNo) throws DatabaseConnectivityException,
			SQLException, InvalidBrowserException {

		DatabaseConnection dbConnection = new DatabaseConnection(
				BaseTest.databaseType, BaseTest.databaseName,
				BaseTest.databaseUserName, BaseTest.databasePassword,BaseTest.databaseServerIP);
		dbConnection.getConnection();
		Object[][] dataMap = (Object[][]) null;

		// DatabaseConnection dbConnection = new DatabaseConnection();
		dataMap = dbConnection.getDataFromRows(tableName, testCaseName,
				fromRowNo, toRowNo);
		return dataMap;
	}

	/**
	 * This function returns the data from excel sheet for the specified class
	 * 
	 * @param externalSheetPath
	 * @param sheetName
	 * @return object array
	 * @throws NoDataInInputSheetException
	 * @throws IncorrectSheetPathException
	 * @throws InvalidSheetHeadingException
	 * @throws InvalidBrowserException
	 * @throws IOException
	 * @throws BiffException
	 * @throws DataSheetException
	 * @throws InvalidExecutionStatusException
	 */

	@DataProvider(name = "dataSheet")
	public Object[][] getTestDataFromExcel(String externalSheetPath,
			String sheetName) throws BiffException, IOException,
			 DataSheetException {
		Object[][] dataMap = (Object[][]) null;
		DataSheet dataSheetObj = new DataSheet();
		dataMap = dataSheetObj.readFromSheet(externalSheetPath+"/InputSheet.xls", sheetName);	
		return dataMap;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return -Returns data object array which reads rows from the input data
	 *         sheet
	 * @throws BiffException
	 * @throws InvalidBrowserException
	 * @throws IOException
	 * @throws DataSheetException
	 */
	public String[][] readFromDriverSheet(String destFile, String sheetname)
			throws BiffException, IOException, DataSheetException {
		String[][] dataObjectArray = null;
		@SuppressWarnings("unused")
		int columns = getColumnCount(destFile+"/DriverData.xls", sheetname);
		int rows = getRowCount(destFile+"/DriverData.xls", sheetname);
		@SuppressWarnings("unused")
		int ActualRowCount = 0;
		int dataObjectArraySize = getValidRows(destFile+"/DriverData.xls", sheetname);
		dataObjectArray = new String[dataObjectArraySize][3];
		int index = 0;
		boolean headingStatus = true;
		FileInputStream input = new FileInputStream(destFile+"/DriverData.xls");
		Workbook wb = Workbook.getWorkbook(input);
		Sheet sheet = wb.getSheet(sheetname);
		// Validating for the heading status
		if (headingStatus) {
			for (int row = 1; row < rows; row++) {
				String executionStatus = sheet.getCell(0, row).getContents();
				// Checking if execution status is 'Y'
				if (executionStatus.trim().equalsIgnoreCase("Y")) {
					ActualRowCount++;
					// Store the feature name
					dataObjectArray[index][0] = sheet.getCell(1, row).getContents();
					// tags
					dataObjectArray[index][1] = sheet.getCell(2, row).getContents();
					// row count
					dataObjectArray[index][2] = Integer.toString(row);
					index++;
				} else if (executionStatus.trim().equalsIgnoreCase("")) {
//					Log.error("Please enter Y or N for execution status");
					throw new NullPointerException("Please enter either Y or N for execution status");
				}
			}
		} else {
//			Log.error("The sheet headings are invalid:The headings should be Browser and Execution Status");
			throw new DataSheetException(
					"The sheet headings are invalid:The headings should be Browser and Execution Status");
		}

		input.close();
		// System.out.println("***Data Provider***");
		// for(String[] temp:dataObjectArray)
		// {
		// //System.out.println("Data from Excel is as follows:");
		// for(String temp2:temp)
		// {
		// //System.out.println(temp2);
		// }
		// }

		return dataObjectArray;
	}
	
	
	
	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method returns column count in the data sheet
	 * @throws BiffException
	 * @throws IOException
	 * @throws DataSheetException
	 * @throws FileNotFoundException
	 */
	public int getColumnCount(String destFile, String sheetname)
			throws BiffException, IOException, DataSheetException,
			FileNotFoundException
			{
		int column = 0;
		try {			
			FileInputStream input = new FileInputStream(destFile);
			Workbook wb = Workbook.getWorkbook(input);
			Sheet sheet = wb.getSheet(sheetname);
			column = sheet.getColumns();
			if (column != 0) {
				return column;
			} else {
				//logger.error("The input data sheet is blank");
				throw new DataSheetException("The input data sheet is blank");
			}
		
		} catch (FileNotFoundException fe) {
			//logger.error("Please provide a valid sheet path:" + destFile + " "
			//		+ "can not be found");
			throw new DataSheetException("Please provide a valid sheet path:" + destFile + " "
					+ "can not be found");
		} 
		catch(NullPointerException e){
			//logger.error("No sheet found with the class name "+sheetname);
			throw new DataSheetException("No sheet found with the class name "+sheetname);
		}

			}

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method returns the row count in the sheet
	 * @throws BiffException
	 * @throws IOException
	 */
	public int getRowCount(String destFile, String sheetname)
			throws BiffException, IOException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);

		Sheet sheet = wb.getSheet(sheetname);
		int row = sheet.getRows();
		input.close();
		return row;

	}
	
	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return -This method returns the valid rows considering execution status
	 * @throws BiffException
	 * @throws IOException
	 * @throws DataSheetException
	 */

	public int getValidRows(String destFile, String sheetname)
			throws BiffException, IOException, DataSheetException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);
		Sheet sheet = wb.getSheet(sheetname);
		//int columns = getColumnCount(destFile, sheetname);
		int rows = getRowCount(destFile, sheetname);
		int count = 0;
		for (int row = 1; row < rows; row++) {
			if (sheet.getCell(0, row).getContents().equalsIgnoreCase("Y")) {
				count++;
			}
		}
        input.close();
		return count;
	}
	
	public static String readTestDataSheet(String destFile, String sheetname, int ScenarioName, String Parm)
			throws BiffException, IOException, 
			DataSheetException {		
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);
		Sheet sheet = wb.getSheet(sheetname);		
		int row = ScenarioName;
		String[] ParmArray = Parm.split("-");
		int ParmCol = Integer.parseInt(ParmArray[1]);
		String ParmValue = sheet.getCell(ParmCol+1,row).getContents(); 
		input.close();
		return ParmValue;
	}
	

}
