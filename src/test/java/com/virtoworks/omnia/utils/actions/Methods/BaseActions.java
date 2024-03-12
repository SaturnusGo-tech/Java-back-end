package com.virtoworks.omnia.utils.actions.Methods;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class BaseActions {

    public static void click(SelenideElement element) {
        element.click();
    }

    public static void setText(SelenideElement element, String text) {
        element.setValue(text);
    }

    public static void waitForSeconds(long seconds) {
        sleep(seconds * 1000);
    }

    /**
     * Retries finding an element until it becomes visible or the retry limit is reached.
     *
     * @param element       Element to find.
     * @param retries       Maximum number of retries.
     * @param delayInMillis Delay between retries in milliseconds.
     * @return The found element, or null if not found within the retry limit.
     */
    public SelenideElement retryFindElement(SelenideElement element, int retries, long delayInMillis) {
        for (int attempt = 0; attempt < retries; attempt++) {
            if (element.exists() && element.isDisplayed()) {
                return element;
            } else {
                Selenide.sleep(delayInMillis);
            }
        }
        System.out.println("Element not found after " + retries + " attempts.");
        return null;
    }

    /**
     * Scrolls through a container to ensure all checkboxes are visible.
     *
     * @param filterContainer Container containing checkboxes.
     * @param checkboxLocator Locator for checkboxes within the container.
     * @param expectedCount   Expected number of checkboxes to find.
     * @return Number of checkboxes found.
     */
    public int scrollToFindAllData(SelenideElement filterContainer, String checkboxLocator, int expectedCount) {
        int itemsCount = 0;
        while (itemsCount < expectedCount) {
            itemsCount = filterContainer.$$x(checkboxLocator).filter(visible).size();
            if (itemsCount < expectedCount) {
                executeJavaScript("arguments[0].scrollTop = arguments[0].scrollHeight", filterContainer);
                Selenide.sleep(1000);
            }
        }
        return itemsCount;
    }
}
