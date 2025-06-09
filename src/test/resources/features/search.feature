Feature: Product search

  Scenario Outline: Search for a product
    Given I am on the home page
    When I search for "swim"
    Then I should see search results header with text "Search Results"

    Example:
      | swim  |