package com.virtoworks.omnia.utils.global;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.virtoworks.omnia.utils.actions.auth.ActionsAuth;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

/**
 * Provides configuration setup for browser and global test environment.
 * Handles initial configuration for tests including browser options, page load strategy,
 * and integrating Allure reports for better test insights.
 */
public class Config {

    private final EnvironmentConfig environmentConfig = new EnvironmentConfig("qa");
    private final ActionsAuth actionsAuth = new ActionsAuth();

    /**
     * Performs global setup for all tests.
     * Configures the browser size, page load strategy, and adds an Allure Selenide listener
     * for capturing screenshots and page source on test failure, enhancing the test reports.
     */
    public void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadStrategy = "normal";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    /**
     * Sets up test-specific configurations such as browser capabilities,
     * opens the base URL appended with a specific endpoint, and logs in the user if necessary.
     *
     * @param endpoint The specific part of the URL to navigate to at the start of a test.
     */
    public void setUp(String endpoint) {
        // Configure Chrome to allow all remote origins
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        // Ensure the base URL ends with a slash for proper concatenation
        String baseUrl = environmentConfig.getUrl().endsWith("/") ? environmentConfig.getUrl() : environmentConfig.getUrl() + "/";
        open(baseUrl + endpoint);

        // Check if user is already logged in before attempting login
        if (Selenide.$$(".vc-button.vc-button--size--xs.vc-button--color--neutral.vc-button--outline--neutral.vc-button--icon.ml-4").isEmpty()) {
            actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());
        }
    }

    /**
     * Retrieves the timeout duration from environment configuration.
     *
     * @return Timeout duration in seconds.
     */
    public int getEnvDuration() {
        return environmentConfig.getEnvDuration();
    }

    /**
     * Cleans up after each test by logging out the user and clearing browser cookies and local storage.
     * This ensures each test starts with a clean state.
     */
    public void tearDown() {
        actionsAuth.userLogOut();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
