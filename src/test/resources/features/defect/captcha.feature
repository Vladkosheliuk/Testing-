Feature: CAPTCHA appears on submitting email without any prior interaction
  As a user
  I want to submit my email
  So that I can continue securely, but instead CAPTCHA is shown

  Scenario: CAPTCHA shows up when clicking "Continue Securely" after email input
    Given I open the Wiggle homepage
    And I go to the checkout or login page
    When I enter a valid email address
    And I click "Continue Securely"
    Then I should see a CAPTCHA challenge
