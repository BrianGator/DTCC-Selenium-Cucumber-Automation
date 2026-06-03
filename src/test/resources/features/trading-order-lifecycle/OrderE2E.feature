Feature: End to End Order Lifecycle Validation

  Scenario Outline: Successful user login and complete checkout fulfillment workflow
    Given User is on the login screen
    When User authenticates with valid credentials "<username>" and "<password>"
    And User adds a specific item "<item>" from the catalogue to the shopping cart
    And User converts the cart contents into a finalized order
    And User submits a valid payment processing profile
    Then The order confirmation payload should display a successful transition status

    Examples:
      | username       | password    | item                |
      | sdet_dtcc_user | SecureP@ss1 | HighVolumeEquity_X1 |
