Feature: Form submission and validation
  @forms
  Scenario: Submit practice form
    Given user opens DemoQA practice form
    When user fills the form with first name "John", last name "Doe", email "john.doe@example.com"
    And submits the form
    Then a submission modal should appear
