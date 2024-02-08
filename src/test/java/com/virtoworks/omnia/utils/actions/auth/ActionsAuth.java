package com.virtoworks.omnia.utils.actions.auth;

import com.virtoworks.omnia.utils.locators.auth.logOutLocators;
import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;

import java.util.Map;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ActionsAuth {

    loginPageLocators loginPageLocators = new loginPageLocators();
    logOutLocators logOutLocators = new logOutLocators();

    public void userLogin(String login, String password){

        loginPageLocators.LOGIN_BUTTON.should(exist);

        loginPageLocators.LOGIN_NAME.sendKeys(login);
        loginPageLocators.PASSWORD_NAME.sendKeys(password);
        loginPageLocators.LOGIN_BUTTON.shouldBe(visible).click();

    }

    public void userLogOut(){

        logOutLocators.VC_ICON.should(exist);

        logOutLocators.VC_ICON.shouldBe(visible).click();

        logOutLocators.LOGOUT_BUTTON.should(exist);

        logOutLocators.LOGOUT_BUTTON.shouldBe(visible).click();

    }
}