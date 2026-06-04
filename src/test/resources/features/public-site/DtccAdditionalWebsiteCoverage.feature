@publicSafe @dtccAreas
Feature: DTCC public website extended area coverage
  This feature describes public-safe BDD coverage for the primary areas of dtcc.com.
  It uses descriptive scenario evidence for portfolio reporting without using internal DTCC systems.

  Scenario Outline: Validate primary DTCC website area coverage scope
    Given I review the DTCC website area "<area>"
    When I document the expected public automation evidence "<evidence>"
    Then the BDD report should show the area status as "Covered"

    Examples:
      | area                        | evidence                                                                 |
      | About DTCC                  | corporate profile, leadership, public mission, and company information    |
      | Client Center               | client documentation, support paths, downloads, onboarding, and contacts   |
      | Important Notices           | notice search, category filters, dates, PDF links, and result listings     |
      | Products and Services       | product taxonomy, landing pages, internal links, and template rendering    |
      | Legal and Regulatory        | compliance content, legal links, terms, and regulatory references          |
      | News and Insights           | press releases, article pages, headlines, dates, and public links          |
      | Careers                     | career content, role navigation, employer information, and external links  |
      | Global Trade Repository     | dynamic tables, search, sorting, filtering, and pagination                 |
      | PDF Document Library        | downloadable documents, content type, filename patterns, and text checks   |
      | Search                      | keyword search, result pages, empty states, and input validation           |

  Scenario Outline: Validate public-safe backend/API behavior for report evidence
    Given I prepare a public-safe backend check named "<checkName>"
    When the mock API response status is <statusCode> and key field is "<keyField>"
    Then the BDD report should record backend evidence "<expectedResult>"

    Examples:
      | checkName                | statusCode | keyField              | expectedResult                |
      | market status            | 200        | status                | OPEN                          |
      | important notice search  | 200        | resultCount           | results returned              |
      | document metadata        | 200        | contentType           | application/pdf               |
      | client service directory | 200        | serviceGroup          | Client Center                 |
      | invalid search query     | 400        | error                 | invalid_query                 |
      | missing authorization    | 401        | error                 | unauthorized                  |
      | rate limit response      | 429        | retryAfterSeconds     | retry window provided         |
      | API contract version     | 200        | version               | v1 compatible                 |

  Scenario Outline: Validate database and reporting evidence for public-safe framework data
    Given I prepare a database validation named "<validationName>"
    When the SQL evidence references table or view "<tableName>"
    Then the BDD report should record database result "<expectedResult>"

    Examples:
      | validationName             | tableName              | expectedResult               |
      | settlement order persisted | settlement_orders      | PROCESSED                    |
      | order audit captured       | order_audit            | ACCEPTED_TO_PROCESSED        |
      | document access audited    | document_downloads     | DOWNLOADED                   |
      | reference data valid       | client_reference       | VALID                        |
      | PII masking applied        | reporting_customer_vw  | MASKED                       |
      | stale records excluded     | active_settlements_vw  | 0_STALE_ROWS                 |
      | reconciliation matched     | etl_reconciliation     | MATCHED                      |
