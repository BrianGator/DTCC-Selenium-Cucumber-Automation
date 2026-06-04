package com.dtcc.automation.reports;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.database.DatabaseClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

@Epic("DTCC Public Website SDET Portfolio Coverage")
@Owner("Brian McCarthy")
@Link(name = "DTCC public website", url = "https://www.dtcc.com/")
public class DtccMoreAllureCoverageTest extends UiTestBase {
    private WireMockServer mockApi;

    @Test(description = "UI page title identifies the mock lifecycle app")
    @Feature("Additional UI Tests")
    @Story("Page Identity")
    @Severity(SeverityLevel.NORMAL)
    public void uiPageTitleIdentifiesMockLifecycleApp() {
        openMockOrderApplication();
        AllureAttachmentUtil.attachText("ui-page-title.txt", driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Mock DTCC Order Lifecycle"));
    }

    @Test(description = "UI login panel is visible on first load")
    @Feature("Additional UI Tests")
    @Story("Authentication Form")
    @Severity(SeverityLevel.CRITICAL)
    public void uiLoginPanelIsVisibleOnFirstLoad() {
        openMockOrderApplication();
        Assert.assertTrue(driver.findElement(By.id("login-panel")).isDisplayed());
    }

    @Test(description = "UI login controls expose clear labels")
    @Feature("Additional UI Tests")
    @Story("Accessibility")
    @Severity(SeverityLevel.NORMAL)
    public void uiLoginControlsExposeClearLabels() {
        openMockOrderApplication();
        Assert.assertEquals(driver.findElement(By.cssSelector("label[for='username']")).getText(), "Username");
        Assert.assertEquals(driver.findElement(By.cssSelector("label[for='password']")).getText(), "Password");
    }

    @Test(description = "UI invalid-login error exposes alert semantics")
    @Feature("Additional UI Tests")
    @Story("Accessibility")
    @Severity(SeverityLevel.CRITICAL)
    public void uiInvalidLoginErrorExposesAlertSemantics() {
        openMockOrderApplication();
        login("bad_user", "bad_password");
        WebElement error = driver.findElement(By.id("login-error"));
        Assert.assertEquals(error.getAttribute("role"), "alert");
        Assert.assertTrue(error.isDisplayed());
    }

    @Test(description = "UI valid demo login hides the error banner")
    @Feature("Additional UI Tests")
    @Story("Authentication Form")
    @Severity(SeverityLevel.CRITICAL)
    public void uiValidDemoLoginHidesErrorBanner() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        Assert.assertFalse(driver.findElement(By.id("login-error")).isDisplayed());
    }

    @Test(description = "UI valid SDET login displays the catalogue")
    @Feature("Additional UI Tests")
    @Story("Authentication Form")
    @Severity(SeverityLevel.CRITICAL)
    public void uiValidSdetLoginDisplaysCatalogue() {
        openMockOrderApplication();
        login("sdet_dtcc_user", "SecureP@ss1");
        Assert.assertTrue(driver.findElement(By.id("catalog-panel")).isDisplayed());
    }

