package com.virtoworks.omnia.test.checkPages.home;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.virtoworks.omnia.utils.actions.auth.ActionsAuth;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import com.virtoworks.omnia.utils.locators.auth.logOutLocators;
import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// page_url = https://qa-opus.omniapartners.com/home
public class TestCheckHomePage {

    com.virtoworks.omnia.utils.locators.auth.loginPageLocators loginPageLocators = new loginPageLocators();
    com.virtoworks.omnia.utils.locators.auth.logOutLocators logOutLocators = new logOutLocators();

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
        open(environmentConfig.getUrl());

        actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());

    }

    /*DOD:
        1.Categories are visible before expand 12 / after expand 17
    */
    @Test
    public void catalogNavigationCategoriesPerPageAmount() {

        $$x("//div[@class='flex w-full flex-col']").should(size(12), Duration.ofSeconds(environmentConfig.getEnvDuration()));

        $x("//a[contains(@href, '/home/categories')]")
                .should(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration()))
                .click();

        $$x("//div[@class='flex w-full flex-col']").should(size(17), Duration.ofSeconds(environmentConfig.getEnvDuration()));

    }

    /*DOD:

        1. Check Main page to Catalog navigation chain
    */
    @Test
    public void catalogNavigationMainToCatalogActive() {

        $x("//img[@src='/themes/assets/static/images/supplier/banner1.jpg']")
                .should(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration()))
                .click();

        assertEquals("Catalog", $("li[class*='font-medium']").getText(), "Fail: Catalog in the breadcrumbs is absent");

    }

    @AfterEach
    public void tearDown(){

        actionsAuth.userLogOut();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

    }
}