Feature: Account authentication validation

  Scenario: Login screen accepts valid test credentials
    Given User is on the login screen
    When User authenticates with valid credentials "sdet_dtcc_user" and "SecureP@ss1"
    Then The order confirmation payload should display a successful transition status
