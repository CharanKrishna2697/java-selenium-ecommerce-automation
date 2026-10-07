package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // TC01 - Valid Login
    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Valid login failed"
        );
    }

    // TC02 - Invalid Login
    @Test(priority = 2)
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.contains("Username and password do not match"),
                "Expected invalid login error was not displayed"
        );
    }

    // TC03 - Locked Out User
    @Test(priority = 3)
    public void lockedOutUserTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(
                errorMessage.contains("locked out"),
                "Expected locked-out user error was not displayed"
        );
    }
}