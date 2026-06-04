Feature: End to End Order Lifecycle Validation

  Scenario Outline: Successful user login and complete checkout fulfillment workflow
    Given User is on the login screen
    When User authenticates with valid credentials "<username>" and "<password>"
    And User adds a specific item "<item>" from the catalogue to the shopping cart
    And User converts the cart contents into a finalized order
    And User submits a valid payment processing profile
    Then The order confirmation payload should display a successful transition status

    Examples:
      | username       | password      | item                |
      | sdet_dtcc_user | SecureP@ss1   | HighVolumeEquity_X1 |
      | demo_user      | demo_password | HighVolumeEquity_X1 |
      | sdet_user      | SecureP@ss1   | HighVolumeEquity_X1 |

  Scenario: Checkout cannot start before an item is selected
    Given User is on the login screen
    When User authenticates with valid credentials "demo_user" and "demo_password"
    And User opens the cart before selecting an item
    Then The checkout action should remain unavailable

  Scenario: Cart selection makes checkout available
    Given User is on the login screen
    When User authenticates with valid credentials "demo_user" and "demo_password"
    And User adds a specific item "HighVolumeEquity_X1" from the catalogue to the shopping cart
    And User opens the cart before selecting an item
    Then The checkout action should become available
