package com.stv.bdd.steps;

import com.stv.factory.core.drivers.MyDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;


import java.time.Duration;

public class SearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the home page")
    public void iAmOnHomePage() {
        driver = MyDriver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get(System.getProperty("base.url"));
        driver.manage().window().maximize();

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            acceptCookies.click();
        } catch (TimeoutException e) {
        }
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearch")));
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
    }

    @Then("I should see search results header with text {string}")
    public void iShouldSeeSearchResultsHeaderWithText(String expectedText) {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        String actualText = header.getText().trim();
        Assert.assertEquals(actualText, expectedText, "Search result header text does not match!");
    }


    @Then("I should see the first product as {string}")
    public void iSeeFirstProduct(String expectedProductName) {
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("span.productdescriptionname"))).get(0);
        String actualProductName = firstProduct.getText().trim();

        Assert.assertEquals(actualProductName, expectedProductName, "First product name doesn't match!");
    }


    @After
    public void tearDown() {
        MyDriver.quitDriver();
    }

}
