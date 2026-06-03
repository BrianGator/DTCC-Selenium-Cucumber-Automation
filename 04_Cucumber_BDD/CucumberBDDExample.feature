Feature: BDD business readable automation
  Scenario: Reviewer sees readable Given When Then coverage
    Given a product workflow exists
    When automated tests execute
    Then the report explains the result in business language
