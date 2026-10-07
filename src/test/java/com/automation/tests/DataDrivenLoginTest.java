package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.testdata.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenLoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = {"regression", "login"})
    public void verifyLoginWithMultipleUsers(
            String username,
            String password,
            boolean expectedLoginSuccess) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedLoginSuccess) {

            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    "Valid login failed for user: " + username
            );

        } else {

            Assert.assertFalse(
                    loginPage.isLoginSuccessful(),
                    "Invalid login unexpectedly succeeded for user: " + username
            );
        }
    }
}