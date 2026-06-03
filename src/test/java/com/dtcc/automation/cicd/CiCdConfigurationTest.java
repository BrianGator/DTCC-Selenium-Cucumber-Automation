package com.dtcc.automation.cicd;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class CiCdConfigurationTest {
    @Test(description = "Jenkinsfile contains the core stages needed for CI/CD test execution and report publishing")
    public void jenkinsfileContainsRequiredPipelineStages() throws Exception {
        String jenkinsfile = Files.readString(Path.of("Jenkinsfile"));
        Assert.assertTrue(jenkinsfile.contains("stage('Compile Testing Target Artifacts')") || jenkinsfile.contains("Compile"));
        Assert.assertTrue(jenkinsfile.contains("mvn"), "Pipeline should invoke Maven test execution.");
        Assert.assertTrue(jenkinsfile.contains("junit"), "Pipeline should publish JUnit/TestNG XML results.");
    }

    @Test(description = "GitHub Actions workflow exists for external repository validation")
    public void githubActionsWorkflowExists() {
        Assert.assertTrue(Files.exists(Path.of(".github/workflows/ci-cd-pipeline.yml")));
    }
}
