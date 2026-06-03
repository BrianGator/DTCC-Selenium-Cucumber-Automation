# 16 - DTCC Public Website Page Coverage

This folder documents the public website automation tests for `https://www.dtcc.com/`.

## Test Strategy

The framework does not create a separate Selenium script for every URL. That would be brittle and hard to maintain. Instead, it uses two layers:

1. **Sitemap-driven HTTP coverage** - reads `https://www.dtcc.com/sitemap.xml` and validates public pages return successful HTTP responses.
2. **Selenium Page Object Model coverage** - validates high-value public templates such as Home, About, Client Center, News, Legal, and Products.

## Tests Added

- `DtccSitemapFullCoverageTest` - validates sitemap URLs return successful HTTP status codes.
- `DtccPublicSiteTemplateTest` - validates page body, branding, navigation, content landmarks, link count, and absence of obvious server errors.
- `DtccPublicWebsite.feature` - Cucumber BDD scenarios for business-readable website checks.

## Commands

```bash
mvn test -Dtest=DtccPublicSiteTemplateTest -Dheadless=true
mvn test -Dtest=DtccSitemapFullCoverageTest -Ddtcc.sitemap.maxPages=25
mvn test -DsuiteXmlFile=src/test/resources/testng-suites/dtcc-public-site-suite.xml
```

## Code Sample

```java
DtccHomePage home = new DtccHomePage(driver);
home.openHomePage();
Assert.assertTrue(home.hasDtccBranding());
Assert.assertTrue(home.hasPrimaryNavigation());
Assert.assertTrue(home.visibleLinkCount() > 10);
```
