package com.virtoworks.omnia.utils.actions.orders;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virtoworks.omnia.utils.actions.orders.data.OrdersData;
import com.virtoworks.omnia.utils.locators.Orders.OrdersLocators;
import org.openqa.selenium.By;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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

    private final OrdersLocators orders = new OrdersLocators();
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
        return $(byText("No results found")).exists();
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
                    .shouldBe(visible, Duration.ofSeconds(15))
                    .shouldBe(enabled, Duration.ofSeconds(15));

            searchInput.setValue(keyword);
            orders.IndexButton.click();

            if (noResultsFound()) {
                System.out.println("No results found. Trying another keyword.");
                $(byText("Reset search")).click();

            } else {
                break; // Successful search, exit loop.,.,.,
            }
        } while (true);
    }

    /**
     * Opens the filter panel by clicking on the "Filters" button.
     * Ensures that the filter button is visible before attempting to click.
     */
    public void openUpFilters() {
        SelenideElement filterButton = $(By.xpath("//button[contains(.,'Filters')]"))
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(enabled, Duration.ofSeconds(15));
        filterButton.click();
    }



    /**
     * Configures dropdown selections based on predefined boolean settings.
     * Iterates through each setting, clicking on the dropdown arrow if the setting is true.
     * Utilizes a CSS selector to identify the dropdown arrows by their order.
     */
    public void setBuilderDropdownByIndex() {
        boolean[] buttonSettings = {true, false};

        ElementsCollection dropdowns = $$(".vc-select__container");
        for (int i = 0; i < buttonSettings.length; i++) {
            if (buttonSettings[i]) {
                dropdowns.get(i).shouldBe(visible, Duration.ofSeconds(15)).click();
            }
        }
    }


    /**
     * Configures checkboxes according to a map of settings.
     * Each entry in the map represents a checkbox's desired state (checked or unchecked)
     * identified by a name and associated with a boolean value.
     * Utilizes selector to target checkboxes by their order in a list.
     */
    public void configureCheckboxes(Map<String, Boolean> settings) {
        Map<String, Integer> namesToIndexes = new HashMap<>();
        namesToIndexes.put("Approval needed", 1);
        namesToIndexes.put("Approved", 2);
        namesToIndexes.put("Cancelled", 3);
        namesToIndexes.put("Completed", 4);
        namesToIndexes.put("Confirmed", 5);
        namesToIndexes.put("New", 6);
        namesToIndexes.put("Payment Required", 7);
        namesToIndexes.put("Pending", 8);
        namesToIndexes.put("Rejected", 9);

        // <-- need to fix this locator --> \\
        String basePath = "//*[@id=\"app\"]/div[3]/div[4]/div/div/div/div[2]/div[3]/div[1]/div/div/div[1]/div[1]/div[1]/div[2]/div[2]/ul/li[%d]";
        // <-- need to fix this locator --> \\
        for (Map.Entry<String, Boolean> entry : settings.entrySet()) {
            String name = entry.getKey();
            Boolean shouldBeChecked = entry.getValue();
            Integer index = namesToIndexes.get(name);

            if (index != null && shouldBeChecked) {
                String xpath = String.format(basePath, index);
                SelenideElement checkbox = $x(xpath);

                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }
    }

    public void applyConfig() {
        SelenideElement filterButton = $(By.xpath("//button[contains(.,'Apply')]"))
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(enabled, Duration.ofSeconds(15));
        filterButton.click();
    }

    /**
     * Pauses the execution for a specified number of milliseconds.
     * This method is used to introduce a delay in test execution, for example, to wait for page elements to load.
     * Note: Using fixed delays is not a best practice in test automation. Consider using explicit waits where possible.
     *
     * @param milliseconds the number of milliseconds to pause the execution
     */
    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}