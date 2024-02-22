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


    public SelenideElement SupplierMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[3]/button"));
    public SelenideElement CatalogMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[3]/button"));

    public SelenideElement ManufacturerMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[3]/div[3]/button"));
    public SelenideElement ColorMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[4]/div[3]/button"));
    public SelenideElement CountryOfOriginMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[5]/div[3]/button"));
    public SelenideElement MaterialMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[6]/div[3]/button"));
    public SelenideElement HeightMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[7]/div[3]/button"));
    public SelenideElement LengthMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[8]/div[3]/button"));
    public SelenideElement WidthMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[9]/div[3]/button"));
    public SelenideElement DepthMoreLessData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[10]/div[3]/button"));


    public SelenideElement CatalogData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div"));
    public SelenideElement FullData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div"));
    public SelenideElement ManufacturerData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[3]/div"));
    public SelenideElement ColorData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[4]/div"));
    public SelenideElement CountryOfOriginData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[5]/div"));
    public SelenideElement MaterialData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[6]/div"));
    public SelenideElement HeightData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[7]/div"));
    public SelenideElement LengthData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[8]/div"));
    public SelenideElement WidthData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[9]/div"));
    public SelenideElement DepthData = $(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[10]/div"));

    /**
     * HashMap
     */

    public Map<String, String> Supplier;
    public Map<String, String> Catalog;
    public Map<String, String> Manufacturer;
    public Map<String, String> Color;
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
        Supplier = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            Supplier.put("Supplier " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[2]/div/div[2]/label[%d]/input", i));
        }


        Catalog = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
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
        CountryOfOrigin = new HashMap<>();
        for (int i = 1; i <= 11; i++) {
            CountryOfOrigin.put("CountryOfOrigin " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[5]/div[2]/div/div[2]/label[%d]/input", i));
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
