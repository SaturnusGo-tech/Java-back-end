package com.virtoworks.omnia.utils.actions.orders;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virtoworks.omnia.utils.actions.json.JsonLoader;
import com.virtoworks.omnia.utils.actions.orders.data.OrdersData;
import com.virtoworks.omnia.utils.locators.Orders.OrdersLocators;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageOrderSearchActions {

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
            System.out.println("Search keyword set to: " + keyword);

            orders.IndexButton.shouldBe(visible, Duration.ofSeconds(15))
                    .shouldBe(enabled, Duration.ofSeconds(15))
                    .click();
            System.out.println("Search button clicked.");

            if (noResultsFound()) {
                System.out.println("No results found with keyword: " + keyword + ". Trying another keyword.");
                $(byText("Reset search")).click();
            } else {
                System.out.println("Results found for keyword: " + keyword);
                break;
            }
        } while (true);
    }

    /**
     * Opens the filter panel by clicking on the "Filters" button.
     * Ensures that the filter button is visible before attempting to click.
     */
    public void openUpFilters() {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Object> data = Collections.unmodifiableMap(
                    jsonLoader.loadJsonData("jsonData/pageOrderActions/PageOrderSearchTest.json", this.getClass()));

            Object locatorsObj = data.get("OpenUpFilters");
            if (!(locatorsObj instanceof Map)) {
                throw new ClassCastException("Expected OpenUpFilters to be a Map.");
            }
            Map<String, String> locators = Collections.unmodifiableMap((Map<String, String>) locatorsObj);

            String filterButtonLocator = locators.get("filterButtonLocator");
            String filterBlockLocator = locators.get("filterBlockLocator");

            if (filterButtonLocator == null || filterBlockLocator == null) {
                throw new NullPointerException("Locator keys not found in JSON.");
            }

            SelenideElement filterButton = $(By.xpath(filterButtonLocator))
                    .shouldBe(visible, Duration.ofSeconds(15))
                    .shouldBe(enabled, Duration.ofSeconds(15));
            filterButton.click();
            System.out.println("Filter button clicked.");

            SelenideElement filterBlock = $(By.xpath(filterBlockLocator))
                    .shouldBe(visible, Duration.ofSeconds(15));

            if (filterBlock.exists()) {
                System.out.println("Filter block is now visible.");
            } else {
                System.err.println("Filter block did not become visible as expected.");
            }
        } catch (FileNotFoundException | ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures dropdown selections based on predefined boolean settings.
     * Iterates through each setting, clicking on the dropdown arrow if the setting is true.
     * Utilizes a CSS selector to identify the dropdown arrows by their order.
     */

    /**
     * Configures dropdown selections based on predefined boolean settings.
     * Iterates through each setting, clicking on the dropdown arrow if the setting is true.
     * Utilizes a CSS selector to identify the dropdown arrows by their order.
     */
    public void clickDropdownByIndex(int index, boolean withDelay) {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Map<String, String>> data =
                    jsonLoader.loadJsonData("jsonData/pageOrderActions/PageOrderSearchTest.json", this.getClass());
            String clickDropdownByIndex = data.get("clickDropdownByIndex").get("locator");

            if (clickDropdownByIndex == null) {
                throw new NullPointerException("Locator for 'closeDropByIndex' not found in JSON.");
            }

            ElementsCollection dropdowns = $$(clickDropdownByIndex);
            SelenideElement dropdown = dropdowns.get(index).shouldBe(visible, Duration.ofSeconds(15));
            dropdown.click();

            System.out.println("Dropdown at index " + index + " clicked.");
            if (withDelay) {
                TimeUnit.SECONDS.sleep(7);
                dropdown = dropdowns.get(index).shouldBe(visible, Duration.ofSeconds(15));
                dropdown.click();
                System.out.println("Dropdown at index " + index + " clicked again after 7 seconds.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Action to close dropdown with -0 index
     */
    public void closeDropByIndex() {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Map<String, String>> data =
                    jsonLoader.loadJsonData("jsonData/pageOrderActions/PageOrderSearchTest.json", this.getClass());
            String closeDropLocator = data.get("closeDropByIndex").get("locator");

            if (closeDropLocator == null) {
                throw new NullPointerException("Locator for 'closeDropByIndex' not found in JSON.");
            }
            SelenideElement closeButton = $$(closeDropLocator).first();
            closeButton.click();
            System.out.println("Close button clicked.");

            if (closeButton.is(visible) && closeButton.is(enabled)) {
                System.out.println("The close button is still visible and enabled, indicating it might not have triggered its intended action.");
            } else {
                System.out.println("The close button's state changed after clicking, indicating the click may have triggered its intended action.");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
            System.err.println("An exception occurred: " + e.getMessage());
        }
    }
    /**
     * Configures checkboxes according to a map of settings.
     * Each entry in the map represents a checkbox's desired state (checked or unchecked)
     * identified by a name and associated with a boolean value.
     * Utilizes selector to target checkboxes by their order in a list.
     */
    public void ordersSearchSettings(Map<String, Boolean> checkboxStatesStatusData) {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Map<String, Map<String, Integer>>> data =
                    jsonLoader.loadNestedJsonData("jsonData/pageOrderActions/PageOrderSearchCheckBoxes.json", this.getClass());

            Map<String, Map<String, Integer>> checkboxesData = data.get("dataOrders");

            clickDropdownByIndex(0, true);

            ElementsCollection checkboxes = $$("input[type='checkbox']"); // <-- This locator may affect bad to use to it from json list Due to the index of checkboxes that are read from this file and passed into the method mapping.
            checkboxesData.forEach((name, details) -> {
                Integer index = details.get("index") - 1;
                Boolean shouldBeChecked = checkboxStatesStatusData.get(name);

                if (shouldBeChecked != null) {
                    SelenideElement checkbox = checkboxes.get(index);
                    boolean isSelected = checkbox.isSelected();

                    if (shouldBeChecked && !isSelected) {
                        checkbox.click();

                        System.out.println("Checkbox '" + name + "' was clicked to change to desired state: true");

                    } else if (!shouldBeChecked && isSelected) {
                        System.err.println("Checkbox '" + name + "' is in an incorrect state and should be unchecked, but no action was taken.");

                    } else {
                        System.out.println("Checkbox '" + name + "' is already in the desired state: " + shouldBeChecked);
                    }

                } else {
                    System.err.println("State for checkbox '" + name + "' not specified in test settings.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("An exception occurred during the checkbox state configuration: " + e.getMessage());
        }
        closeDropByIndex();
    }

    /**
     * Configures checkboxes according to a map of settings.
     * Each entry in the map represents a checkbox's desired state (checked or unchecked)
     * identified by a name and associated with a boolean value.
     * Utilizes selector to target checkboxes by their order in a list.
     */

    public void scrollToElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'nearest'});", element);
    }
    public void suppliersSearchSettings(Map<String, Boolean> checkboxStatesSupData) {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Map<String, Map<String, Integer>>> data =
                    jsonLoader.loadNestedJsonData("jsonData/pageOrderActions/PageOrderSearchCheckBoxes.json", getClass());

            Map<String, Map<String, Integer>> checkboxesData = data.get("dataSuppliers");

            clickDropdownByIndex(1, true);

            ElementsCollection checkboxes = $$("input[type='checkbox']");

            checkboxesData.forEach((name, details) -> {
                Integer index = details.get("index") - 1;
                if (index >= 9) {
                    Boolean shouldBeChecked = checkboxStatesSupData.get(name);
                    if (shouldBeChecked != null) {
                        SelenideElement checkbox = checkboxes.get(index).shouldBe(visible, Duration.ofSeconds(15));
                        scrollToElement(checkbox);

                        boolean isSelected = checkbox.isSelected();

                        if (shouldBeChecked && !isSelected) {
                            checkbox.click();
                            System.out.println("Supplier checkbox '" + name + "' was clicked to change to desired state: true.");
                        } else if (!shouldBeChecked && isSelected) {
                            checkbox.click();
                            System.out.println("Supplier checkbox '" + name + "' was clicked to change to desired state: false.");
                        } else {
                            System.out.println("Supplier checkbox '" + name + "' is already in the desired state: " + shouldBeChecked + ".");
                        }
                    } else {
                        System.err.println("State for supplier checkbox '" + name + "' not specified in test settings.");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("An exception occurred during the supplier checkbox state configuration: " + e.getMessage());
        }
    }
    public void applyConfig() {
        try {
            JsonLoader jsonLoader = new JsonLoader();
            Map<String, Map<String, String>> data = jsonLoader.loadJsonData("jsonData/pageOrderActions/PageOrderSearchTest.json", this.getClass());

            String applyButtonLocator = data.get("applyConfig").get("locator");

            if (applyButtonLocator == null) {
                throw new NullPointerException("Apply button locator not found in JSON.");
            }

            SelenideElement filterButton = $(By.xpath(applyButtonLocator))
                    .shouldBe(visible, Duration.ofSeconds(15))
                    .shouldBe(enabled, Duration.ofSeconds(15));
            filterButton.click();
            System.out.println("Attempted to click the Apply button.");

            if (filterButton.is(visible) && filterButton.is(enabled)) {
                System.out.println("The Apply button is still visible and enabled, indicating it might not have triggered its intended action.");
            } else {
                System.out.println("The Apply button's state changed after clicking, indicating the click may have triggered its intended action.");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
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