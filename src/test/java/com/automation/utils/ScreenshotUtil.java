package com.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        try {

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Path screenshotDirectory =
                    Path.of("screenshots");

            Files.createDirectories(screenshotDirectory);

            String fileName =
                    testName + "_" + System.currentTimeMillis() + ".png";

            Path destination =
                    screenshotDirectory.resolve(fileName);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destination.toAbsolutePath().toString();

        } catch (Exception e) {

            System.out.println(
                    "Unable to capture screenshot: "
                            + e.getMessage()
            );

            return null;
        }
    }
}