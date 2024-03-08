package com.virtoworks.omnia.utils.locators.catalog.products;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsLocators {

    public SelenideElement searchProductsInput;

    {
        searchProductsInput = $(".vc-input__input");
    }

    public SelenideElement IndexButton;

    {
        IndexButton = $(byXpath("//*[@id=\"app\"]/div/div[2]/nav/div/div[1]/div/div[1]/div[1]/button"));
    }

    public SelenideElement addToCartButton;

    {
        addToCartButton = $x("//button[contains(., 'Add to cart')]");
    }


    public Map<String, String> Supplier;

    public ProductsLocators() {
        Supplier = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
            Supplier.put("Supplier " + i, String.format("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div/div/div[1]/div[2]/div/div/label[%d]/input", i));
        }
    }
}
