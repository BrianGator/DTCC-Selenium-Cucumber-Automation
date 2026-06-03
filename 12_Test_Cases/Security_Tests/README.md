# Security Tests

Purpose: Validates HTTPS/security response headers such as HSTS and X-Content-Type-Options.

Executable implementation: `src/test/java/com/dtcc/automation/security/SecurityHeadersTest.java`

Run command example:

```bash
mvn test -Dtest=SecurityHeadersTest test
```

Reviewer note: this folder is a navigation-friendly copy/reference area. The executable Maven source lives under `src/test/java` so the project follows standard Java automation structure.
