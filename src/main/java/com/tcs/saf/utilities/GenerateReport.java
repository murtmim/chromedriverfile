package com.tcs.saf.utilities;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class GenerateReport {

	/**
	 * 
	 */
	public static void createRepo() {
		String outPutFolder = createFolder();
		String path = new File("").getAbsolutePath();
		String inDir = path + "/target";
		String outDir = path + "/Cucumber_report/"+outPutFolder;
		File reportOutputDirectory = new File(outDir);
		File reportDirectory = new File(inDir);
		File[] files = reportDirectory.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".json");
			}
		});
		List<String> jsonFiles = new ArrayList<String>();
		for (int iTestCnt = 0; iTestCnt < files.length; iTestCnt++) {
			System.out.println(files[iTestCnt].getName());
			jsonFiles.add(inDir + "/" + files[iTestCnt].getName());
		}
		String jenkinsBasePath = "";
		String buildNumber = "1";
		String projectName = "BITS Framework Report";
		/*
		 * boolean skippedFails = true; boolean pendingFails = false; boolean
		 * undefinedFails = true; boolean missingFails = true;
		 */
		boolean runWithJenkins = false;
		boolean parallelTesting = false;
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// configuration.setStatusFlags(skippedFails, pendingFails,
		// undefinedFails, missingFails);
		configuration.setParallelTesting(parallelTesting);
		configuration.setJenkinsBasePath(jenkinsBasePath);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
		System.out.println("Cucumber report generated in the folder:" + outDir);
	}

	/**
	 * 
	 */
	public static void deleteFiles() {
		String path = new File("").getAbsolutePath();
		String inDir = path + "/target";
		File folder = new File(inDir);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().endsWith(".json")) {
				file.delete();

			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String createFolder() {
		String fileNameFirst = "";
		String fileNameFinal = "";
		String path = new File("").getAbsolutePath();
		String inDir = path + "/target";
		File folder = new File(inDir);
		File[] listOfFiles = folder.listFiles();		
		try{
			for (File file : listOfFiles) {
				if (file.getName().endsWith(".json")) {
					fileNameFirst = file.getName().toString().trim();
					fileNameFinal = fileNameFirst.split("-report-")[1];
					fileNameFinal = "Result_" + fileNameFinal.split(".json")[0];
				}
			}
			if (fileNameFinal.equals("")) {
				fileNameFinal = "Result_New";
			}
		}catch(Exception ex){
			
		}
		return fileNameFinal;
	}

}
