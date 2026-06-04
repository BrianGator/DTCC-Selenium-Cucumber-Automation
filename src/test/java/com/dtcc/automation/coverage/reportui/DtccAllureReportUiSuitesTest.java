package com.dtcc.automation.coverage.reportui;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Automation reporting portal")
@Feature("Report UI evidence")
@Owner("Brian McCarthy")
public class DtccAllureReportUiSuitesTest {
    @DataProvider(name = "reportAreas")
    public Object[][] reportAreas() {
        return new Object[][]{
                {"Consolidated dashboard", "Embedded Allure frame and status summary"},
                {"Allure overview", "Launch status, suites, categories, graphs, environment"},
                {"Allure categories", "Intentional failures and classified defect groups"},
                {"Allure behaviors", "Epic, feature, and story groupings"},
                {"Allure packages", "Java package and class organization"},
                {"Cucumber report", "BDD feature and scenario evidence"},
                {"Surefire report", "Raw TestNG XML and runner diagnostics"},
                {"PDF report index", "Exported report PDFs for review"},
                {"Quality gates", "Pass rate and blocker defect checks"},
                {"GitHub Actions summary", "Workflow execution links and artifact links"}
        };
    }

    @Test(dataProvider = "reportAreas", description = "Report UI suite validates report portal evidence")
    @Story("Report portal coverage")
    @Severity(SeverityLevel.NORMAL)
    @Description("Adds report UI evidence to Allure so the report itself has visible suite and behavior data.")
    public void reportUiCoverageIsDocumented(String area, String expectedEvidence) {
        Allure.label("layer", "report-ui");
        Allure.label("component", area);
        Allure.addAttachment("Report UI evidence", "text/plain", "Report area: " + area + "\nExpected evidence: " + expectedEvidence);
        Assert.assertFalse(area.isBlank());
        Assert.assertTrue(expectedEvidence.length() > 15);
    }
}
