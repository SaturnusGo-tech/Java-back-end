package com.virtoworks.omnia.test.checkPages;

import com.virtoworks.omnia.utils.global.Config;
import com.virtoworks.omnia.utils.actions.catalog.ActionsCatalog;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCheckCatalogPage {

    private static final Config config = new Config();
    private final ActionsCatalog actionsCatalog = new ActionsCatalog();

    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    @BeforeEach
    public void setUp() {
        config.setUp("catalog");
    }

    /**
     * DOD:
     *   1. Catalog items are present more than 12
     *   2. Check the breadcrumbs catalog item
     */
    @Test
    @Tag("catalogItems")
    public void catalogNavigationCatalogItemsPerPageAmount() {
        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount)
                .as("Number of catalog items should be at least 12")
                .isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText)
                .as("Breadcrumb text should be 'Catalog'")
                .isEqualTo("Catalog");
    }

    /**
     * DOD:
     *   1. Grid visualization is visible
     *   2. List visualization is visible
     */
    @Test
    @Tag("visualization")
    public void catalogNavigationGridListVisualisation() {
        boolean isListVisible = actionsCatalog.verifyVisualisationSwitch("List", 12, config.getEnvDuration());
        assertThat(isListVisible)
                .as("List visualization should be visible")
                .isTrue();

        boolean isGridVisible = actionsCatalog.verifyVisualisationSwitch("Grid", 12, config.getEnvDuration());
        assertThat(isGridVisible)
                .as("Grid visualization should be visible")
                .isTrue();
    }

    @AfterEach
    public void tearDown() {
        config.tearDown();
    }
}
