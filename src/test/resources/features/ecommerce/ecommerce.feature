Feature: E-commerce workflow
  @e2e
  Scenario: Add to cart and checkout
    Given user is on products page after login
    When user adds a backpack to cart and checks out
    Then order confirmation should be displayed
