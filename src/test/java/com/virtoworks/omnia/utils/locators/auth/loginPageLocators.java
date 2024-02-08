package com.virtoworks.omnia.utils.locators.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class loginPageLocators {

    public SelenideElement LOGIN_NAME = $("input[name='email']");

    public SelenideElement PASSWORD_NAME = $("input[type='password']");

    public SelenideElement SUBMIT_BUTTON = $("button[type='submit']");
}
