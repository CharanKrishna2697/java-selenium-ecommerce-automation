package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartTitle =
            By.cssSelector(".title");

    private By backpackProduct =
            By.id("item_4_title_link");

    private By backpackPrice =
            By.cssSelector(".inventory_item_price");

    private By checkoutButton =
            By.id("checkout");

    public CartPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartPageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartTitle)
        ).getText().equals("Your Cart");
    }

    public boolean isBackpackDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(backpackProduct)
        ).isDisplayed();
    }

    public String getBackpackPrice() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(backpackPrice)
        ).getText();
    }

    public void clickCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}