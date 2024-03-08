package com.virtoworks.omnia.utils.actions.catalog.products;

import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virtoworks.omnia.utils.data.ProductsData;
import com.virtoworks.omnia.utils.locators.catalog.products.ProductsLocators;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;


public class ProductsActions {

    private final ProductsLocators productsLocators = new ProductsLocators();
    private final Gson gson = new Gson();

    public void enterRandomProductKeyword() {
        Type productListType = new TypeToken<Map<String, List<Map<String, Object>>>>() {}.getType();

        Map<String, List<Map<String, Object>>> productData = gson.fromJson(ProductsData.JSON_DATA, productListType);
        List<Map<String, Object>> products = productData.get("products");

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        String randomKeyword = (String) products.get(randomIndex).get("keyword");

        System.out.println("Selected keyword: " + randomKeyword);

        SelenideElement searchProductsInput = productsLocators.searchProductsInput;

        searchProductsInput.shouldBe(visible);

        searchProductsInput.setValue(randomKeyword).pressEnter();

        System.out.println("Waiting for 10 seconds to observe the search results...");
        sleep(10000);

    }

}
