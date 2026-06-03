# DTCC Public Website Test Case Matrix

| Test ID | Area | Test | Tool | Expected Result |
|---|---|---|---|---|
| DTCC-WEB-001 | Home | Load public home page | Selenium/TestNG | Page loads, DTCC branding visible, navigation present |
| DTCC-WEB-002 | Sitemap | Validate XML sitemap URLs | Java HTTP/TestNG | Public URLs return 2xx or 3xx responses |
| DTCC-WEB-003 | About | Validate content template | Selenium/TestNG | Body, heading/content landmark, links visible |
| DTCC-WEB-004 | Client Center | Validate content template | Selenium/TestNG | Body, heading/content landmark, links visible |
| DTCC-WEB-005 | News | Validate content template | Selenium/TestNG | Body, heading/content landmark, links visible |
| DTCC-WEB-006 | Legal | Validate content template | Selenium/TestNG | Body, heading/content landmark, links visible |
| DTCC-WEB-007 | Products | Validate content template | Selenium/TestNG | Body, heading/content landmark, links visible |
| DTCC-WEB-008 | BDD | Validate website behavior in Gherkin | Cucumber/TestNG | Feature scenarios pass and generate Cucumber report |
