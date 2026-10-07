package com.automation.base;

import com.automation.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Disable Chrome password manager
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Disable Chrome password leak detection popup
        options.addArguments("--disable-features=PasswordLeakDetection");

        // Read browser from configuration
        String browser = ConfigReader.getProperty("browser");

        // Launch browser
        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver(options);

        } else {

            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }

        // Maximize browser
        driver.manage().window().maximize();

        // Open application
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}