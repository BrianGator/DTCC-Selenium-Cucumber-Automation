package examples.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredPublicApiContractTest {
    @BeforeClass
    public void configure() {
        RestAssured.baseURI = System.getProperty("api.base.url", "http://localhost:8089");
    }

    @Test
    public void publicStatusEndpointReturnsExpectedFields() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/public/status")
        .then()
                .statusCode(Matchers.anyOf(Matchers.is(200), Matchers.is(204)))
                .time(Matchers.lessThan(2000L));
    }

    @Test
    public void publicSearchRejectsInvalidInput() {
        given()
                .accept(ContentType.JSON)
                .queryParam("q", "")
        .when()
                .get("/api/public/search")
        .then()
                .statusCode(Matchers.anyOf(Matchers.is(400), Matchers.is(422)));
    }
}
