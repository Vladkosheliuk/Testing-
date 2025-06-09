package com.stv.bdd.steps.defect;



import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import static org.junit.Assert.*;

import com.stv.factory.core.drivers.MyDriver;
import com.stv.factory.core.utils.PropertyReader;

import java.time.Duration;

public class CaptchaSteps {

    private WebDriver driver = MyDriver.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private String baseUrl = PropertyReader.getTestProperty("base.url");

    @Given("I open the Wiggle homepage")
    public void iOpenTheWiggleHomepage() {
        driver.get(baseUrl);
        acceptCookies();
    }

    private void acceptCookies() {
        try {
            WebElement allowAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            allowAllButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie consent already handled or not present.");
        }
    }

    @And("I go to the checkout or login page")
    public void iGoToTheCheckoutOrLoginPage() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginMenu")));
        loginButton.click();
    }

    @When("I enter a valid email address")
    public void iEnterAValidEmailAddress() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Input_EmailAddress")));
        emailField.click();
        String email = "vladkoseluk22@gmail.com";

        Actions actions = new Actions(driver);
        for (char ch : email.toCharArray()) {
            actions.sendKeys(String.valueOf(ch)).perform();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    @And("I click {string}")
    public void iClickButton(String buttonText) {
        if (buttonText.equals("Continue Securely")) {
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("emailSubmit")));
            continueButton.click();
        } else {
            throw new IllegalArgumentException("Unsupported button text: " + buttonText);
        }
    }

    @Then("I should see a CAPTCHA challenge")
    public void iShouldSeeACaptchaChallenge() {
        boolean captchaDetected = driver.findElements(By.cssSelector("iframe[title*='captcha'], div[class*='captcha']")).size() > 0;
        assertTrue("Expected CAPTCHA challenge to appear", captchaDetected);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
