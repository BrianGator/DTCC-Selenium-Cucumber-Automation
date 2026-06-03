package com.dtcc.automation.performance;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class PageLoadPerformanceTest {

    @Test
    public void publicHomePageShouldRespondWithinPortfolioThreshold() throws Exception {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getProperty("dtcc.home.url", "https://www.dtcc.com/")))
                .timeout(Duration.ofSeconds(20))
                .GET()
                .build();

        long start = System.currentTimeMillis();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        long elapsedMs = System.currentTimeMillis() - start;

        Assert.assertTrue(response.statusCode() < 400, "Home page should respond successfully.");
        Assert.assertTrue(elapsedMs < 5000, "Public page response exceeded 5 second smoke threshold: " + elapsedMs + "ms");
    }
}
