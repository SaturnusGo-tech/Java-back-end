package com.virtoworks.omnia.test.catalog;

import com.codeborne.selenide.Configuration;
import com.virtoworks.omnia.utils.actions.*;
import com.virtoworks.omnia.utils.locators.auth.*;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import com.virtoworks.omnia.utils.env.EnvironmentConfig;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class TestBrowsingCatalog {

    ActorWantToBrowseCatalog actorWantToBrowseCatalog = new ActorWantToBrowseCatalog();
    loginPageLocators loginPageLocators = new loginPageLocators();

    EnvironmentConfig environmentConfig = new EnvironmentConfig("qa");

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
    }

    @Test
    public void loginToMainPage() {
    /*    actorWantToBrowseCatalog.LogInAsUser();
        actorWantToBrowseCatalog.MoveToPage();
    */


    }
}