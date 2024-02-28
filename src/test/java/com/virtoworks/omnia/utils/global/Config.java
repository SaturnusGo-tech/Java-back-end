package com.virtoworks.omnia.utils.global;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import com.virtoworks.omnia.utils.actions.auth.ActionsAuth;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;

import static com.codeborne.selenide.Selenide.open;

public class Config {

    private final EnvironmentConfig environmentConfig = new EnvironmentConfig("qa");
    private final ActionsAuth actionsAuth = new ActionsAuth();

    /**
     * Global setup for all tests, configuring browser size and page load strategy,
     * and adding Allure Selenide listener for enhanced test reporting.
     */
    public void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadStrategy = "normal";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    /**
     * Setup for each test, configuring browser capabilities, opening the base URL
     * appended with the given endpoint, and performing user login.
     *
     * @param endpoint The specific part of the URL to be appended to the base URL.
     */
    public void setUp(String endpoint) {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        String baseUrl = environmentConfig.getUrl().endsWith("/") ? environmentConfig.getUrl() : environmentConfig.getUrl() + "/";
        open(baseUrl + endpoint);

        if (Selenide.$$(".vc-button.vc-button--size--xs.vc-button--color--neutral.vc-button--outline--neutral.vc-button--icon.ml-4").isEmpty()) {
            actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());
        }
    }

    /**
     * Retrieves the environment-specific duration setting for timeouts.
     *
     * @return Duration in seconds as configured in the environment settings.
     */
    public int getEnvDuration() {
        return environmentConfig.getEnvDuration();
    }

    /**
     * Tear down actions after each test, logging out the user and clearing browser cookies and local storage.
     */
    public void tearDown() {
        actionsAuth.userLogOut();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
