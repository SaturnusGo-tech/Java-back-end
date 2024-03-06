package com.virtoworks.omnia.utils.locators.mocksFilters;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class for managing filters locators in the application.
 * Provides easy access to filter elements and their locators.
 */
public class Filters2 {
    public SelenideElement supplierMoreLessData;

    {
        supplierMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Supplier']"));
    }

    public SelenideElement CatalogMoreLessData;

    {
        CatalogMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Catalogs']"));
    }

    public SelenideElement ManufacturerMoreLessData;

    {
        ManufacturerMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Manufacturer']"));
    }

    public SelenideElement ColorMoreLessData;

    {
        ColorMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Color']"));
    }

    public SelenideElement CountryOfOriginMoreLessData;

    {
        CountryOfOriginMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Country of origin']"));
    }

    public SelenideElement MaterialMoreLessData;

    {
        MaterialMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Materials']"));
    }

    public SelenideElement HeightMoreLessData;

    {
        HeightMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Height']"));
    }

    public SelenideElement LengthMoreLessData;

    {
        LengthMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Length']"));
    }

    public SelenideElement WidthMoreLessData;

    {
        WidthMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Width']"));
    }

    public SelenideElement DepthMoreLessData;

    {
        DepthMoreLessData = $(byXpath("//div[@data-element-instance='filter-button: Depth']"));
    }


    public SelenideElement FullData;

    {
        FullData = $(byXpath("//div[@data-element-instance='filter: Supplier']"));
    }

    public SelenideElement CatalogData;

    {
        CatalogData = $(byXpath("//div[@data-element-instance='filter: Catalog']"));
    }

    public SelenideElement ManufacturerData;

    {
        ManufacturerData = $(byXpath("//div[@data-element-instance='filter: Manufacturer']"));
    }

    public SelenideElement ColorData;

    {
        ColorData = $(byXpath("//div[@data-element-instance='filter: Color']"));
    }

    public SelenideElement MeasureData;

    {
        MeasureData = $(byXpath("//div[@data-element-instance='filter: Measure']"));
    }

    public SelenideElement  CountryOfOriginData;

    {
        CountryOfOriginData = $(byXpath("//div[@data-element-instance='filter: CountryOfOrigin']"));
    }

    public SelenideElement MaterialData;

    {
        MaterialData = $(byXpath("//div[@data-element-instance='filter: Material']"));
    }

    public SelenideElement HeightData;

    {
        HeightData = $(byXpath("//div[@data-element-instance='filter: Height']"));
    }

    public SelenideElement LengthData;

    {
        LengthData = $(byXpath("//div[@data-element-instance='filter: Length']"));
    }

    public SelenideElement WidthData;

    {
        WidthData = $(byXpath("//div[@data-element-instance='filter: Width']"));
    }

    public SelenideElement DepthData;

    {
        DepthData = $(byXpath("//div[@data-element-instance='filter: Depth']"));
    }

    /**
     * HashMap
     */

    public Map<String, String> Supplier;
    public Map<String, String> Catalog;
    public Map<String, String> Manufacturer;
    public Map<String, String> Color;
    public Map<String, String> Measure;
    public Map<String, String> CountryOfOrigin;
    public Map<String, String> Material;
    public Map<String, String> Height;
    public Map<String, String> Length;
    public Map<String, String> Width;
    public Map<String, String> Depth;



    /**
     * Initializes maps with checkbox locators for Suppliers and Catalogs.
     * Uses dynamic XPath generation to accommodate a varying number of checkboxes.
     */
    public Filters2() {
        Supplier = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
            Supplier.put("Supplier " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Catalog = new HashMap<>();
        for (int i = 1; i <= 17; i++) {
            Catalog.put("Catalog " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Manufacturer = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Manufacturer.put("Manufacturer " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[3]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Color = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Color.put("Color " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[4]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Measure = new HashMap<>();
        for (int i = 1; i == 1; i++) {
            Measure.put("Measure " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[5]/div[2]/div/div/label[%d]/input", i));
        }
        CountryOfOrigin = new HashMap<>();
        for (int i = 1; i <= 11; i++) {
            CountryOfOrigin.put("CountryOfOrigin " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[6]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Material = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Material.put("Material " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[6]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Height = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Height.put("Height " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[7]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Length = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Length.put("Length " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[8]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Width = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Width.put("Width " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[9]/div[2]/div/div[2]/label[%d]/input", i));
        }
        Depth = new HashMap<>();
        for (int i = 1; i <= 250; i++) {
            Depth.put("Depth " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[10]/div[2]/div/div[2]/label[%d]/input", i));
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
