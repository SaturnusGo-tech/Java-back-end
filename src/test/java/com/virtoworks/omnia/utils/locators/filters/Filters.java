package com.virtoworks.omnia.utils.locators.filters;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class for managing filters locators in the application.
 * Provides easy access to filter elements and their locators.
 */
public class Filters {

    // Data elements locators for supplier and catalog sections
    public SelenideElement SupData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[2]/div[4]/div[1]/div[2]/div[2]/div[2]"));
    public SelenideElement CatalogData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div"));
    public SelenideElement FullData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[2]/div/div[2]"));

    // Locator for the "More/Less" button to toggle visibility of additional filter options
    public SelenideElement MoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[3]/button"));

    // Maps for storing checkbox locators for Supplier and Catalog filters


    /**
     * HashMap
     */

    public Map<String, String> Supplier;
    public Map<String, String> Catalog;

    /**
     * Initializes maps with checkbox locators for Suppliers and Catalogs.
     * Uses dynamic XPath generation to accommodate a varying number of checkboxes.
     */
    public Filters() {
        Supplier = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
            Supplier.put("Supplier " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[2]/div/div[2]/label[%d]/input", i));
        }

        Catalog = new HashMap<>();
        for (int i = 1; i <= 16; i++) {
            Catalog.put("Catalog " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[2]/div/div[2]/label[%d]/input", i));
        }
    }

    /**
     * Retrieves the XPath locator for a specified checkbox name, whether it belongs to Supplier or Catalog.
     * @param checkboxName The name of the checkbox to retrieve the locator for.
     * @return The XPath locator as a String.
     */
    public String getCheckboxLocator(String checkboxName) {
        String locator = Supplier.get(checkboxName);
        if (locator == null) {
            locator = Catalog.get(checkboxName);
        }
        return locator;
    }
}
