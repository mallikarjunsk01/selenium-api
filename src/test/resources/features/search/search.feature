Feature: Search functionality
  @search
  Scenario Outline: Search from JSON data
    Given user opens DuckDuckGo home page
    When user searches for keyword from json "<key>"
    Then results page should contain "<contains>"

    Examples:
      | key   | contains |
      | java  | java     |
      | selenium | selenium |
