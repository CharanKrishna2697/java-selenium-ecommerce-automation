package com.automation.testdata;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][]{

                {"standard_user", "secret_sauce", true},

                {"invalid_user", "wrong_password", false},

                {"locked_out_user", "secret_sauce", false}

        };
    }
}