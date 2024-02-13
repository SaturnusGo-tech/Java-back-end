package com.virtoworks.omnia.utils.actions.auth;

import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import com.virtoworks.omnia.utils.locators.auth.logOutLocators;
import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ActionsAuth {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    // Initialize environment configuration
    EnvironmentConfig environmentConfig = new EnvironmentConfig("demo");

    /**
     * Logs in a user with provided credentials.
     *
     * @param login    The login ID of the user.
     * @param password The password of the user.
     * <p>
     * This method enters provided credentials into login and password fields
     * and clicks the login button. It waits for elements to be visible and enabled
     * before interaction, based on the specified environment configuration duration.
     */
    public void userLogin(String login, String password){
        loginPageLocators.LOGIN_NAME.shouldBe(visible, Duration.ofSeconds(environmentConfig.getEnvDuration())).sendKeys(login);
        loginPageLocators.PASSWORD_NAME.shouldBe(visible, Duration.ofSeconds(environmentConfig.getEnvDuration())).sendKeys(password);
        loginPageLocators.LOGIN_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
    }

    /**
     * Logs out the currently logged-in user.
     * <p>
     * This method clicks on the logout button in the user interface.
     * It ensures that the necessary UI elements are enabled before attempting
     * to log out, based on the specified environment configuration duration.
     */
    public void userLogOut(){
        logOutLocators.VC_ICON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
        logOutLocators.LOGOUT_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
    }
}
