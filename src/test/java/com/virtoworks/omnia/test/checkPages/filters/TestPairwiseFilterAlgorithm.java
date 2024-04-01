package com.virtoworks.omnia.test.checkPages.filters;

import com.codeborne.selenide.SelenideElement;
import com.virtoworks.omnia.utils.actions.catalog.ActionsCatalog;
import com.virtoworks.omnia.utils.global.Config;
import com.virtoworks.omnia.utils.locators.filters.Filters;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.assertj.core.api.Assertions.assertThat;


public class TestPairwiseFilterAlgorithm {

    private static final Config config = new Config();
    private final ActionsCatalog actionsCatalog = new ActionsCatalog();

    private final Filters filters = new Filters();

    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    private void scrollToElementWithCorrection(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
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
    @Tag("SuppliersFilters")
    public void verifySupplierData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(13, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 13").isGreaterThanOrEqualTo(13);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.FullData);
        filters.FullData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.supplierMoreLessData);
        filters.supplierMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        List<String> checkboxLocators = new ArrayList<>(filters.Supplier.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.supplierMoreLessData, filters.FullData);

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
    public void verifyCatalogData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(17, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog filters items should be at least 17").isGreaterThanOrEqualTo(17);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.CatalogData);
        filters.CatalogData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.CatalogMoreLessData);
        filters.CatalogMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        List<String> checkboxLocators = new ArrayList<>(filters.Catalog.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.CatalogMoreLessData, filters.CatalogData);

        actionsCatalog.waitForCatalogPage();
    }


    /**
     * Test verifies the functionality of color filters within the catalog section.
     * Ensures that applying color filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("ColorFilters")
    public void verifyColorData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.ColorData);
        filters.ColorData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.ColorMoreLessData);
        filters.ColorMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Color.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.ColorMoreLessData, filters.ColorData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of color filters within the catalog section.
     * Ensures that applying color filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Manufacturer'.
     */
    @Test
    @Tag("ColorFilters")
    public void verifyManufacturerData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.ManufacturerData);
        filters.ManufacturerData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.ManufacturerMoreLessData);
        filters.ManufacturerMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Manufacturer.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.ManufacturerMoreLessData, filters.ManufacturerData);
        actionsCatalog.waitForCatalogPage();
    }

    @Test
    @Tag("MeasureFilters")
    public void verifyMeasureData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.MeasureData);
        filters.MeasureData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();


        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Measure.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.MeasureData, filters.MeasureData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of country of origin filters within the catalog.
     * Ensures that applying these filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("CountryOfOriginFilters")
    public void verifyCountryOfOriginData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.CountryOfOriginData);
        filters.CountryOfOriginData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.CountryOfOriginMoreLessData);
        filters.CountryOfOriginMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.CountryOfOrigin.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.CountryOfOriginMoreLessData, filters.CountryOfOriginData);
        actionsCatalog.waitForCatalogPage();
    }
    /**
     * Test verifies the functionality of material filters within the catalog.
     * Ensures that applying material filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'
     */
    @Test
    @Tag("MaterialFilters")
    public void verifyMaterialData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.MaterialData);
        filters.MaterialData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.MaterialMoreLessData);
        filters.MaterialMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Material.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.MaterialMoreLessData, filters.MaterialData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of height filters within the catalog.
     * Ensures that applying height filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("HeightFilters")
    public void verifyHeightData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.HeightData);
        filters.HeightData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.HeightMoreLessData);
        filters.HeightMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Height.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.HeightMoreLessData, filters.HeightData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of length filters within the catalog.
     * Ensures that applying length filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("LengthFilters")
    public void verifyLengthData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.LengthData);
        filters.LengthData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.LengthMoreLessData);
        filters.LengthMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Length.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.LengthMoreLessData, filters.LengthData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of width filters within the catalog.
     * Ensures that applying width filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("WidthFilters")
    public void verifyWidthData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).isGreaterThanOrEqualTo(12).as("The number of catalog items should be at least 12");

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).isEqualTo("Catalog").as("Breadcrumb text should match 'Catalog'");


        scrollToElementWithCorrection(filters.WidthData);
        filters.WidthData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.WidthMoreLessData);
        filters.WidthMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Width.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.WidthMoreLessData, filters.WidthData);
        actionsCatalog.waitForCatalogPage();
    }

    /**
     * Test verifies the functionality of depth filters within the catalog.
     * Ensures that applying depth filters correctly influences the displayed catalog items.
     * <p>
     * Definition of Done (DOD):
     *   1. Catalog items are present more than or equal to 12.
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("DepthFilters")
    public void verifyDepthData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        scrollToElementWithCorrection(filters.DepthData);
        filters.DepthData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        scrollToElementWithCorrection(filters.DepthMoreLessData);
        filters.DepthMoreLessData.shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Depth.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.DepthMoreLessData, filters.DepthData);
        actionsCatalog.waitForCatalogPage();
    }

    @AfterEach
    public void tearDown() {
        config.tearDown();
    }
}
