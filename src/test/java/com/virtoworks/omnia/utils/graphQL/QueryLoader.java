package com.virtoworks.omnia.utils.graphQL;

import com.virtoworks.omnia.utils.global.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class QueryLoader {
    private static final String GRAPHQL_ENDPOINT = "https://qa-opus.omniapartners.com/xapi/graphql";
    private static final Config config = new Config();

    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    @BeforeEach
    public void setUp() {
        config.setUp("catalog");
    }

    @Test
    public void graphqlTest() {
        String queryWithVariables = "query SearchProducts {"
                + " products("
                + " storeId: \"opus\","
                + " userId: \"78e100ff-ea81-4aca-ad83-5f112e80fc77\","
                + " currencyCode: \"USD\","
                + " cultureName: \"en-US\","
                + " filter: \"new filter != correct\","
                + " after: \"\","
                + " first: 16,"
                + " sort: \"\","
                + " query: \"\","
                + " fuzzy: false,"
                + " fuzzyLevel: 0,"
                + " productIds: []"
                + " ) {"
                + " totalCount"
                + " items {"
                + " id"
                + " name"
                + " }"
                + " }"
                + "}";

        String requestBody = String.format("{ \"query\": \"%s\" }", queryWithVariables.replace("\"", "\\\""));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(GRAPHQL_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response: " + response.asPrettyString());
    }

}
