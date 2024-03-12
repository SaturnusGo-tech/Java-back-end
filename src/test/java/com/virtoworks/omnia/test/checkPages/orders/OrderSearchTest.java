package com.virtoworks.omnia.test.checkPages.orders;

import com.virtoworks.omnia.utils.actions.orders.OrderSearchActions;
import com.virtoworks.omnia.utils.global.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class to verify the functionality of searching orders with random keywords.
 */
public class OrderSearchTest {

    // Global configuration for setting up the environment before running tests.
    private static final Config config = new Config();
    // Actions to perform order searches.
    private final OrderSearchActions searchActions = new OrderSearchActions();

    /**
     * Set up configuration that applies to all tests in this class.
     */
    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    /**
     * Set up before each test, ensuring the application is navigated to the correct page.
     */
    @BeforeEach
    public void setUp() {
        config.setUp("account/orders");
    }

    /**
     * Test to verify that searching with random keywords correctly processes
     * either by finding products or resetting the search when no results are found.
     */
    @Test
    public void testSearchWithRandomKeywords() {
        // Attempt to search for orders using random keywords.
        searchActions.attemptSearchWithRandomKeywords();
    }
}
