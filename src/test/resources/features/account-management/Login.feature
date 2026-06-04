Feature: Account authentication validation

  Scenario Outline: Login screen accepts valid public-safe test credentials
    Given User is on the login screen
    When User authenticates with valid credentials "<username>" and "<password>"
    Then The catalogue should be available

    Examples:
      | username       | password      |
      | sdet_dtcc_user | SecureP@ss1   |
      | demo_user      | demo_password |
      | sdet_user      | SecureP@ss1   |

  Scenario Outline: Login screen rejects invalid or unsafe credentials
    Given User is on the login screen
    When User attempts login with invalid credentials "<username>" and "<password>"
    Then The login error should be displayed
    And The catalogue should remain hidden

    Examples:
      | username                    | password      |
      | bad_user                    | bad_password  |
      |                             |               |
      | <script>alert(1)</script>   | demo_password |
      | ' OR '1'='1                 | anything      |
