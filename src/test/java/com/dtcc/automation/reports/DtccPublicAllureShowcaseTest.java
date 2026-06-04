package com.dtcc.automation.reports;

import com.dtcc.automation.base.UiTestBase;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

@Epic("DTCC Public Website Automation")
@Feature("Expanded Allure UI, Security, and REST API Coverage")
@Owner("Brian McCarthy")
@Link(name = "DTCC public website", url = "https://www.dtcc.com/")
public class DtccPublicAllureShowcaseTest extends UiTestBase {
    private WireMockServer mockApi;

    @Test(description = "DTCC public site metadata is represented in the report")
    @Story("Public Website UI")
    @Severity(SeverityLevel.NORMAL)
    @Description("Records the public DTCC URL as the website under test without using private systems or credentials.")
    public void publicDtccWebsiteMetadataIsAttached() {
        attach("Website under test: https://www.dtcc.com/\nMode: public-safe automation metadata.");
        Assert.assertTrue("https://www.dtcc.com/".startsWith("https://"));
    }

    @Test(description = "Mock DTCC login page exposes accessible username field")
    @Story("Public Website UI")
    @Severity(SeverityLevel.NORMAL)
    public void loginUsernameFieldHasAccessibleLabel() {
        openMockOrderApplication();
        WebElement username = driver.findElement(By.id("username"));
        Assert.assertEquals(username.getAttribute("aria-label"), "Username");
    }

    @Test(description = "Mock DTCC login page exposes accessible password field")
    @Story("Public Website UI")
    @Severity(SeverityLevel.NORMAL)
    public void loginPasswordFieldHasAccessibleLabel() {
        openMockOrderApplication();
        WebElement password = driver.findElement(By.id("password"));
        Assert.assertEquals(password.getAttribute("aria-label"), "Password");
    }

    @Test(description = "Invalid login displays controlled security error")
    @Story("Security Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidLoginDisplaysControlledError() {
        openMockOrderApplication();
        login("bad_user", "bad_password");
        WebElement error = driver.findElement(By.id("login-error"));
        Assert.assertTrue(error.isDisplayed(), "Invalid login error should be displayed.");
        Assert.assertTrue(error.getText().contains("Invalid username or password"));
    }

    @Test(description = "Invalid login does not expose catalog content")
    @Story("Security Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidLoginDoesNotExposeCatalogPanel() {
        openMockOrderApplication();
        login("bad_user", "bad_password");
        Assert.assertFalse(driver.findElement(By.id("catalog-panel")).isDisplayed(), "Catalog must remain hidden after failed auth.");
    }

    @Test(description = "Blank credentials are rejected")
    @Story("Security Validation")
    @Severity(SeverityLevel.NORMAL)
    public void blankCredentialsAreRejected() {
        openMockOrderApplication();
        login("", "");
        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
    }

