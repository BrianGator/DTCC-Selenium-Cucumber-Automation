# Full Selenium Automation Framework From Scratch

This folder explains the foundational Selenium architecture without relying on the higher-level Cucumber layer.

## Layers

1. `DriverManager` creates and owns WebDriver instances.
2. `BasePage` centralizes waits, clicks, text entry, and page synchronization.
3. Page Object classes expose business actions, not locator details.
4. Test classes call page actions and assert outcomes.
5. Listeners capture failure screenshots and support defect documentation.

## Interview talking point

A strong SDET does not write Selenium scripts as one large procedural file. The framework should separate locator management, reusable browser actions, assertions, test data, reporting, and CI execution so the suite is maintainable under frequent UI change.
