package com.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getReportInstance() {

        if (extentReports == null) {

            String reportPath =
                    System.getProperty("user.dir")
                            + "/test-output/ExtentReport.html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle(
                    "Automation Test Execution Report"
            );

            sparkReporter.config().setReportName(
                    "E-Commerce Selenium Automation"
            );

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Project",
                    "Java Selenium E-Commerce Automation"
            );

            extentReports.setSystemInfo(
                    "Automation Tool",
                    "Selenium WebDriver"
            );

            extentReports.setSystemInfo(
                    "Framework",
                    "TestNG"
            );

            extentReports.setSystemInfo(
                    "Java Version",
                    "17"
            );

            extentReports.setSystemInfo(
                    "Browser",
                    "Chrome"
            );
        }

        return extentReports;
    }
    public static void flushReport() {

        if (extentReports != null) {
            extentReports.flush();
        }
    }
}