package com.virtoworks.omnia.utils.locators.Orders;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class OrdersLocators {

    public SelenideElement searchProductsInput;

    {
        searchProductsInput = $("[placeholder='Enter keyword...']");
    }

    public SelenideElement IndexButton;

    {
        IndexButton = $(byXpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[2]/div[3]/div[2]/div/div[1]/div[1]/button[2]"));
    }
}
