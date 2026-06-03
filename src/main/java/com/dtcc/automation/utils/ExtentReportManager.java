package com.dtcc.automation.utils;

public final class ExtentReportManager {
    private ExtentReportManager() {}

    public static void logInfo(String message) {
        System.out.println("[REPORT] " + message);
    }
}
