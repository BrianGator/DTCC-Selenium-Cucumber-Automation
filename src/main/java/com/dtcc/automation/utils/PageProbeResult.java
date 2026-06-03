package com.dtcc.automation.utils;

public class PageProbeResult {
    private final String url;
    private final int statusCode;
    private final String contentType;
    private final long responseTimeMs;

    public PageProbeResult(String url, int statusCode, String contentType, long responseTimeMs) {
        this.url = url;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.responseTimeMs = responseTimeMs;
    }

    public String getUrl() { return url; }
    public int getStatusCode() { return statusCode; }
    public String getContentType() { return contentType; }
    public long getResponseTimeMs() { return responseTimeMs; }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 400;
    }
}
