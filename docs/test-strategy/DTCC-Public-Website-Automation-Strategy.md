# DTCC Public Website Automation Strategy

## Objective

Provide a public-safe Selenium/Cucumber automation framework for `dtcc.com` that demonstrates how an SDET validates an enterprise website without relying on internal systems, private credentials, or protected test data.

## Coverage Model

- **Smoke coverage:** home page, navigation, branding, page body, links, and search/navigation availability.
- **Template coverage:** About, Client Center, News, Legal, Products, and other representative content templates.
- **Sitemap coverage:** XML sitemap URLs checked with lightweight HTTP validation.
- **Regression coverage:** TestNG suites and Jenkins/GitHub Actions jobs run public-site tests automatically.

## Why Not Script Every Page Manually?

A large public website can contain hundreds or thousands of URLs. Manually scripting every URL creates duplicate tests and high maintenance cost. A better enterprise SDET strategy is to validate every URL at the HTTP/sitemap layer, then use Selenium against representative page templates and high-risk user flows.

## Risk Areas Covered

- Broken public pages
- Missing navigation/header/body content
- Pages returning non-2xx/3xx responses
- Public page templates that fail to render
- Obvious server error text
- Regression risk across key sections

## Reviewer Talking Point

This shows an SDET can balance coverage, maintainability, runtime, and risk. Full page coverage belongs in a sitemap/HTTP test. Browser-level coverage belongs in reusable Page Objects and representative functional flows.

## Selenide Enhancement for DTCC.com

Selenide is the recommended Java/Selenium wrapper for reducing public-site UI automation flakiness. Use it for high-value DTCC.com template checks where asynchronous rendering, AJAX updates, menus, and page-load variability can make raw Selenium waits brittle.

Recommended priority:

1. Client Center - validates navigation, public support content, login redirects, and page structure.
2. Important Notices - validates search/filter behavior, result rendering, pagination, PDF links, and date/content patterns.
3. Legal and Regulatory pages - validates document lists, filtering, and download link integrity.
4. Global Trade Repository pages - validates dynamic table loading and asynchronous state changes. Selenide should be preferred for normal interactions; raw Selenium FluentWait utilities can remain available for specialized table polling.

PDF handling should use a PDF parser such as Apache PDFBox to validate downloaded PDF titles, expected terms, page count, and file integrity.
