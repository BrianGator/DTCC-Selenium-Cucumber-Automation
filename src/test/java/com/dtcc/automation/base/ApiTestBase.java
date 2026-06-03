package com.dtcc.automation.base;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class ApiTestBase {
    protected WireMockServer mockApi;

    protected void startMockApi() {
        mockApi = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        mockApi.start();
    }

    protected void stopMockApi() {
        if (mockApi != null) mockApi.stop();
    }
}
