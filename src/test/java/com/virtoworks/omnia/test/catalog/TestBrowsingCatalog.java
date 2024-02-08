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
import static com.codeborne.selenide.Selenide.*;

public class TestBrowsingCatalog {

    loginPageLocators loginPageLocators = new loginPageLocators();

    ActionsAuth actionsAuth = new ActionsAuth();

    EnvironmentConfig environmentConfig = new EnvironmentConfig("qa");

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        //Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open(environmentConfig.getUrl());

        actionsAuth.userLogin(environmentConfig.getEmail(), environmentConfig.getPassword());

    }

    @Test
    public void simpleBrowsingCatalog() {




    }
}