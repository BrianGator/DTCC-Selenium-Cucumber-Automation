package com.dtcc.automation.reports;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class IntentionalFailureExampleTest {

    @Test
    public void intentionalFailureExampleForReportDemo() {
        if (!Boolean.getBoolean("includeIntentionalFailures")) {
            throw new SkipException("Intentional failure demo skipped. Run with -DincludeIntentionalFailures=true to generate failed-test examples.");
        }
        Assert.fail("Intentional failure example: expected order status PROCESSED but received PENDING_REVIEW.");
    }

    @Test
    public void intentionalApiContractFailureForReportDemo() {
        if (!Boolean.getBoolean("includeIntentionalFailures")) {
            throw new SkipException("Intentional failure demo skipped. Run with -DincludeIntentionalFailures=true to generate failed-test examples.");
        }
        Assert.assertEquals("application/xml", "application/json",
                "Intentional failure example: API returned the wrong content type.");
    }
}
