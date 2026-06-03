package com.dtcc.automation.publicsite;

import com.dtcc.automation.utils.DtccSitemapClient;
import com.dtcc.automation.utils.PageProbeResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class DtccSitemapFullCoverageTest {
    @Test(description = "Validate DTCC XML sitemap pages return successful HTTP responses")
    public void validateEverySitemapPageRespondsSuccessfully() throws Exception {
        String sitemapUrl = System.getProperty("dtcc.sitemap.url", "https://www.dtcc.com/sitemap.xml");
        int maxPages = Integer.parseInt(System.getProperty("dtcc.sitemap.maxPages", "25"));
        DtccSitemapClient client = new DtccSitemapClient(sitemapUrl);
        List<String> urls = client.fetchUrls(maxPages);
        Assert.assertFalse(urls.isEmpty(), "Sitemap should provide at least one public page URL.");

        StringBuilder failures = new StringBuilder();
        for (String url : urls) {
            PageProbeResult result = client.probe(url);
            System.out.printf("[SITEMAP] %s -> %d in %d ms%n", result.getUrl(), result.getStatusCode(), result.getResponseTimeMs());
            if (!result.isSuccessful()) {
                failures.append(result.getUrl()).append(" returned ").append(result.getStatusCode()).append(System.lineSeparator());
            }
        }
        Assert.assertEquals(failures.toString(), "", "One or more sitemap URLs failed HTTP validation.\n" + failures);
    }
}
