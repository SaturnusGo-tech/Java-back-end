package com.virtoworks.omnia.test.checkPages.orders;

import com.virtoworks.omnia.utils.actions.orders.OrderSearchActions;
import com.virtoworks.omnia.utils.global.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.sleep;

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
     * This test demonstrates the automated process of searching for products using random keywords,
     * opening filters, configuring dropdowns, and checkboxes based on predefined settings.
     * It showcases how to interact with web elements using Selenide and handle conditional logic
     * based on the existence of elements and their states.
     */
    @Test
    public void testSearchWithRandomKeywords() {
        // Log the start of the test
        System.out.println("Test started: Searching with random keywords");
        // Attempt to search for products using a randomly selected keyword.
        searchActions.attemptSearchWithRandomKeywords();
        /*
        Log the action of opening filters
         Open the filters section to reveal additional search options.
         */
        System.out.println("Filters are opened");
        searchActions.openUpFilters();

        /*
         Introduce a delay to ensure filters are fully loaded and ready for interaction.
         Note: In real tests, prefer using explicit waits over fixed delays.
         */
        System.out.println("Waiting 10 seconds before configuring dropdowns");
        sleep(10000);

        /*
        Log the configuration of dropdowns
         */
        System.out.println("Configuring dropdowns");
        // Configure dropdowns based on predefined settings (true or false).
        searchActions.setBuilderDropdownByIndex();

        /*
          Prepare settings for checkboxes.
          Each entry represents a checkbox and whether it should be checked (true) or unchecked (false).
         */
        Map<String, Boolean> settings = new HashMap<>();
        settings.put("Approval needed", true);
        settings.put("Approved", false);
        settings.put("Cancelled", true);
        settings.put("Completed", false);
        settings.put("Confirmed", true);
        settings.put("New", false);
        settings.put("Payment Required", true);
        settings.put("Pending", false);
        settings.put("Rejected", true);

        /*
         Log the configuration of checkboxes based on the settings map
         */
        System.out.println("Configuring checkboxes based on settings");
        /*
        Configure each checkbox according to the settings map. Checkboxes will be checked or unchecked based on their corresponding value in the map.
         */
        searchActions.configureCheckboxes(settings);

        /*
        Log the completion of the test
         */
        System.out.println("Test finished");
    }

}
