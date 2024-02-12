package com.virtoworks.omnia.test.checkPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.virtoworks.omnia.utils.actions.auth.ActionsAuth;
import com.virtoworks.omnia.utils.locators.auth.*;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCheckCatalogPage {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    ActionsAuth actionsAuth = new ActionsAuth();

    EnvironmentConfig environmentConfig = new EnvironmentConfig("qa");

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadStrategy = "normal"; // eager / normal / none
        // Use it only for debug mode only
        //Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open(environmentConfig.getUrl() + "catalog");

        actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());

    }

    /*DOD:

        1. Catalog items are present more than 12
        2. Check the breadcrumbs catalog item
    */
    @Test
    public void catalogNavigationCatalogItemsPerPageAmount() {


        $$x("//div[contains(@class, 'w-full')]").should(sizeGreaterThanOrEqual(12), Duration.ofSeconds(environmentConfig.getEnvDuration()));

        assertEquals("Catalog", $("li[class*='font-medium']").getText(), "Fail: Catalog in the breadcrumbs is absent");

    }

    /*DOD:

        1. Grid visualisation is visible
        2. List visualisation is visible
    */
    @Test
    public void catalogNavigationGridListVisualisation() {

    $x("//button[contains(@class, 'text-primary')]").shouldHave(Condition.text("List"), Duration.ofSeconds(environmentConfig.getEnvDuration())).click();

        $$x("//div[contains(@class, 'grid')]").should(sizeGreaterThanOrEqual(12), Duration.ofSeconds(environmentConfig.getEnvDuration()));

    $x("//button[contains(@class, 'text-primary')]").shouldHave(Condition.text("Grid"), Duration.ofSeconds(environmentConfig.getEnvDuration())).click();

        $$x("//div[contains(@class, 'w-full')]").should(sizeGreaterThanOrEqual(12), Duration.ofSeconds(environmentConfig.getEnvDuration()));
    }

    @AfterEach
    public void tearDown(){

        actionsAuth.userLogOut();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

    }
}