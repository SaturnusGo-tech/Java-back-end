package com.virtoworks.omnia.test.checkPages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.virtoworks.omnia.utils.global.Config;
import com.virtoworks.omnia.utils.actions.catalog.ActionsCatalog;
import com.virtoworks.omnia.utils.locators.filters.Filters;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPairwiseFilterAlgorithm {

    private static final Config config = new Config();
    private final ActionsCatalog actionsCatalog = new ActionsCatalog();

    private final Filters filters = new Filters();

    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    @BeforeEach
    public void setUp() {
        config.setUp("catalog");
    }

    /**
     * Test verifies the functionality of supplier filters within the supplier section.
     * Ensures that applying filters correctly influences the displayed catalog items.
     * <p>
     * DOD:
     *   1. Catalog items are present more than or equal to 12
     *   2. Breadcrumb text is verified as 'Catalog'
     */
    @Test
    @Tag("SupplierFilters")
    public void verifySupplierFiltersFunctionality() throws InterruptedException {
        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        List<String> checkboxLocators = new ArrayList<>(filters.Supplier.values());
        checkboxLocators.addAll(filters.Catalog.values());

        // Click on each checkbox and verify updates
        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.MoreLessData, filters.SupData);

        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of catalog filters within the catalog section.
     * It ensures that filters work as expected and that the catalog updates accordingly.
     * <p>
     * DOD:
     *   1. Catalog items are present more than or equal to 12
     *   2. Breadcrumb text is verified as 'Catalog'
     */
    @Test
    @Tag("CatalogFilters")
    public void verifyCatalogFiltersFunctionality() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        filters.CatalogData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        List<String> checkboxLocators = new ArrayList<>(filters.Catalog.values());

        // Click on each checkbox and verify updates
        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.MoreLessData, filters.FullData);

        actionsCatalog.waitForCatalogPage();
    }

    @AfterEach
    public void tearDown() {
        config.tearDown();
    }
}
