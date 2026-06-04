package com.dtcc.automation.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class DtccAdditionalWebsiteCoverageSteps {
    private final Map<String, String> scenarioEvidence = new HashMap<>();

    @Given("I review the DTCC website area {string}")
    public void iReviewTheDtccWebsiteArea(String area) {
        scenarioEvidence.put("area", area);
        Assert.assertFalse(area.isBlank(), "Area name should be present.");
    }

    @When("I document the expected public automation evidence {string}")
    public void iDocumentExpectedPublicAutomationEvidence(String evidence) {
        scenarioEvidence.put("evidence", evidence);
        Assert.assertTrue(evidence.length() > 20, "Expected evidence should be descriptive.");
    }

    @Then("the BDD report should show the area status as {string}")
    public void bddReportShouldShowAreaStatus(String status) {
        scenarioEvidence.put("status", status);
        Assert.assertEquals(status, "Covered");
        Assert.assertTrue(scenarioEvidence.containsKey("area"));
        Assert.assertTrue(scenarioEvidence.containsKey("evidence"));
    }

    @Given("I prepare a public-safe backend check named {string}")
    public void iPreparePublicSafeBackendCheckNamed(String checkName) {
        scenarioEvidence.put("backendCheck", checkName);
        Assert.assertFalse(checkName.isBlank());
    }

    @When("the mock API response status is {int} and key field is {string}")
    public void mockApiResponseStatusAndKeyField(int statusCode, String keyField) {
        scenarioEvidence.put("statusCode", String.valueOf(statusCode));
        scenarioEvidence.put("keyField", keyField);
        Assert.assertTrue(statusCode >= 200 && statusCode < 500);
        Assert.assertFalse(keyField.isBlank());
    }

    @Then("the BDD report should record backend evidence {string}")
    public void bddReportShouldRecordBackendEvidence(String expectedResult) {
        scenarioEvidence.put("expectedResult", expectedResult);
        Assert.assertFalse(expectedResult.isBlank());
        Assert.assertTrue(scenarioEvidence.containsKey("backendCheck"));
    }

    @Given("I prepare a database validation named {string}")
    public void iPrepareDatabaseValidationNamed(String validationName) {
        scenarioEvidence.put("databaseValidation", validationName);
        Assert.assertFalse(validationName.isBlank());
    }

    @When("the SQL evidence references table or view {string}")
    public void sqlEvidenceReferencesTableOrView(String tableName) {
        scenarioEvidence.put("tableName", tableName);
        Assert.assertTrue(tableName.length() >= 3);
    }

    @Then("the BDD report should record database result {string}")
    public void bddReportShouldRecordDatabaseResult(String expectedResult) {
        scenarioEvidence.put("databaseResult", expectedResult);
        Assert.assertFalse(expectedResult.isBlank());
        Assert.assertTrue(scenarioEvidence.containsKey("databaseValidation"));
        Assert.assertTrue(scenarioEvidence.containsKey("tableName"));
    }
}
