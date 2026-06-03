package com.dtcc.automation.pdf;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class PdfDownloadValidationTest {

    @Test
    public void configuredPublicPdfShouldBeDownloadableWhenUrlIsProvided() throws Exception {
        String pdfUrl = System.getProperty("dtcc.sample.pdf.url", "");
        if (pdfUrl.isBlank()) {
            System.out.println("Skipping PDF download validation because -Ddtcc.sample.pdf.url was not provided.");
            return;
        }

        HttpURLConnection connection = (HttpURLConnection) new URL(pdfUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        Assert.assertEquals(connection.getResponseCode(), 200, "PDF should return HTTP 200.");
        Assert.assertTrue(connection.getContentType().toLowerCase().contains("pdf"),
                "Downloaded resource should identify as PDF content.");
    }
}
