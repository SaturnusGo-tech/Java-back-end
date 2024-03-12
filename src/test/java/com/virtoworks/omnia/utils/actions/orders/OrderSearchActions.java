package com.virtoworks.omnia.utils.actions.orders;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virtoworks.omnia.utils.actions.orders.data.OrdersData;
import com.virtoworks.omnia.utils.locators.catalog.products.ProductsLocators;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderSearchActions {

    /**
     * Enters a random orders keyword into the search input.
     * <p>
     * This method selects a random keyword from a predefined list of suppliers or orders, logs the selected keyword,
     * ensures that the search input field is visible and enabled, and then inputs the keyword into the field.
     * It uses a delay to wait for elements and interactions to be ready based on the application's responsiveness.
     *
     * <p>Usage instructions:</p>
     * <ul>
     *     <li>Ensure that the `OrdersData.JSON_DATA` contains a valid JSON structure of products with keywords.</li>
     *     <li>Call this method when you need to simulate entering a random product keyword in the search input.</li>
     *     <li>This method is designed for testing purposes to verify that the search functionality can handle
     *     various product keywords correctly.</li>
     * </ul>
     *
     * <p>Adaptation guide:</p>
     * <ul>
     *     <li>To adapt this method for different scenarios, consider changing the source of the product keywords
     *     or adjusting the delay times to match the responsiveness of the application under test.</li>
     *     <li>If the structure of the product data changes, update the `selectRandomProductKeyword` method
     *     to correctly parse and select keywords from the new data structure.</li>
     *     <li>For applications with different locator strategies, update the `ProductsLocators` class accordingly
     *     to ensure that the search input field can be correctly identified and interacted with.</li>
     * </ul>
     */

    private final ProductsLocators productsLocators = new ProductsLocators();
    private final Gson gson = new Gson();

    /**
     * Selects a random keyword from a list of products.
     * Assumes that the JSON data structure is a map with a key "products" that contains a list of products,
     * each product being a map with a key "keyword".
     * @return A random product keyword.
     */
    private String getRandomOrderKeyword() {
        Type typeOfProductList = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
        Map<String, List<Map<String, String>>> productsData = gson.fromJson(OrdersData.JSON_DATA, typeOfProductList);
        List<Map<String, String>> products = productsData.get("products");

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        return products.get(randomIndex).get("keyword");
    }

    /**
     * Checks if the page shows "No results found".
     * @return true if the text "No results found" is found on the page.
     */
    private boolean noResultsFound() {
        return $(Selectors.byText("No results found")).exists();
    }

    /**
     * Attempts to search for a product using random keywords until results are found.
     * If "No results found" is displayed, tries another random keyword.
     */
    public void attemptSearchWithRandomKeywords() {
        do {
            String keyword = getRandomOrderKeyword();
            System.out.println("Attempting to search with keyword: " + keyword);

            SelenideElement searchInput = $(Selectors.byAttribute("placeholder", "Enter keyword..."))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldBe(enabled);

            searchInput.clear();
            searchInput.setValue(keyword);
            productsLocators.IndexButton.click();

            // Wait for results to load.
            sleep();

            if (noResultsFound()) {
                System.out.println("No results found. Trying another keyword.");
                $(Selectors.byText("Reset search")).click();
                // Wait before trying again.
                sleep();
            } else {
                break; // Successful search, exit loop.
            }
        } while (true);
    }

    /**
     * Sleeps for the given number of milliseconds.
     */
    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}