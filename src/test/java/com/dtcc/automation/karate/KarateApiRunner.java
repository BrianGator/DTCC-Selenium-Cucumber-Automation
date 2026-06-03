package com.dtcc.automation.karate;

import com.intuit.karate.junit5.Karate;

public class KarateApiRunner {
    @Karate.Test
    Karate runClearingApiContractTests() {
        return Karate.run("classpath:karate/clearing-api-test.feature");
    }
}
