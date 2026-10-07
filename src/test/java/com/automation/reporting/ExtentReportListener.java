package com.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports extentReports =
            ExtentReportManager.getReportInstance();

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extentReports.createTest(
                        result.getMethod().getMethodName()
                );

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest
                .get()
                .pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("========== EXTENT FAILURE LISTENER ==========");
        System.out.println("Failed test: " + result.getMethod().getMethodName());

        extentTest
                .get()
                .fail(result.getThrowable());

        WebDriver driver = getWebDriver(result);

        System.out.println("WebDriver found: " + (driver != null));

        if (driver != null) {

            String testName =
                    result.getMethod().getMethodName();

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            driver,
                            testName
                    );

            System.out.println("Screenshot path: " + screenshotPath);

            if (screenshotPath != null) {

                try {

                    extentTest
                            .get()
                            .addScreenCaptureFromPath(
                                    screenshotPath
                            );

                    extentTest
                            .get()
                            .info("Failure screenshot attached");

                    System.out.println(
                            "Screenshot successfully attached to Extent Report"
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Unable to attach screenshot: "
                                    + e.getMessage()
                    );

                    extentTest
                            .get()
                            .warning(
                                    "Unable to attach screenshot: "
                                            + e.getMessage()
                            );
                }
            }
        }

        System.out.println("============================================");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest
                .get()
                .skip("Test skipped");
    }

    private WebDriver getWebDriver(ITestResult result) {

        Object testClass =
                result.getInstance();

        WebDriver driver = null;

        try {

            Field driverField =
                    testClass.getClass()
                            .getSuperclass()
                            .getDeclaredField("driver");

            driverField.setAccessible(true);

            driver =
                    (WebDriver) driverField.get(testClass);

        } catch (Exception e) {

            System.out.println(
                    "Unable to access WebDriver: "
                            + e.getMessage()
            );
        }

        return driver;
    }
}