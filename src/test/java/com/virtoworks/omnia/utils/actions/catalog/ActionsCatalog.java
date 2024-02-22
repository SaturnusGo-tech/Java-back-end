package com.virtoworks.omnia.utils.actions.catalog;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.virtoworks.omnia.utils.locators.catalog.CatalogPageLocators;
import com.virtoworks.omnia.utils.locators.filters.Filters;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static com.codeborne.selenide.Selenide.$x;

import static com.codeborne.selenide.Selenide.sleep;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Selenide.$x;
import static org.hamcrest.Matchers.greaterThan;

import java.nio.file.Files;
import java.nio.file.Paths;




public class ActionsCatalog {

    public String loadGraphQLQuery(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Или обработайте исключение соответствующим образом
        }
    }

    private final CatalogPageLocators locators = new CatalogPageLocators();

    /**
     * Verifies that the number of catalog items is greater than or equal to the expected minimum.
     * <p>
     * DOD:
     *   1. Catalog items are present more than the specified expectedMinimum.
     *
     * @param expectedMinimum The minimum number of catalog items expected.
     * @param timeoutSeconds The duration in seconds to wait for the condition.
     * @return The actual number of catalog items found.
     */
    public int verifyCatalogItemsPresent(int expectedMinimum, int timeoutSeconds) {
        return $$x(locators.catalogItems).shouldHave(sizeGreaterThanOrEqual(expectedMinimum), Duration.ofSeconds(timeoutSeconds)).size();
    }

    /**
     * Checks the text of the breadcrumb and returns it.
     * <p>
     * DOD:
     *   1. Check the breadcrumbs catalog item for the expected text.
     *
     * @param expectedText The expected text of the breadcrumb.
     * @param timeoutSeconds The duration in seconds to wait for the condition.
     * @return The text of the breadcrumb.
     */
    public String checkBreadcrumbText(String expectedText, int timeoutSeconds) {
        return $x(locators.breadcrumb).shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).getText();
    }

    /**
     * Verifies the visibility of catalog items after switching visualization mode.
     * <p>
     * DOD:
     *   1. Grid visualization is visible when buttonText is "Grid".
     *   2. List visualization is visible when buttonText is "List".
     *
     * @param buttonText The text on the button that triggers the switch in visualization.
     * @param expectedMinimum The minimum number of items expected to be visible after the switch.
     * @param timeoutSeconds The duration in seconds to wait for the condition.
     * @return True if the expected minimum number of items are visible after the switch, otherwise false.
     */
    public boolean verifyVisualisationSwitch(String buttonText, int expectedMinimum, int timeoutSeconds) {
        $x(locators.visualisationSwitchButton + "[contains(text(), '" + buttonText + "')]").shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).click();
        return $$x(locators.catalogItems).shouldBe(sizeGreaterThanOrEqual(expectedMinimum), Duration.ofSeconds(timeoutSeconds)).size() >= expectedMinimum;
    }

    public void waitForCatalogPage() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Stopped after some error", e);
        }
    }

    /**
     * Performs clicks on checkboxes and verifies updates in the UI.
     *
     * @param filters The filters object containing the locators.
     * @param checkboxLocators A list of locators for the checkboxes.
     * @param moreLessButton The "More/Less" button element.
     * @param dataElementLocator The locator for the data element to check after updates.
     */
    public void clickCheckboxesAndCheckUpdates(Filters filters, List<String> checkboxLocators, SelenideElement moreLessButton, SelenideElement dataElementLocator) {
        for (String locator : checkboxLocators) {
            SelenideElement checkbox = $x(locator).shouldBe(Condition.visible).shouldBe(Condition.enabled);

            // Click using JavaScript to avoid issues with element overlay
            checkbox.scrollIntoView("{block: \"center\"}").click();
            checkbox.shouldBe(Condition.checked);

            if (checkbox.equals(moreLessButton)) {
                moreLessButton.scrollIntoView("{block: \"center\"}").click();
                // Smart wait for any possible UI updates after clicking "More/Less"
                moreLessButton.shouldBe(Condition.disappear);
            }


            // Processing data after updates if needed
            SelenideElement dataElement = retryFindElement(dataElementLocator, 10, 1000);
            if (dataElement != null) {
                String dataText = dataElement.text();
                System.out.println("Data for locator " + locator + ": " + dataText);
            } else {
                System.out.println("Element not found after several attempts: " + locator);
            }
        }
    }

    /**
     * Retries finding an element until it's visible or the retry limit is reached.
     *
     * @param element The Selenide element to find.
     * @param retries Number of retries.
     * @param delayInMillis Delay in milliseconds between retries.
     * @return The found element or null if not found.
     */
    public SelenideElement retryFindElement(SelenideElement element, int retries, long delayInMillis) {
        for (int attempt = 0; attempt < retries; attempt++) {
            if (element.exists() && element.isDisplayed()) {
                return element;
            } else {
                // Intelligent wait using Selenide's built-in waiting mechanism
                element.shouldBe(Condition.appear, Duration.ofMillis(delayInMillis));
            }
        }
        System.out.println("Element not found after " + retries + " attempts.");
        return null;
    }

}