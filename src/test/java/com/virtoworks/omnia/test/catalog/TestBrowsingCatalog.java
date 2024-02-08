package com.virtoworks.omnia;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://qa-opus.omniapartners.com/sign-in?ReturnUrl=/
public class TestBrowsingCatalog {

    ActorWantToBrowseCatalog actorWantToBrowseCatalog = new ActorWantToBrowseCatalog();
    loginPageLocators loginPageLocators = new loginPageLocators();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("");
    }

    @Test
    public void loginToMainPage() {
        actorWantToBrowseCatalog.LogInAsUser();
        actorWantToBrowseCatalog.MoveToPage();



    }
}