@Smoke2
Feature: User FormFilling

  As a user
  I want to fill out the registration form
  So that I can proceed to the shopping page

  Background:
    Given the user navigates to the formFill page
    When the user selects country as "Argentina"
    And the user selects gender as "Male"

  Scenario Outline: Form submission with different names
    When the user enters "<name>" in the Name field
    And the user clicks on the button "Let’s Shop"
    Then the user should see the message "<errorMessage>"

    Examples:
      | name        | errorMessage               |
      | ""          | "Please enter your name"   |
      | "Test User" | "Product"                   |
