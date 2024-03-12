package com.virtoworks.omnia.utils.locators.catalog.products;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsLocators {

    public SelenideElement searchProductsInput;

    {
        searchProductsInput = $("[placeholder='Enter keyword...']");
    }

    public SelenideElement IndexButton;

    {
        IndexButton = $(byXpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[2]/div[3]/div[2]/div/div[1]/div[1]/button"));
    }


    public SelenideElement addToCartButton;

    {
        addToCartButton = $x("//button[contains(., 'Add to cart')]");
    }

}
