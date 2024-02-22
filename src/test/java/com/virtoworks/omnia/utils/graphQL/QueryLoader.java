package com.virtoworks.omnia.utils.graphQL;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryLoader.class);

    /**
     * Sends a GraphQL request with the specified filter and verifies the response.
     *
     * @param filter The filter to apply in the GraphQL query.
     * @param queryFilePath The file path of the GraphQL query template.
     */
    public void sendGraphQLRequestAndVerifyResponse(String filter, String queryFilePath) {
        String queryTemplate;
        try {
            queryTemplate = loadGraphQLQuery(queryFilePath);
        } catch (IOException e) {
            LOGGER.error("Error loading GraphQL query from file: {}", queryFilePath, e);
            return; // Or handle the exception according to some policy, i will get it latter
        }

        String requestBody = String.format(queryTemplate, filter);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://qa-opus.omniapartners.com/xapi/graphql")
                .then()
                .statusCode(200)
                .body("data.products.items.size()", greaterThan(0));
    }

    /**
     * Loads a GraphQL query from a file located at the specified file path.
     * Uses UTF-8 encoding by default.
     *
     * @param filePath The relative or absolute path to the file containing the GraphQL query.
     * @return The content of the file as a String.
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public String loadGraphQLQuery(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Failed to load GraphQL query from file: {}", filePath, e);
            throw e;
        }
    }
}
