Feature: Cucumber order lifecycle example
  Scenario: Login, select catalogue item, add to cart, convert order, and pay
    Given User is on the login screen
    When User authenticates with valid credentials "sdet_dtcc_user" and "SecureP@ss1"
    And User adds a specific item "HighVolumeEquity_X1" from the catalogue to the shopping cart
    And User converts the cart contents into a finalized order
    And User submits a valid payment processing profile
    Then The order confirmation payload should display a successful transition status
