package com.virtoworks.omnia.utils.actions.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.virtoworks.omnia.utils.locators.catalog.CatalogPageLocators;
import com.virtoworks.omnia.utils.locators.filters.Filters;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

/**
 * ActionsCatalog class encapsulates behaviors related to catalog operations,
 * such as loading queries, verifying items, and manipulating UI elements.
 */
public class ActionsCatalog {

    private final CatalogPageLocators locators = new CatalogPageLocators();

    /**
     * Load a GraphQL query from a file.
     *
     * @param filePath Path to the file containing the GraphQL query.
     * @return String representation of the query, or null in case of an error.
     */
    public String loadGraphQLQuery(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Verify that a certain number of catalog items are present.
     *
     * @param expectedMinimum Minimum expected number of items.
     * @param timeoutSeconds  Timeout in seconds to wait for the condition.
     * @return Actual number of items found.
     */
    public int verifyCatalogItemsPresent(int expectedMinimum, int timeoutSeconds) {
        return $$x(locators.catalogItems).shouldHave(sizeGreaterThanOrEqual(expectedMinimum), Duration.ofSeconds(timeoutSeconds)).size();
    }

    /**
     * Check and return the text of a breadcrumb element.
     *
     * @param expectedText   Expected text to verify.
     * @param timeoutSeconds Timeout in seconds to wait for visibility.
     * @return Text of the breadcrumb.
     */
    public String checkBreadcrumbText(String expectedText, int timeoutSeconds) {
        return $x(locators.breadcrumb).shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).getText();
    }

    /**
     * Verify catalog items' visibility after switching the view mode.
     *
     * @param buttonText     Text on the button to switch views.
     * @param expectedMinimum Minimum expected number of items visible.
     * @param timeoutSeconds  Timeout in seconds to wait for the condition.
     * @return True if the condition is met, false otherwise.
     */
    public boolean verifyVisualisationSwitch(String buttonText, int expectedMinimum, int timeoutSeconds) {
        $x(locators.visualisationSwitchButton + "[contains(text(), '" + buttonText + "')]").shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).click();
        return $$x(locators.catalogItems).shouldBe(sizeGreaterThanOrEqual(expectedMinimum), Duration.ofSeconds(timeoutSeconds)).size() >= expectedMinimum;
    }

    /**
     * Waits for the catalog page to fully load.
     */
    public void waitForCatalogPage() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
    }

    // Constant for the GraphQL endpoint URL.
    private static final String GRAPHQL_ENDPOINT = "https://qa-opus.omniapartners.com/xapi/graphql";

    /**
     * Click on checkboxes according to provided locators and check for updates.
     */
    public void clickWithJS(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

    public void clickCheckboxesAndCheckUpdates(Filters filters, List<String> checkboxLocators, SelenideElement moreLessButton, SelenideElement dataElementLocator) throws InterruptedException {
        Set<String> clickedCheckboxes = new HashSet<>();

        for (String locator : checkboxLocators) {
            SelenideElement checkbox = retryFindElement(locator, 500, 300000);

            scrollToElementWithCorrection(checkbox, 100);

            if (!clickedCheckboxes.contains(locator) && !checkbox.isSelected()) {
                clickWithJS(checkbox);
                checkbox.shouldBe(Condition.checked, Duration.ofSeconds(10));
                System.out.println("Clicked on checkbox: " + locator);
                clickedCheckboxes.add(locator);
            } else {
                System.out.println("Checkbox " + locator + " is already clicked or selected.");
            }
        }
    }

    /**
     * Scrolls to an element on the page with a possible position correction.
     *
     * @param element The element to scroll to.
     * @param correction The correction offset to ensure the element is not hidden under a fixed header.
     */
    public void scrollToElementWithCorrection(SelenideElement element, int correction) {
        executeJavaScript("arguments[0].scrollIntoView(true); window.scrollBy(0, arguments[1]);", element, -correction);
    }

    /**
     * Repeatedly searches for an element with a delay and timeout.
     *
     * @param locator The locator of the element to search for.
     * @param delay The delay between attempts in milliseconds.
     * @param timeout The timeout for waiting in milliseconds.
     * @return The found element.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */

    private SelenideElement retryFindElement(String locator, long delay, long timeout) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                SelenideElement element = $x(locator).shouldBe(visible, Duration.ofSeconds(10)).shouldBe(enabled, Duration.ofSeconds(10));
                return element;
            } catch (Exception e) {
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw new AssertionError("\"Element not found within the allocated time: " + timeout + " ms. Locator: " + locator);
                }
                Thread.sleep(delay);
            }
        }
        throw new AssertionError("\"Element not found. Execution should not have reached this point.");
    }




    /**
         * Sends a GraphQL query to a server and returns the response as a string.
         *
         * This method constructs a GraphQL request using a given query string,
         * sends the request to a GraphQL server, and handles the response.
         * It serializes the query into JSON format, sends it over HTTP POST,
         * and extracts the response body as a string.
         *
         * @param queryString The GraphQL query string to be sent.
         * @return The server's response as a String.
         */
    public String sendGraphQLRequest(String queryString) {
        // Create a Gson instance for JSON serialization.
        Gson gson = new GsonBuilder().create();

        // Prepare the request body by wrapping the query string into a map with a "query" key.
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", queryString);

        // Serialize the map into a JSON string.
        String jsonRequestBody = gson.toJson(requestBody);

        // Log the JSON request body for debugging purposes.
        System.out.println("Sending GraphQL request with body: " + jsonRequestBody);

        // Send the request to the GraphQL endpoint using RestAssured,
        // specifying the content type as application/json, setting the request body,
        // and using HTTP POST method. Extract and return the response body as a string.
        Response response = given()
                .contentType("application/json")
                .body(jsonRequestBody)
                .post(GRAPHQL_ENDPOINT)
                .then()
                .extract()
                .response();

        return response.asString();
    }
}