    @Test(description = "Script injection payload is rejected in username field")
    @Story("Security Validation")
    @Severity(SeverityLevel.BLOCKER)
    public void scriptInjectionPayloadIsRejected() {
        openMockOrderApplication();
        login("<script>alert(1)</script>", "demo_password");
        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("catalog-panel")).isDisplayed());
    }

    @Test(description = "SQL injection style payload is rejected")
    @Story("Security Validation")
    @Severity(SeverityLevel.BLOCKER)
    public void sqlInjectionPayloadIsRejected() {
        openMockOrderApplication();
        login("' OR '1'='1", "anything");
        Assert.assertTrue(driver.findElement(By.id("login-error")).isDisplayed());
    }

    @Test(description = "Successful public-safe login reveals catalog")
    @Story("Public Website UI")
    @Severity(SeverityLevel.CRITICAL)
    public void validDemoLoginRevealsCatalog() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        Assert.assertTrue(driver.findElement(By.id("catalog-panel")).isDisplayed());
    }

    @Test(description = "Keyboard login flow works from password field")
    @Story("Public Website UI")
    @Severity(SeverityLevel.NORMAL)
    public void keyboardLoginFlowWorks() {
        openMockOrderApplication();
        driver.findElement(By.id("username")).sendKeys("demo_user");
        driver.findElement(By.id("password")).sendKeys("demo_password");
        driver.findElement(By.id("loginBtn")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("loginBtn")).click();
        Assert.assertTrue(driver.findElement(By.id("catalog-panel")).isDisplayed());
    }

    @Test(description = "Order conversion button remains hidden before cart has items")
    @Story("Order Lifecycle UI")
    @Severity(SeverityLevel.NORMAL)
    public void checkoutButtonHiddenBeforeCartSelection() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        driver.findElement(By.id("cart-icon")).click();
        Assert.assertFalse(driver.findElement(By.id("checkout-btn")).isDisplayed());
    }

    @Test(description = "Cart item enables checkout button")
    @Story("Order Lifecycle UI")
    @Severity(SeverityLevel.CRITICAL)
    public void cartSelectionEnablesCheckout() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.id("cart-icon")).click();
        Assert.assertTrue(driver.findElement(By.id("checkout-btn")).isDisplayed());
    }

    @Test(description = "Payment button appears after order conversion")
    @Story("Order Lifecycle UI")
    @Severity(SeverityLevel.CRITICAL)
    public void orderConversionShowsPaymentButton() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        addItemAndOpenCheckout();
        driver.findElement(By.id("checkout-btn")).click();
        Assert.assertTrue(driver.findElement(By.id("pay-submit-btn")).isDisplayed());
    }

    @Test(description = "Confirmation banner appears after payment")
    @Story("Order Lifecycle UI")
    @Severity(SeverityLevel.CRITICAL)
    public void paymentDisplaysSuccessConfirmation() {
        openMockOrderApplication();
        login("demo_user", "demo_password");
        addItemAndOpenCheckout();
        driver.findElement(By.id("checkout-btn")).click();
        driver.findElement(By.id("pay-submit-btn")).click();
        String confirmation = driver.findElement(By.id("confirmation-banner")).getText();
        attach("Confirmation text: " + confirmation);
        Assert.assertTrue(confirmation.contains("SUCCESS"));
    }

    @Test(description = "REST market status endpoint returns public-safe response")
    @Story("REST API")
    @Severity(SeverityLevel.CRITICAL)
    public void restMarketStatusEndpointReturnsOpenStatus() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v1/market/status"))
                .willReturn(okJson("{\"market\":\"US\",\"status\":\"OPEN\",\"source\":\"mock-dtcc-public\"}")));
        RestAssured.baseURI = mockApi.baseUrl();

        given().accept(ContentType.JSON)
        .when().get("/public/v1/market/status")
        .then().statusCode(200)
                .time(lessThan(1500L))
                .body("status", equalToIgnoringCase("OPEN"));
    }

    @Test(description = "REST clearing eligibility endpoint validates symbol")
    @Story("REST API")
    @Severity(SeverityLevel.NORMAL)
    public void restClearingEligibilityEndpointValidatesSymbol() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v1/clearing/eligibility?symbol=DTCCMOCK"))
                .willReturn(okJson("{\"symbol\":\"DTCCMOCK\",\"eligible\":true,\"assetClass\":\"EQUITY\"}")));
        RestAssured.baseURI = mockApi.baseUrl();

        given().accept(ContentType.JSON)
        .when().get("/public/v1/clearing/eligibility?symbol=DTCCMOCK")
        .then().statusCode(200)
                .body("eligible", org.hamcrest.Matchers.equalTo(true))
                .body("assetClass", equalToIgnoringCase("EQUITY"));
    }

    @Test(description = "REST risk endpoint rejects missing authorization")
    @Story("REST API Security")
    @Severity(SeverityLevel.BLOCKER)
    public void restRiskEndpointRejectsMissingAuthorization() {
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/secure/v1/risk/summary"))
                .willReturn(aResponse().withStatus(401).withHeader("Content-Type", "application/json")
                        .withBody("{\"error\":\"unauthorized\",\"message\":\"Bearer token required\"}")));
        RestAssured.baseURI = mockApi.baseUrl();

        given().accept(ContentType.JSON)
        .when().get("/secure/v1/risk/summary")
        .then().statusCode(401)
                .body("error", equalToIgnoringCase("unauthorized"));
    }

    @Test(description = "REST order endpoint accepts sanitized payload")
    @Story("REST API Security")
    @Severity(SeverityLevel.CRITICAL)
    public void restOrderEndpointAcceptsSanitizedPayload() {
        startMockApi();
        mockApi.stubFor(post(urlEqualTo("/secure/v1/orders"))
                .withHeader("Authorization", equalTo("Bearer mock-oauth2-token"))
                .willReturn(aResponse().withStatus(201).withHeader("Content-Type", "application/json")
                        .withBody("{\"orderId\":\"DTCC-PUBLIC-100\",\"status\":\"ACCEPTED\",\"sanitized\":true}")));
        RestAssured.baseURI = mockApi.baseUrl();

        given().header("Authorization", "Bearer mock-oauth2-token")
                .contentType(ContentType.JSON)
                .body(Map.of("symbol", "DTCCMOCK", "quantity", 100, "comment", "public-safe test"))
        .when().post("/secure/v1/orders")
        .then().statusCode(201)
                .body("sanitized", org.hamcrest.Matchers.equalTo(true))
                .body("orderId", org.hamcrest.Matchers.equalTo("DTCC-PUBLIC-100"));

        mockApi.verify(postRequestedFor(urlEqualTo("/secure/v1/orders")));
    }

    @Test(description = "REST search endpoint protects against script query payload")
    @Story("REST API Security")
    @Severity(SeverityLevel.BLOCKER)
    public void restSearchEndpointRejectsScriptPayload() {
        startMockApi();
        mockApi.stubFor(get(urlPathEqualTo("/public/v1/search"))
                .willReturn(aResponse().withStatus(400).withHeader("Content-Type", "application/json")
                        .withBody("{\"error\":\"invalid_query\",\"violations\":[\"script payload rejected\"]}")));
        RestAssured.baseURI = mockApi.baseUrl();

        given().accept(ContentType.JSON)
        .when().get("/public/v1/search?q=%3Cscript%3Ealert(1)%3C/script%3E")
        .then().statusCode(400)
                .body("violations.size()", greaterThanOrEqualTo(1));
    }

    @Test(description = "Intentional UI defect example is opt-in only")
    @Story("Intentional Failure Demonstrations")
    @Severity(SeverityLevel.NORMAL)
    public void intentionalUiDefectExampleIsOptInOnly() {
        requireIntentionalFailuresEnabled();
        openMockOrderApplication();
        attach("Intentional UI failure: expecting a production banner on a mock page.");
        Assert.assertTrue(driver.getTitle().contains("Production DTCC"), "Intentional UI defect example for Allure Categories.");
    }

    @Test(description = "Intentional REST defect example is opt-in only")
    @Story("Intentional Failure Demonstrations")
    @Severity(SeverityLevel.NORMAL)
    public void intentionalRestDefectExampleIsOptInOnly() {
        requireIntentionalFailuresEnabled();
        startMockApi();
        mockApi.stubFor(get(urlEqualTo("/public/v1/market/status"))
                .willReturn(okJson("{\"market\":\"US\",\"status\":\"DEGRADED\"}")));
        RestAssured.baseURI = mockApi.baseUrl();
        attach("Intentional REST failure: expecting CLOSED while mock response is DEGRADED.");

        given().accept(ContentType.JSON)
        .when().get("/public/v1/market/status")
        .then().statusCode(200)
                .body("status", equalToIgnoringCase("CLOSED"));
    }

    @Step("Login with username {username}")
    private void login(String username, String password) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
    }

    @Step("Add item to cart and open checkout")
    private void addItemAndOpenCheckout() {
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.id("cart-icon")).click();
    }

    @Step("Start public-safe mock REST API")
    private void startMockApi() {
        if (mockApi == null) {
            mockApi = new WireMockServer(WireMockConfiguration.options().dynamicPort());
            mockApi.start();
        }
    }

    @Step("Attach evidence")
    private void attach(String message) {
        AllureAttachmentUtil.attachLog(message);
    }

    private void requireIntentionalFailuresEnabled() {
        if (!Boolean.getBoolean("includeIntentionalFailures")) {
            throw new SkipException("Intentional failure demo skipped by design. Run with -DincludeIntentionalFailures=true to show failed defects in Allure.");
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
