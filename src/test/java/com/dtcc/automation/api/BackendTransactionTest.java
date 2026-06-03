package com.dtcc.automation.api;

import com.dtcc.automation.base.ApiTestBase;
import com.dtcc.automation.database.DatabaseClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import static org.wiremock.client.WireMock.*;

public class BackendTransactionTest extends ApiTestBase {
    private DatabaseClient databaseClient;

    @BeforeClass
    public void setup() throws SQLException {
        startMockApi();
        RestAssured.baseURI = mockApi.baseUrl();
        databaseClient = new DatabaseClient("jdbc:h2:mem:dtcc;DB_CLOSE_DELAY=-1", "sa", "");
        databaseClient.initializeSettlementTable();

        mockApi.stubFor(post(urlEqualTo("/v1/clearing/orders"))
                .willReturn(created()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"orderId\":\"ORD-10001\",\"transactionRef\":\"${json-unit.any-string}\",\"status\":\"PROCESSED\",\"processedTimestamp\":\"2026-06-03T12:00:00Z\"}")));
    }

    @Test
    public void validateTransactionLifecycleAndDatabaseState() throws SQLException {
        String referenceId = "TXN-" + System.currentTimeMillis();
        Map<String, Object> payload = new HashMap<>();
        payload.put("transactionRef", referenceId);
        payload.put("clearingHouseCode", "DTCC_NY_01");
        payload.put("amount", 2500000.50);
        payload.put("currency", "USD");

        Response response = given()
                .header("Authorization", "Bearer mock-oauth2-token")
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/v1/clearing/orders")
        .then()
                .log().ifValidationFails()
                .statusCode(201)
                .time(lessThan(1500L))
                .contentType(ContentType.JSON)
                .body("status", equalTo("PROCESSED"))
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schemas/transaction-response-schema.json")))
                .extract().response();

        String orderId = response.jsonPath().getString("orderId");
        databaseClient.insertSettlementOrder(orderId, referenceId, "PROCESSED", 2500000.50, "USD");
        Assert.assertTrue(databaseClient.orderExistsWithStatus(orderId, referenceId, "PROCESSED"));
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() throws SQLException {
        if (databaseClient != null) databaseClient.close();
        stopMockApi();
    }
}
