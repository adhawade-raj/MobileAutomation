Feature: Visual snapshot

  @visual
  Scenario: Open app and take snapshot
    Given the app is launched
    When I take a Percy snapshot "Home Screen"
    Then the test completes

