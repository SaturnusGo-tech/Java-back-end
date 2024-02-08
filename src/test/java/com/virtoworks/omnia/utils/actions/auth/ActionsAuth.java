package com.virtoworks.omnia.utils.actions.auth;

import com.virtoworks.omnia.utils.locators.auth.loginPageLocators;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class ActionsAuth {

    loginPageLocators loginPageLocators = new loginPageLocators();

    public void userLogin(String login, String password){

        loginPageLocators.LOGIN_NAME.sendKeys(login);
        loginPageLocators.PASSWORD_NAME.sendKeys(password);
        loginPageLocators.SUBMIT_BUTTON.click();

    }
}