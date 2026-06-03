package com.dtcc.automation.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenTransactionTest {
    @DataProvider(name = "clearinghouseMatrix", parallel = true)
    public Object[][] getTransactionData() {
        return new Object[][]{
                {"DTCC_NY_01", 150000.00, "USD", "PROCESSED"},
                {"DTCC_LN_02", 4500000.50, "GBP", "PROCESSED"},
                {"DTCC_TK_03", 99000000.00, "JPY", "PENDING_HIGH_VALUE"}
        };
    }

    @Test(dataProvider = "clearinghouseMatrix", enabled = false, description = "Enable against a real or stubbed API environment.")
    public void validateTransactionMatrixExecution(String branch, double amount, String currency, String expectedStatus) {
        RestAssured.baseURI = System.getProperty("api.baseUrl", "https://example.test");
        String referenceId = "DATA-TXN-" + System.currentTimeMillis() + "-" + branch;
        Map<String, Object> payload = Map.of("transactionRef", referenceId, "clearingHouseCode", branch, "amount", amount, "currency", currency);
        given().contentType(ContentType.JSON).body(payload)
                .when().post("/clearing/orders")
                .then().statusCode(201).body("status", equalTo(expectedStatus)).body("transactionRef", equalTo(referenceId));
    }
}
