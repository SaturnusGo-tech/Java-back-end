package com.virtoworks.omnia.test.checkPages.orders;

import com.virtoworks.omnia.utils.actions.orders.PageOrderSearchActions;
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
public class PageOrderSearchTest {

    // Global configuration for setting up the environment before running tests.
    private static final Config config = new Config();
    // Actions to perform order searches.
    private final PageOrderSearchActions searchActions = new PageOrderSearchActions();

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
     * This test demonstrates the process of executing a search operation with random keywords
     * and applying various filters to refine the search results. It highlights key actions such as
     * opening the filters section, configuring dropdown selections, and setting checkboxes
     * according to predefined criteria. The test provides insight into handling dynamic web elements
     * with Selenide and illustrates the application of explicit waits and conditional logic
     * to ensure the correct state of web elements before interaction.
     */
    @Test
    public void testSearchWithRandomKeywords() {
        // Start of the test: Searching with random keywords.
        System.out.println("Test started: Searching with random keywords");
        // Attempt to search for orders using a randomly selected keyword.
        searchActions.attemptSearchWithRandomKeywords();

        // Opening the filters section to access more search options.
        System.out.println("Filters are opened");
        searchActions.openUpFilters();

        // Introducing a brief pause to ensure that dropdowns are ready for interaction.
        System.out.println("Waiting 10 seconds before configuring dropdowns");
        sleep(10000);

        // Setting up criteria for checkboxes to simulate user selection in the UI.
        Map<String, Boolean> checkboxStatesStatusData = getStringBooleanMapStatusData();
        // Applying the configurations to checkboxes based on the predefined settings.
        System.out.println("Configuring checkboxes based on settings");
        searchActions.ordersSearchSettings(checkboxStatesStatusData);

        // Setting up criteria for checkboxes to simulate user selection in the UI.
        Map<String, Boolean> checkboxStatesSupData = getStringBooleanMapSupData();
        searchActions.suppliersSearchSettings(checkboxStatesSupData);
        sleep(4000);

        // Applying the configurations and finalizing the filter setup.
        searchActions.applyConfig();
        sleep(1000);

        // Marking the end of the test execution.
        System.out.println("Test finished");
    }
    /**
     *
     * Each entry represents a checkbox and whether it should be
     * checked (true) or unchecked (false).
     */
    private static Map<String, Boolean> getStringBooleanMapStatusData() {
        Map<String, Boolean> settings = new HashMap<>();
        settings.put("Approval needed", true);
        settings.put("Approved", true);
        settings.put("Cancelled", true);
        settings.put("Completed", false);
        settings.put("Confirmed", true);
        settings.put("New", false);
        settings.put("Payment Required", false);
        settings.put("Pending", false);
        settings.put("Rejected", true);
        return settings;
    }
    private static Map<String, Boolean> getStringBooleanMapSupData() {
        Map<String, Boolean> settings = new HashMap<>();
        settings.put("Global Industrial", true);
        settings.put("ODP Business Solutions, LLC (formerly Office Depot)", true);
        settings.put("Pocket Nurse", false);
        settings.put("Quill Corporation", false);
        return settings;
    }

}