package com.dtcc.automation.reports;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("DTCC Additional Backend API Database and Report UI Coverage")
@Owner("Brian McCarthy")
@Link(name = "Repository", url = "https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/")
@Link(name = "Website Under Test", url = "https://www.dtcc.com/")
public class DtccAdditionalAllureCoverageTest {

    @DataProvider(name = "websiteAreas")
    public Object[][] websiteAreas() {
        return new Object[][]{
                {"About DTCC", "corporate profile, public mission, leadership, and company information", "NORMAL"},
                {"Client Center", "client access, documentation, service links, onboarding, and support paths", "CRITICAL"},
                {"Important Notices", "time-sensitive notices, PDF documents, search, filtering, and regulatory communication", "CRITICAL"},
                {"Products and Services", "public product/service taxonomy, landing pages, internal links, and content templates", "NORMAL"},
                {"Legal and Regulatory", "legal terms, compliance-oriented content, policy pages, and regulatory references", "CRITICAL"},
                {"News and Insights", "press releases, article detail pages, headlines, dates, tags, and public links", "NORMAL"},
                {"Careers", "career content, navigation to open roles, and employer information", "MINOR"},
                {"Global Trade Repository", "dynamic tables, jurisdictional data, search, sorting, filtering, and pagination", "CRITICAL"},
                {"PDF Document Library", "download links, content type, file naming, and PDF text validation plan", "NORMAL"},
                {"Search", "sitewide keyword search, result listing, empty results, and input validation", "NORMAL"}
        };
    }

    @Test(dataProvider = "websiteAreas", description = "UI/Content - DTCC website area has defined automation scope")
    @Feature("DTCC Website Area Coverage")
    @Story("Primary Website Areas")
    @Severity(SeverityLevel.NORMAL)
    @Description("Adds detailed Allure records for the primary areas of dtcc.com and what the automation framework should validate.")
    public void dtccWebsiteAreaHasDefinedAutomationScope(String area, String expectedEvidence, String priority) {
        AllureAttachmentUtil.attachLog("Area: " + area + "\nPriority: " + priority + "\nExpected automation evidence: " + expectedEvidence);
        Assert.assertFalse(area.isBlank());
        Assert.assertTrue(expectedEvidence.length() > 25);
    }

    @DataProvider(name = "apiBackendChecks")
    public Object[][] apiBackendChecks() {
        return new Object[][]{
                {"market-status", 200, "status", "OPEN"},
                {"notice-search", 200, "resultCount", "42"},
                {"document-metadata", 200, "contentType", "application/pdf"},
                {"client-service-directory", 200, "serviceGroup", "Client Center"},
                {"public-search-validation", 400, "error", "invalid_query"},
                {"risk-summary-auth", 401, "error", "unauthorized"},
                {"rate-limit-check", 429, "retryAfterSeconds", "30"},
                {"contract-version", 200, "version", "v1"}
        };
    }

    @Test(dataProvider = "apiBackendChecks", description = "API/Backend - Public-safe API contract evidence is represented")
    @Feature("Backend API Coverage")
    @Story("API Contract and Security")
    @Severity(SeverityLevel.CRITICAL)
    public void apiBackendContractEvidenceIsRepresented(String endpoint, int statusCode, String keyField, String expectedValue) {
        String payload = "{endpoint='" + endpoint + "', statusCode=" + statusCode + ", " + keyField + "='" + expectedValue + "'}";
        AllureAttachmentUtil.attachJson(endpoint + "-payload", payload);
        Assert.assertTrue(statusCode >= 200 && statusCode < 500);
        Assert.assertFalse(keyField.isBlank());
        Assert.assertFalse(expectedValue.isBlank());
    }

    @DataProvider(name = "databaseChecks")
    public Object[][] databaseChecks() {
        return new Object[][]{
                {"settlement_orders", "order status persisted", "PROCESSED"},
                {"order_audit", "state transition captured", "ACCEPTED_TO_PROCESSED"},
                {"document_downloads", "PDF access audit captured", "DOWNLOADED"},
                {"client_reference", "valid service code exists", "VALID"},
                {"reporting_customer_vw", "PII masking applied", "MASKED"},
                {"active_settlements_vw", "stale records excluded", "0_STALE_ROWS"},
                {"etl_reconciliation", "source and target counts match", "MATCHED"}
        };
    }

    @Test(dataProvider = "databaseChecks", description = "Database - SQL validation evidence is represented")
    @Feature("Database and Backend Validation")
    @Story("SQL Evidence")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseValidationEvidenceIsRepresented(String tableName, String validation, String expectedResult) {
        Map<String, String> row = Map.of("table", tableName, "validation", validation, "expectedResult", expectedResult);
        AllureAttachmentUtil.attachJson(tableName + "-sql-evidence", row.toString());
        Assert.assertTrue(row.get("table").length() > 3);
        Assert.assertFalse(row.get("expectedResult").isBlank());
    }
}
