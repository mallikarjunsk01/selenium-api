Feature: Navigation and menu
  @navigation
  Scenario: Open menu and logout
    Given I am logged in
    When I open the burger menu
    Then I can logout from menu
