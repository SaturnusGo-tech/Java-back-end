package com.virtoworks.omnia.test.checkPages.filters;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.virtoworks.omnia.utils.global.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQlTest {
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
    public void graphqlTest() throws Exception {
        String query = """
                {
                    "query": "query SearchProducts { products(storeId: \\"opus\\", userId: \\"78e100ff-ea81-4aca-ad83-5f112e80fc77\\", currencyCode: \\"USD\\", cultureName: \\"en-US\\", filter: \\"new filter != correct\\", after: \\"\\", first: 16, sort: \\"\\", query: \\"\\", fuzzy: false, fuzzyLevel: 0, productIds: []) { totalCount items { id name } } }"
                }
                """;

        HttpResponse<String> response = Unirest.post(GRAPHQL_ENDPOINT)
                .header("Content-Type", "application/json")
                .body(query)
                .asString();

        System.out.println(((com.mashape.unirest.http.HttpResponse<?>) response).getBody());
    }
}


