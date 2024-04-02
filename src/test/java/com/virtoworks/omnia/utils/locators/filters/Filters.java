package com.virtoworks.omnia.utils.locators.filters;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class for managing filters locators in the application.
 * Provides easy access to filter elements and their locators.
 */
public class Filters {
    public SelenideElement supplierMoreLessData;

    {
        supplierMoreLessData = $("button[data-element-instance='filter-button: Supplier']");
    }

    public SelenideElement CatalogMoreLessData;

    {
        CatalogMoreLessData = $("button[data-element-instance='filter-button: Catalogs']");
    }

    public SelenideElement ManufacturerMoreLessData;

    {
        ManufacturerMoreLessData = $("button[data-element-instance='filter-button: Manufacturer']");
    }

    public SelenideElement ColorMoreLessData;

    {
        ColorMoreLessData = $("button[data-element-instance='filter-button: Color']");
    }

    public SelenideElement CountryOfOriginMoreLessData;

    {
        CountryOfOriginMoreLessData = $("button[data-element-instance='filter-button: Country of Origin']");
    }

    public SelenideElement MaterialMoreLessData;

    {
        MaterialMoreLessData = $("button[data-element-instance='filter-button: Material']");
    }

    public SelenideElement HeightMoreLessData;

    {
        HeightMoreLessData = $("button[data-element-instance='filter-button: Height']");
    }

    public SelenideElement LengthMoreLessData;

    {
        LengthMoreLessData = $("button[data-element-instance='filter-button: Length']");
    }

    public SelenideElement WidthMoreLessData;

    {
        WidthMoreLessData = $("button[data-element-instance='filter-button: Width']");
    }

    public SelenideElement DepthMoreLessData;

    {
        DepthMoreLessData = $("button[data-element-instance='filter-button: Depth']");
    }


    public SelenideElement FullData;

    {
        FullData = $(byXpath("//div[@data-element-instance='filter: Supplier']"));
    }

    public SelenideElement CatalogData;

    {
        CatalogData = $(byXpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/button/span/span[2]"));
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
        MeasureData = $(byXpath("//div[@data-element-instance='filter: Unit of Measure']"));
    }

    public SelenideElement  CountryOfOriginData;

    {
        CountryOfOriginData = $(byXpath("//div[@data-element-instance='filter: Country of Origin']"));
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

    public SelenideElement DepthData = $(byXpath("//div[@data-element-instance='filter: Depth']"));

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
    public Filters() {
        Supplier = new LinkedHashMap<>();
        for (int i = 1; i <= 12; i++) {
            Supplier.put("Supplier " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Catalog = new LinkedHashMap<>();
        for (int i = 1; i <= 17; i++) {
            Catalog.put("Catalog " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Manufacturer = new LinkedHashMap<>();
        for (int i = 1; i <= 250; i++) {
            Manufacturer.put("Manufacturer " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[3]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Color = new LinkedHashMap<>();
        for (int i = 1; i <= 250; i++) {
            Color.put("Color " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[4]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Measure = new LinkedHashMap<>();
        for (int i = 1; i == 3; i++) {
            Measure.put("Measure " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[5]/div/div/div/div/label[%d]/input", i));
        }
        CountryOfOrigin = new LinkedHashMap<>(); //141
        for (int i = 1; i <= 1; i++) {
            CountryOfOrigin.put("CountryOfOrigin " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[6]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Material = new LinkedHashMap<>(); //250
        for (int i = 1; i <= 1; i++) {
            Material.put("Material " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[8]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Height = new LinkedHashMap<>(); //250
        for (int i = 1; i <= 1; i++) {
            Height.put("Height " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[9]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Length = new LinkedHashMap<>(); //250
        for (int i = 1; i <= 1; i++) {
            Length.put("Length " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[10]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Width = new LinkedHashMap<>(); //250
        for (int i = 1; i <= 1; i++) {
            Width.put("Width " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[11]/div[1]/div/div/div[2]/label[%d]/input", i));
        }
        Depth = new HashMap<>(); //250
        for (int i = 1; i <= 1; i++) {
            Depth.put("Depth " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[12]/div[1]/div/div/div[2]/label[%d]/input", i));
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


