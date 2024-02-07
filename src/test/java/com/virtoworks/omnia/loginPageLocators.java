package com.virtoworks.omnia;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

// page_url = https://qa-opus.omniapartners.com/
public class loginPageLocators {

    public SelenideElement loginName = $("input[name='email']");

    public SelenideElement passwordName = $("input[type='password']");

    public SelenideElement submitButton = $("button[type='submit']");
}