package com.stv.factory.factorypages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FactoryPage {
    private WebDriver driver;

    public FactoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Input_EmailAddress")
    private WebElement emailField;

    @FindBy(id = "Login_Password")
    private WebElement passwordField;

    @FindBy(id = "LoginButton")
    private WebElement signInButton;

    @FindBy(css = ".dnnFormValidationSummary.field-validation-error")
    private WebElement errorMessage;

    @FindBy(id = "loginMenu")
    private WebElement accountIcon;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void clickAccountIcon() {
        accountIcon.click();
    }

    public void acceptCookiesIfPresent() {
        try {
            if (acceptCookiesButton.isDisplayed() && acceptCookiesButton.isEnabled()) {
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException | ElementNotInteractableException ignored) {}
    }
}
