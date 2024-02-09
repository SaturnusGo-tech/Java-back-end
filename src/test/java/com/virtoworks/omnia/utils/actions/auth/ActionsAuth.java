package com.virtoworks.omnia.utils.actions.auth;

import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import com.virtoworks.omnia.utils.locators.auth.logOutLocators;
import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class ActionsAuth {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    // solve it through class constructor
    EnvironmentConfig environmentConfig = new EnvironmentConfig("demo");


    public void userLogin(String login, String password){

        loginPageLocators.LOGIN_NAME.sendKeys(login);
        loginPageLocators.PASSWORD_NAME.sendKeys(password);
        loginPageLocators.LOGIN_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();

    }

    public void userLogOut(){

        logOutLocators.VC_ICON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();

        logOutLocators.LOGOUT_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();

    }
}