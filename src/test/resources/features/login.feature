Feature: Login with invalid credentials

  Scenario: Valid email with incorrect password
    Given I am on the login page
    When I enter "vlad@gmail.com" into the email field
    And I enter "1234567" into the password field
    And I click the "Sign In" button
    Then I should see an error message about incorrect credentials
