Feature: Karate contract test example
  Scenario: Contract-style API validation
    * def status = 'PROCESSED'
    * match status == 'PROCESSED'
