@Smoke
Feature: User Registration

  As a user
  I want to fill out the registration form
  So that I can proceed to the shopping page

  Background:
    Given the user navigates to the registration form
    When the user selects "Argentina" from the country dropdown
    And the user selects "Male" from the gender dropdown

  Scenario: Un-Successful form submission
    When the user enters "" as the name
    And the user clicks on the "Let’s Shop" button
    Then user gets error message "Please enter your name"

  Scenario: Successful form submission
    When the user enters "Test User" as the name
    And the user clicks on the "Let’s Shop" button
    Then the user is redirected to the "Products" page


