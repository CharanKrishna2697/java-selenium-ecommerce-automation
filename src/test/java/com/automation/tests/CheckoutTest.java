package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private void loginAndAddProduct() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed"
        );

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addBackpackToCart();
        productsPage.clickCart();
    }

    // TC07 - Verify Cart Page
    @Test(priority = 7)
    public void verifyCartPage() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page was not displayed"
        );
    }

    // TC08 - Verify Product In Cart
    @Test(priority = 8)
    public void verifyProductDetailsInCart() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Backpack was not displayed in cart"
        );

        Assert.assertEquals(
                cartPage.getBackpackPrice(),
                "$29.99",
                "Backpack price is incorrect"
        );
    }

    // TC09 - Proceed To Checkout
    @Test(priority = 9)
    public void proceedToCheckout() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page was not displayed"
        );
    }

    // TC10 - Validate Checkout Information
    @Test(priority = 10)
    public void validateCheckoutInformation() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Charan");
        checkoutPage.enterLastName("Krishna");
        checkoutPage.enterPostalCode("500001");

        checkoutPage.clickContinue();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two"),
                "Checkout overview page was not displayed"
        );
    }

    // TC11 - Complete Order
    @Test(priority = 11)
    public void completeOrder() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Charan");
        checkoutPage.enterLastName("Krishna");
        checkoutPage.enterPostalCode("500001");

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed(),
                "Order confirmation was not displayed"
        );
    }

    // TC12 - Verify Order Confirmation
    @Test(priority = 12)
    public void verifyOrderConfirmation() {

        loginAndAddProduct();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Charan");
        checkoutPage.enterLastName("Krishna");
        checkoutPage.enterPostalCode("500001");

        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html",
                "Order confirmation URL is incorrect"
        );

        Assert.assertTrue(
                checkoutPage.isOrderConfirmed(),
                "Order confirmation message was not displayed"
        );
    }
}