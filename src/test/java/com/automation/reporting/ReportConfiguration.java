package com.automation.reporting;

import org.testng.annotations.AfterSuite;

public class ReportConfiguration {

    @AfterSuite
    public void flushReport() {

        ExtentReportManager.flushReport();
    }
}