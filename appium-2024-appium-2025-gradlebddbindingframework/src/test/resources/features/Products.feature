@Products
Feature: Add products to cart

  Background:
    Given the user navigates to the registration form
    When the user selects "Argentina" from the country dropdown
    And the user selects "Male" from the gender dropdown
    When the user enters "Test User" as the name
    And the user clicks on the "Let’s Shop" button
    Then the user is redirected to the "Products" page

  Scenario: Add products to carts
    Given User I should select 0 index of product
    And user should select 0 index of product
    Then User should click on cart button and "Cart" title dispalys
