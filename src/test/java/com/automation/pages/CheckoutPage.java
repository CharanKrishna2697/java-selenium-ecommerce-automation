package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutTitle =
            By.cssSelector(".title");

    private By firstName =
            By.id("first-name");

    private By lastName =
            By.id("last-name");

    private By postalCode =
            By.id("postal-code");

    private By continueButton =
            By.id("continue");

    private By finishButton =
            By.id("finish");

    private By orderConfirmation =
            By.cssSelector(".complete-header");

    private By overviewTitle =
            By.cssSelector(".title");

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isCheckoutPageDisplayed() {

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        checkoutTitle,
                        "Checkout: Your Information"
                )
        );
    }

    public void enterFirstName(String value) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        );

        element.clear();
        element.sendKeys(value);
    }

    public void enterLastName(String value) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastName)
        );

        element.clear();
        element.sendKeys(value);
    }

    public void enterPostalCode(String value) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCode)
        );

        element.clear();
        element.sendKeys(value);
    }

    public void clickContinue() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        wait.until(
                ExpectedConditions.urlContains("checkout-step-two")
        );

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        overviewTitle,
                        "Checkout: Overview"
                )
        );
    }

    public boolean isCheckoutOverviewDisplayed() {

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        overviewTitle,
                        "Checkout: Overview"
                )
        );
    }

    public void clickFinish() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        wait.until(
                ExpectedConditions.urlContains("checkout-complete")
        );
    }

    public boolean isOrderConfirmed() {

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        orderConfirmation,
                        "Thank you for your order!"
                )
        );
    }
}