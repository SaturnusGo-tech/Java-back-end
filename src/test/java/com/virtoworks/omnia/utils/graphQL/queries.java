package com.virtoworks.omnia.utils.graphQL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class queries {
    private void sendGraphQLRequestAndVerifyResponse(String filter) {
        String requestBody = String.format(
                "{\"query\":\"query Search($filter: String) { products(filter: $filter) { items { id } } }\",\"variables\":{\"filter\":\"%s\"}}",
                filter);

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("https://qa-opus.omniapartners.com/xapi/graphql")
                .then()
                .statusCode(200)
                .body("data.products.items.size()", greaterThan(0));
    }
}
