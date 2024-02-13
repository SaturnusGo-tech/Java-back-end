package com.virtoworks.omnia.utils.actions.catalog;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import com.virtoworks.omnia.utils.locators.catalog.CatalogPageLocators;
import java.time.Duration;

public class ActionsCatalog {

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
        return $x(locators.breadcrumb).shouldBe(Condition.visible, Duration.ofSeconds(timeoutSeconds)).getText();
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
        $x(locators.visualisationSwitchButton + "[contains(text(), '" + buttonText + "')]").shouldBe(Condition.visible, Duration.ofSeconds(timeoutSeconds)).click();
        return $$x(locators.catalogItems).shouldBe(sizeGreaterThanOrEqual(expectedMinimum), Duration.ofSeconds(timeoutSeconds)).size() >= expectedMinimum;
    }
}
