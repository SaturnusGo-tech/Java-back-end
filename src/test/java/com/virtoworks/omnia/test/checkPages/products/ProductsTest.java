package com.virtoworks.omnia.test.checkPages.products;

import com.virtoworks.omnia.utils.actions.catalog.products.ProductsActions;
import com.virtoworks.omnia.utils.global.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductsTest {

    private static final Config config = new Config();
    private final ProductsActions productsActions = new ProductsActions();

    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    @BeforeEach
    public void setUp() {
        config.setUp("catalog");
    }

    @Test
    public void testEnterRandomProductKeyword() {
        productsActions.enterRandomProductKeyword();

    }
}