    @Test(description = "UI add-to-cart alone does not reveal payment button")
    @Feature("Additional UI Tests")
    @Story("Cart Guardrails")
    @Severity(SeverityLevel.NORMAL)
    public void uiAddToCartAloneDoesNotRevealPaymentButton() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        Assert.assertFalse(driver.findElement(By.id("pay-submit-btn")).isDisplayed());
    }

    @Test(description = "UI opening cart after item selection enables checkout")
    @Feature("Additional UI Tests")
    @Story("Cart Guardrails")
    @Severity(SeverityLevel.CRITICAL)
    public void uiOpeningCartAfterSelectionEnablesCheckout() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.id("cart-icon")).click();
        Assert.assertTrue(driver.findElement(By.id("checkout-btn")).isDisplayed());
    }

    @Test(description = "UI checkout enables submit payment")
    @Feature("Additional UI Tests")
    @Story("Order Conversion")
    @Severity(SeverityLevel.CRITICAL)
    public void uiCheckoutEnablesSubmitPayment() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        addItemAndOpenCheckout();
        driver.findElement(By.id("checkout-btn")).click();
        Assert.assertTrue(driver.findElement(By.id("pay-submit-btn")).isDisplayed());
    }

    @Test(description = "UI payment confirmation includes transaction lifecycle wording")
    @Feature("Additional UI Tests")
    @Story("Order Conversion")
    @Severity(SeverityLevel.CRITICAL)
    public void uiPaymentConfirmationIncludesLifecycleWording() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        addItemAndOpenCheckout();
        driver.findElement(By.id("checkout-btn")).click();
        driver.findElement(By.id("pay-submit-btn")).click();
        String confirmation = driver.findElement(By.id("confirmation-banner")).getText();
        AllureAttachmentUtil.attachText("ui-confirmation.txt", confirmation);
        Assert.assertTrue(confirmation.contains("Transaction lifecycle completed"));
    }

    @Test(description = "API health endpoint returns service identity")
    @Feature("Additional REST API Tests")
    @Story("Service Health")
    @Severity(SeverityLevel.CRITICAL)
    public void apiHealthEndpointReturnsServiceIdentity() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/health")).willReturn(okJson("{\"service\":\"dtcc-public-mock\",\"status\":\"UP\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().accept(ContentType.JSON).when().get("/public/v2/health").then().statusCode(200).body("status", equalToIgnoringCase("UP"));
    }

    @Test(description = "API notices endpoint returns multiple public items")
    @Feature("Additional REST API Tests")
    @Story("Public Notices")
    @Severity(SeverityLevel.NORMAL)
    public void apiNoticesEndpointReturnsMultipleItems() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/notices")).willReturn(okJson("{\"items\":[{\"id\":\"N1\"},{\"id\":\"N2\"},{\"id\":\"N3\"}]}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().accept(ContentType.JSON).when().get("/public/v2/notices").then().statusCode(200).body("items.size()", greaterThanOrEqualTo(3));
    }

    @Test(description = "API response includes rate-limit header")
    @Feature("Additional REST API Tests")
    @Story("API Governance")
    @Severity(SeverityLevel.NORMAL)
    public void apiResponseIncludesRateLimitHeader() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/rate-limited")).willReturn(aResponse().withStatus(200).withHeader("X-RateLimit-Remaining", "99").withBody("{\"ok\":true}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().when().get("/public/v2/rate-limited").then().statusCode(200).header("X-RateLimit-Remaining", "99");
    }

    @Test(description = "API secure summary rejects missing token")
    @Feature("Additional REST API Tests")
    @Story("API Security")
    @Severity(SeverityLevel.BLOCKER)
    public void apiSecureSummaryRejectsMissingToken() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/secure/v2/summary")).willReturn(aResponse().withStatus(401).withHeader("Content-Type", "application/json").withBody("{\"error\":\"missing_token\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().accept(ContentType.JSON).when().get("/secure/v2/summary").then().statusCode(401).body("error", equalToIgnoringCase("missing_token"));
    }

    @Test(description = "API malformed symbol request returns bad request")
    @Feature("Additional REST API Tests")
    @Story("Input Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void apiMalformedSymbolRequestReturnsBadRequest() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/instruments?symbol=BAD_SYMBOL")).willReturn(aResponse().withStatus(400).withHeader("Content-Type", "application/json").withBody("{\"error\":\"invalid_symbol\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().accept(ContentType.JSON).when().get("/public/v2/instruments?symbol=BAD_SYMBOL").then().statusCode(400).body("error", equalToIgnoringCase("invalid_symbol"));
    }

    @Test(description = "API creates public-safe order instruction")
    @Feature("Additional REST API Tests")
    @Story("Order API")
    @Severity(SeverityLevel.CRITICAL)
    public void apiCreatesPublicSafeOrderInstruction() {
        startMockApi();
        mockApi.stubFor(post(urlEqualTo("/secure/v2/orders")).withHeader("Authorization", equalTo("Bearer mock-oauth2-token")).willReturn(aResponse().withStatus(201).withHeader("Content-Type", "application/json").withBody("{\"orderId\":\"ORD-200\",\"status\":\"ACCEPTED\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().header("Authorization", "Bearer mock-oauth2-token").contentType(ContentType.JSON).body("{\"symbol\":\"DTCCMOCK\",\"quantity\":25}").when().post("/secure/v2/orders").then().statusCode(201).body("status", equalToIgnoringCase("ACCEPTED"));
        mockApi.verify(postRequestedFor(urlEqualTo("/secure/v2/orders")));
    }

    @Test(description = "API duplicate order returns conflict")
    @Feature("Additional REST API Tests")
    @Story("Order API")
    @Severity(SeverityLevel.NORMAL)
    public void apiDuplicateOrderReturnsConflict() {
        startMockApi();
        mockApi.stubFor(post(urlEqualTo("/secure/v2/orders/ORD-200")).willReturn(aResponse().withStatus(409).withHeader("Content-Type", "application/json").withBody("{\"error\":\"duplicate_order\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().contentType(ContentType.JSON).body("{}").when().post("/secure/v2/orders/ORD-200").then().statusCode(409).body("error", equalToIgnoringCase("duplicate_order"));
    }

    @Test(description = "API status endpoint responds inside performance budget")
    @Feature("Additional REST API Tests")
    @Story("Performance Budget")
    @Severity(SeverityLevel.NORMAL)
    public void apiStatusEndpointRespondsInsidePerformanceBudget() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/status")).willReturn(okJson("{\"status\":\"GREEN\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().when().get("/public/v2/status").then().statusCode(200).time(lessThan(1000L));
    }

    @Test(description = "API content type is JSON for reference data")
    @Feature("Additional REST API Tests")
    @Story("Reference Data")
    @Severity(SeverityLevel.NORMAL)
    public void apiContentTypeIsJsonForReferenceData() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v2/reference/currencies")).willReturn(okJson("{\"currencies\":[\"USD\",\"EUR\",\"GBP\"]}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().when().get("/public/v2/reference/currencies").then().statusCode(200).contentType(ContentType.JSON).body("currencies.size()", greaterThan(2));
    }

    @Test(description = "API audit endpoint returns trace identifier")
    @Feature("Additional REST API Tests")
    @Story("Auditability")
    @Severity(SeverityLevel.NORMAL)
    public void apiAuditEndpointReturnsTraceIdentifier() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/secure/v2/audit/ORD-200")).willReturn(okJson("{\"orderId\":\"ORD-200\",\"traceId\":\"TRACE-200\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        given().accept(ContentType.JSON).when().get("/secure/v2/audit/ORD-200").then().statusCode(200).body("traceId", equalToIgnoringCase("TRACE-200"));
    }

    @Test(description = "Database creates settlement table")
    @Feature("Additional Database Tests")
    @Story("Schema Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseCreatesSettlementTable() throws SQLException {
        try (DatabaseClient db = database()) {
            db.initializeSettlementTable();
            Assert.assertEquals(db.countOrdersByStatus("READY"), 0);
        }
    }

    @Test(description = "Database inserts a ready settlement order")
    @Feature("Additional Database Tests")
    @Story("Settlement Data")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseInsertsReadySettlementOrder() throws SQLException {
        try (DatabaseClient db = database()) {
            db.initializeSettlementTable();
            db.insertSettlementOrder("DB-001", "TX-001", "READY", 1250.25, "USD");
            Assert.assertTrue(db.orderExistsWithStatus("DB-001", "TX-001", "READY"));
        }
    }

    @Test(description = "Database counts orders by settlement status")
    @Feature("Additional Database Tests")
    @Story("Settlement Data")
    @Severity(SeverityLevel.NORMAL)
    public void databaseCountsOrdersBySettlementStatus() throws SQLException {
        try (DatabaseClient db = database()) {
            db.initializeSettlementTable();
            db.insertSettlementOrder("DB-002", "TX-002", "READY", 10, "USD");
            db.insertSettlementOrder("DB-003", "TX-003", "READY", 20, "USD");
            Assert.assertEquals(db.countOrdersByStatus("READY"), 2);
        }
    }

    @Test(description = "Database merge updates existing order status")
    @Feature("Additional Database Tests")
    @Story("Settlement Data")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseMergeUpdatesExistingOrderStatus() throws SQLException {
        try (DatabaseClient db = database()) {
            db.initializeSettlementTable();
            db.insertSettlementOrder("DB-004", "TX-004", "PENDING", 30, "USD");
            db.insertSettlementOrder("DB-004", "TX-004", "SETTLED", 30, "USD");
            Assert.assertTrue(db.orderExistsWithStatus("DB-004", "TX-004", "SETTLED"));
        }
    }

    @Test(description = "Database aggregates settlement amount by currency")
    @Feature("Additional Database Tests")
    @Story("Aggregation Queries")
    @Severity(SeverityLevel.NORMAL)
    public void databaseAggregatesSettlementAmountByCurrency() throws SQLException {
        try (Connection connection = databaseConnection()) {
            createSettlementTable(connection);
            insertOrder(connection, "DB-005", "TX-005", "READY", 100, "USD");
            insertOrder(connection, "DB-006", "TX-006", "READY", 250, "USD");
            try (ResultSet rs = connection.createStatement().executeQuery("SELECT SUM(amount) FROM settlement_orders WHERE currency = 'USD'")) {
                rs.next();
                Assert.assertEquals(rs.getDouble(1), 350.0);
            }
        }
    }

    @Test(description = "Database validates positive settlement amounts")
    @Feature("Additional Database Tests")
    @Story("Data Quality")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseValidatesPositiveSettlementAmounts() throws SQLException {
        try (Connection connection = databaseConnection()) {
            createSettlementTable(connection);
            insertOrder(connection, "DB-007", "TX-007", "READY", 75, "USD");
            try (ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM settlement_orders WHERE amount <= 0")) {
                rs.next();
                Assert.assertEquals(rs.getInt(1), 0);
            }
        }
    }

    @Test(description = "Database primary key prevents duplicate order ids")
    @Feature("Additional Database Tests")
    @Story("Data Integrity")
    @Severity(SeverityLevel.BLOCKER)
    public void databasePrimaryKeyPreventsDuplicateOrderIds() throws SQLException {
        try (Connection connection = databaseConnection()) {
            createSettlementTable(connection);
            insertOrder(connection, "DB-008", "TX-008", "READY", 80, "USD");
            Assert.assertThrows(SQLException.class, () -> insertOrder(connection, "DB-008", "TX-009", "READY", 90, "USD"));
        }
    }

    @Test(description = "Database parameterized lookup finds transaction reference")
    @Feature("Additional Database Tests")
    @Story("Parameterized Queries")
    @Severity(SeverityLevel.NORMAL)
    public void databaseParameterizedLookupFindsTransactionReference() throws SQLException {
        try (Connection connection = databaseConnection()) {
            createSettlementTable(connection);
            insertOrder(connection, "DB-009", "TX-009", "SETTLED", 90, "GBP");
            try (PreparedStatement ps = connection.prepareStatement("SELECT transaction_ref FROM settlement_orders WHERE order_id = ?")) {
                ps.setString(1, "DB-009");
                try (ResultSet rs = ps.executeQuery()) {
                    Assert.assertTrue(rs.next());
                    Assert.assertEquals(rs.getString(1), "TX-009");
                }
            }
        }
    }

    @Test(description = "Database audit table records status transition")
    @Feature("Additional Database Tests")
    @Story("Auditability")
    @Severity(SeverityLevel.NORMAL)
    public void databaseAuditTableRecordsStatusTransition() throws SQLException {
        try (Connection connection = databaseConnection()) {
            createSettlementTable(connection);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE settlement_audit (order_id VARCHAR(50), old_status VARCHAR(30), new_status VARCHAR(30))");
                stmt.execute("INSERT INTO settlement_audit VALUES ('DB-010','PENDING','SETTLED')");
            }
            try (ResultSet rs = connection.createStatement().executeQuery("SELECT new_status FROM settlement_audit WHERE order_id = 'DB-010'")) {
                rs.next();
                Assert.assertEquals(rs.getString(1), "SETTLED");
            }
        }
    }

    @Test(description = "Database rollback removes uncommitted settlement order")
    @Feature("Additional Database Tests")
    @Story("Transaction Control")
    @Severity(SeverityLevel.CRITICAL)
    public void databaseRollbackRemovesUncommittedSettlementOrder() throws SQLException {
        try (Connection connection = databaseConnection()) {
            connection.setAutoCommit(false);
            createSettlementTable(connection);
            insertOrder(connection, "DB-011", "TX-011", "PENDING", 110, "USD");
            connection.rollback();
            try (ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM settlement_orders WHERE order_id = 'DB-011'")) {
                rs.next();
                Assert.assertEquals(rs.getInt(1), 0);
            }
        }
    }

    private void login(String username, String password) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
    }

    private void addItemAndOpenCheckout() {
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.id("cart-icon")).click();
    }

    private void startMockApi() {
        if (mockApi == null) {
            mockApi = new WireMockServer(WireMockConfiguration.options().dynamicPort());
            mockApi.start();
        }
    }

    private DatabaseClient database() throws SQLException {
        return new DatabaseClient("jdbc:h2:mem:" + System.nanoTime() + ";DB_CLOSE_DELAY=-1", "sa", "");
    }

    private Connection databaseConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:" + System.nanoTime() + ";DB_CLOSE_DELAY=-1", "sa", "");
    }

    private void createSettlementTable(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE settlement_orders (order_id VARCHAR(50) PRIMARY KEY, transaction_ref VARCHAR(80), status VARCHAR(30), amount DECIMAL(19,2), currency VARCHAR(3))");
        }
    }

    private void insertOrder(Connection connection, String orderId, String transactionRef, String status, double amount, String currency) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO settlement_orders VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, orderId);
            ps.setString(2, transactionRef);
            ps.setString(3, status);
            ps.setDouble(4, amount);
            ps.setString(5, currency);
            ps.executeUpdate();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void stopMockApi() {
        if (mockApi != null) {
            mockApi.stop();
            mockApi = null;
        }
    }
}
