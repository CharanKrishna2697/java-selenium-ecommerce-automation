package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productsTitle =
            By.cssSelector(".title");

    private By backpackAddButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cartIcon =
            By.className("shopping_cart_link");

    private By cartBadge =
            By.className("shopping_cart_badge");

    private By backpackProduct =
            By.id("item_4_title_link");

    public ProductsPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductsPageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsTitle)
        ).getText().equals("Products");
    }

    public void addBackpackToCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(backpackAddButton)
        ).click();
    }

    public String getCartItemCount() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void clickCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );
    }

    public boolean isBackpackDisplayedInCart() {

        return wait.until(driver -> {

            try {

                return driver.findElement(backpackProduct).isDisplayed();

            } catch (StaleElementReferenceException e) {

                return false;
            }
        });
    }
}