package com.dtcc.automation.security;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class SecurityHeadersTest {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    @Test
    public void publicSiteShouldReturnCommonSecurityHeaders() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getProperty("dtcc.home.url", "https://www.dtcc.com/")))
                .timeout(Duration.ofSeconds(20))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, java.util.List<String>> headers = response.headers().map();

        Assert.assertTrue(response.statusCode() < 400, "Home page should be reachable.");
        Assert.assertTrue(headers.keySet().stream().anyMatch(h -> h.equalsIgnoreCase("strict-transport-security")),
                "Public site should return HSTS to enforce HTTPS.");
        Assert.assertTrue(headers.keySet().stream().anyMatch(h -> h.equalsIgnoreCase("x-content-type-options")),
                "Public site should return X-Content-Type-Options to reduce MIME-sniffing risk.");
    }
}
