package com.virtoworks.omnia.utils.actions.auth;

import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import com.virtoworks.omnia.utils.locators.auth.logOutLocators;
import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

/**
 * Class responsible for handling user authentication, including login and logout operations.
 */
public class ActionsAuth {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    // Configuration for environmental specific settings such as timeouts.
    EnvironmentConfig environmentConfig = new EnvironmentConfig("demo");

    /**
     * Performs user login using specified credentials.
     * <p>
     * Waits for the login and password input fields to be visible before entering the credentials,
     * then clicks the login button. Visibility and enablement checks use timeouts specified by environment configuration.
     *
     * @param login The user's login identifier.
     * @param password The user's password.
     */
    public void userLogin(String login, String password) {
        loginPageLocators.LOGIN_NAME.shouldBe(visible, Duration.ofSeconds(environmentConfig.getEnvDuration())).sendKeys(login);
        loginPageLocators.PASSWORD_NAME.shouldBe(visible, Duration.ofSeconds(environmentConfig.getEnvDuration())).sendKeys(password);
        loginPageLocators.LOGIN_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
    }

    /**
     * Logs out the currently logged-in user.
     * <p>
     * Initiates the logout process by clicking the user's profile icon to reveal the logout option,
     * then clicks the logout button. It ensures that necessary UI elements are enabled
     * based on the specified environment configuration duration before attempting to log out.
     */
    public void userLogOut() {
        logOutLocators.VC_ICON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
        logOutLocators.LOGOUT_BUTTON.shouldBe(enabled, Duration.ofSeconds(environmentConfig.getEnvDuration())).click();
    }
}
