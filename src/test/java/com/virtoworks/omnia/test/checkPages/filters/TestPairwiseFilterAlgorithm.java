package com.virtoworks.omnia.test.checkPages.filters;

import com.virtoworks.omnia.utils.actions.catalog.ActionsCatalog;
import com.virtoworks.omnia.utils.global.Config;
import com.virtoworks.omnia.utils.locators.filters.Filters;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
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
        RestAssured.baseURI = "https://qa-opus.omniapartners.com/xapi/graphql";
    }


    /**
     * Test query data
     * Here's I've put the example part of graphql data - Need to IMP bearer token for further steps
     *
     *  @Test
     *     @Tag("SuppliersFilters")
     *     public void verifyCheckboxClickAndGraphQLResponse() {
     * <p>
     *         String authToken = getTokenFromCookie();
     * <p>
     *         String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
     *         assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");
     * <p>
     *         $x("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[2]/div/div[2]/label[1]/input")
     *                 .shouldBe(visible, enabled)
     *                 .click();
     * <p>
     *         String requestBody = String.format(
     *                 "{\"query\":\"query SearchProducts($storeId: String!, $userId: String!, $currencyCode: String!, $cultureName: String, $filter: String, $after: String, $first: Int, $sort: String, $query: String, $fuzzy: Boolean, $fuzzyLevel: Int, $productIds: [String], $withFacets: Boolean!, $withImages: Boolean!, $withProductOffers: Boolean!) { products(storeId: \\\"%s\\\", userId: \\\"%s\\\", currencyCode: \\\"%s\\\", cultureName: \\\"%s\\\", filter: \\\"%s\\\") { totalCount } }\",\"variables\":{\"storeId\":\"opus\", \"userId\":\"78e100ff-ea81-4aca-ad83-5f112e80fc77\", \"currencyCode\":\"USD\", \"cultureName\":\"en-US\", \"filter\":\"category.subtree:21057e9c-14df-48c1-be58-0c59a4f38f06 \\\"SupplierOuterId\\\":\\\"MOK-1\\\"\", \"after\":\"0\", \"first\":16, \"sort\":\"\", \"query\":\"\", \"fuzzy\":false, \"fuzzyLevel\":0, \"productIds\":[], \"withFacets\":true, \"withImages\":true, \"withProductOffers\":false}}",
     *                 "opus", "78e100ff-ea81-4aca-ad83-5f112e80fc77", "USD", "en-US", "category.subtree:21057e9c-14df-48c1-be58-0c59a4f38f06 \"SupplierOuterId\":\"MOK-1\"");
     * <p>
     *         ValidatableResponse authorization = given()
     *                 .contentType(ContentType.JSON)
     *                 .header("Authorization", "Bearer " + authToken)
     *                 .body(requestBody)
     *                 .when()
     *                 .post("/xapi/graphql")
     *                 .then()
     *                 .statusCode(200)
     *                 .body("data.products.totalCount", greaterThan(0));
     *     }
     * /

    /**
     * Dynamic bearer token eater
     *
     * <p>
     *
     *  public String getTokenFromCookie() {
     *         return WebDriverRunner.getWebDriver().manage().getCookieNamed(".AspNetCore.Identity.Application").getValue();
     *     }
     */

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

        filters.supplierMoreLessData.scrollTo().shouldBe(visible).click();
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

        filters.CatalogData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        filters.CatalogMoreLessData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        List<String> checkboxLocators = new ArrayList<>(filters.Catalog.values());

        // Click on each checkbox and verify updates
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

        filters.ColorData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.ColorMoreLessData.scrollTo().shouldBe(visible).click();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Color.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.ColorMoreLessData, filters.ManufacturerData);
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

        filters.ManufacturerData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.ManufacturerMoreLessData.scrollTo().shouldBe(visible).click();

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

        filters.MeasureData.scrollTo().shouldBe(visible).click();
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

        filters.CountryOfOriginData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.ManufacturerMoreLessData.scrollTo().shouldBe(visible).click();

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
     *   2. Breadcrumb text is verified as 'Catalog'.
     */
    @Test
    @Tag("MaterialFilters")
    public void verifyMaterialData() throws InterruptedException {
        actionsCatalog.waitForCatalogPage();

        int itemsCount = actionsCatalog.verifyCatalogItemsPresent(12, config.getEnvDuration());
        assertThat(itemsCount).as("The number of catalog items should be at least 12").isGreaterThanOrEqualTo(12);

        String breadcrumbText = actionsCatalog.checkBreadcrumbText("Catalog", config.getEnvDuration());
        assertThat(breadcrumbText).as("Breadcrumb text should match 'Catalog'").isEqualTo("Catalog");

        filters.MaterialData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.MaterialMoreLessData.scrollTo().shouldBe(visible).click();

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

        filters.HeightData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.HeightMoreLessData.scrollTo().shouldBe(visible).click();

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

        filters.LengthData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.LengthMoreLessData.scrollTo().shouldBe(visible).click();

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

        filters.WidthData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.WidthMoreLessData.scrollTo().shouldBe(visible).click();

        actionsCatalog.waitForCatalogPage();
        List<String> checkboxLocators = new ArrayList<>(filters.Width.values());

        actionsCatalog.clickCheckboxesAndCheckUpdates(filters, checkboxLocators, filters.WidthMoreLessData, filters.WidthData); // Check each option and validate the update.
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

        filters.DepthData.scrollTo().shouldBe(visible).click();
        actionsCatalog.waitForCatalogPage();

        Thread.sleep(2000);
        filters.DepthMoreLessData.scrollTo().shouldBe(visible).click();

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
