package com.virtoworks.omnia.test.catalog;

import com.codeborne.selenide.Configuration;
import com.virtoworks.omnia.utils.actions.auth.ActionsAuth;
import com.virtoworks.omnia.utils.locators.auth.*;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;

import java.util.Map;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestBrowsingCatalog {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    ActionsAuth actionsAuth = new ActionsAuth();

    EnvironmentConfig environmentConfig = new EnvironmentConfig("demo");

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open(environmentConfig.getUrl());

        actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());

    }

    /*DOD:
            1.catalog is visible, catalog is scaling, catalog is consistency
            2.filters is present
    */

    @Test
    public void simpleBrowsingCatalog() {




    }

    @AfterEach
    public void tearDown(){

        actionsAuth.userLogOut();

    }
}