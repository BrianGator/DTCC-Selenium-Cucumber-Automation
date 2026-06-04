@publicSite
Feature: DTCC public website page coverage

  As an SDET publishing a portfolio automation framework
  I want to validate representative DTCC.com public website pages
  So that navigation, page rendering, and public content templates are covered safely

  Scenario: Validate the DTCC home page loads with basic navigation
    Given I open the DTCC public home page
    Then the DTCC page should load successfully
    And the page should show public site navigation

  Scenario Outline: Validate representative public content page templates
    Given I open the DTCC public page "<url>"
    Then the DTCC page should load successfully
    And the page should contain a visible content area

    Examples:
      | url                                  |
      | https://www.dtcc.com/about           |
      | https://www.dtcc.com/client-center   |
      | https://www.dtcc.com/news            |
      | https://www.dtcc.com/legal           |
      | https://www.dtcc.com/products        |
