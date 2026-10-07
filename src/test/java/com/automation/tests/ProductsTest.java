package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    private void login() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed"
        );
    }

    @Test(priority = 4, groups = {"smoke", "products"})
    public void verifyProductsPage() {

        login();

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Products page was not displayed"
        );
    }

    @Test(priority = 5, groups = {"smoke", "products"})
    public void addProductToCart() {

        login();

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addBackpackToCart();

        Assert.assertEquals(
                productsPage.getCartItemCount(),
                "1",
                "Cart item count is incorrect"
        );
    }

    @Test(priority = 6, groups = {"regression", "products"})
    public void verifyProductInCart() {

        login();

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addBackpackToCart();
        productsPage.clickCart();

        Assert.assertTrue(
                productsPage.isBackpackDisplayedInCart(),
                "Backpack was not displayed in cart"
        );
    }
}