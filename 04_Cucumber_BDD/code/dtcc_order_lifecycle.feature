Feature: DTCC-style order lifecycle BDD coverage
  As an SDET
  I want business-readable scenarios
  So that automation is traceable to expected user behavior

  Scenario: Valid user completes an order lifecycle
    Given User is on the login screen
    When User authenticates with valid credentials "demo_user" and "demo_password"
    And User adds a specific item "HighVolumeEquity_X1" from the catalogue to the shopping cart
    And User converts the cart contents into a finalized order
    And User submits a valid payment processing profile
    Then The order confirmation payload should display a successful transition status

  Scenario: Invalid user cannot access the catalogue
    Given User is on the login screen
    When User attempts login with invalid credentials "invalid_user" and "bad_password"
    Then The login error should be displayed
    And The catalogue should remain hidden
