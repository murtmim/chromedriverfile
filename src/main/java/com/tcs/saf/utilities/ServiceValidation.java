package com.tcs.saf.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ServiceValidation {

	public static void main(String[] args) {		
//		List<String> results = new ArrayList<String>();
//		List<String> fileFinal = new ArrayList<String>();
//		File[] files = new File("D:/Rahul/StommeAdvancedBuild/ServiceReport").listFiles();
//		for (File file : files) {
//		    if (file.isFile()) {
//		    	
//		    	if(file.getName().trim().contains("Shopping-")){
//		    		results.add(file.getName());
//		    	}	        
//		    }
//		}
//		for (String f:results){
//			System.out.println("f is "+f);
//		}
		//writeToSheet("abv");
		extractResponseParam("<sessionid>","</sessionid>");
	}
	
	/**
	 * 
	 * @param value
	 */
	public static void writeToSheet(String value) {	
		try {
			FileInputStream file = new FileInputStream(new File(
					"D:/Rahul/StommeAdvancedBuild/ServiceReport//ServiceResult.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(1);			
			for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			    Row row = sheet.getRow(i);
			    row.createCell(2).setCellValue("hello");
			}
			file.close();
			FileOutputStream out = new FileOutputStream(new File(
					"D:/Rahul/StommeAdvancedBuild/ServiceReport//ServiceResult.xls"));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return value;
	}
	
	public static void extractResponseParam(String value1,String value2) {	
		List<String> params = new ArrayList<String>();
		String[] a = null;
		String tag = "";
		try {
		    BufferedReader in = new BufferedReader(new FileReader("D:/Rahul/StommeAdvancedBuild/ServiceReport/Shopping-Buyproduct-login__Request_1-0-OK.txt"));
		    String str;
		    while ((str = in.readLine()) != null)
	    	a = str.split(value1);  
		    tag = a[1].split(value2)[0];
		    System.out.println();
		    in.close();
		} catch (IOException e) {
		}
	}

}
