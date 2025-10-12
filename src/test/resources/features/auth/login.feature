Feature: User authentication
  As a user I want to login and logout

  Background:
    Given user navigates to login page

  @smoke @auth
  Scenario: Successful login
    When user logs in with username "standard_user" and password "secret_sauce"
    Then home page should be displayed
    And user logs out

  @auth
  Scenario Outline: Invalid login attempts
    When user logs in with username "<user>" and password "<pass>"
    Then login error should be shown

    Examples:
      | user          | pass          |
      | invalid_user  | secret_sauce  |
      | standard_user | wrong_pass    |
