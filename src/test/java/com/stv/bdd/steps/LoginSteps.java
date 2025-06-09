package com.stv.bdd.steps;

import com.stv.factory.core.drivers.MyDriver;
import com.stv.factory.factorypages.FactoryPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginSteps {

    private WebDriver driver;
    private FactoryPage factoryPage;

    @Given("I am on the login page")
    public void iAmOnLoginPage() {
        driver = MyDriver.getDriver();
        driver.get(System.getProperty("base.url"));
        driver.manage().window().maximize();

        factoryPage = new FactoryPage(driver);
        factoryPage.acceptCookiesIfPresent();
        factoryPage.clickAccountIcon();
    }

    @When("I enter {string} into the email field")
    public void iEnterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(factoryPage.getEmailField()));
        factoryPage.getEmailField().clear();
        factoryPage.getEmailField().sendKeys(email);
    }


    @And("I enter {string} into the password field")
    public void iEnterPassword(String password) {
        factoryPage.getPasswordField().clear();
        factoryPage.getPasswordField().sendKeys(password);
    }

    @And("I click the \"Continue Securely\" button")
    public void iClickSignInButton() {
        factoryPage.getSignInButton().click();
    }

    @Then("I should see an error message about incorrect credentials")
    public void iSeeErrorMessage() {
        Assert.assertTrue(factoryPage.getErrorMessage().isDisplayed(), "Expected error message is not displayed!");
        MyDriver.quitDriver();
    }
}
