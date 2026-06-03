package com.dtcc.automation.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DtccSitemapClient {
    private final String sitemapUrl;

    public DtccSitemapClient(String sitemapUrl) {
        this.sitemapUrl = sitemapUrl;
    }

    public List<String> fetchUrls(int maxPages) throws Exception {
        List<String> urls = new ArrayList<>();
        Document doc;
        try (InputStream stream = URI.create(sitemapUrl).toURL().openStream()) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
        }
        NodeList locs = doc.getElementsByTagName("loc");
        for (int i = 0; i < locs.getLength(); i++) {
            String url = locs.item(i).getTextContent().trim();
            if (url.startsWith("https://www.dtcc.com/") && !url.toLowerCase().endsWith(".pdf")) {
                urls.add(url);
            }
            if (maxPages > 0 && urls.size() >= maxPages) {
                break;
            }
        }
        return urls;
    }

    public PageProbeResult probe(String pageUrl) throws Exception {
        long start = System.currentTimeMillis();
        HttpURLConnection connection = (HttpURLConnection) new URL(pageUrl).openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 SDET Automation Portfolio Bot");
        int statusCode = connection.getResponseCode();
        String contentType = connection.getContentType();
        long elapsed = System.currentTimeMillis() - start;
        connection.disconnect();
        return new PageProbeResult(pageUrl, statusCode, contentType, elapsed);
    }
}
