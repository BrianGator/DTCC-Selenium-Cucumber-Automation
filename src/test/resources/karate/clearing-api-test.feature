Feature: Settlement Clearing API Payload Integrity Validation

  Background:
    * url apiGatewayUrl
    * header Authorization = authToken
    * header Content-Type = 'application/json'

  Scenario: Verify high-volume equity clearance transaction schema and processing states
    Given path '/clearing/orders'
    * def uniqueRef = 'KARATE-TXN-' + java.lang.System.currentTimeMillis()
    And request { transactionRef: '#(uniqueRef)', clearingHouseCode: 'DTCC_NY_01', amount: 500000.00, currency: 'USD' }
    When method post
    Then status 201
    And match response.status == 'PROCESSED'
    And match response.transactionRef == uniqueRef
    And match response.orderId == '#notnull'
    And match response == { orderId: '#string', transactionRef: '#string', status: '#string', processedTimestamp: '#ignore' }
