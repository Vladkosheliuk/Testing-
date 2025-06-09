package com.stv.factory.factorypages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtSearch")
    private WebElement searchInput;

    @FindBy(css = "h1")
    private WebElement searchHeader;

    @FindBy(css = "span.productdescriptionname")
    private List<WebElement> productList;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    public void enterSearchQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(query + Keys.ENTER);
    }

    public String getSearchHeaderText() {
        return wait.until(ExpectedConditions.visibilityOf(searchHeader)).getText().trim();
    }

    public String getFirstProductName() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productList)).get(0).getText().trim();
    }

    public void acceptCookiesIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        } catch (TimeoutException ignored) {}
    }
}
