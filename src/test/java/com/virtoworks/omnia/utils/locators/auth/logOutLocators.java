package com.virtoworks.omnia.utils.locators.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class logOutLocators {

    public SelenideElement LOGOUT_BUTTON = $("button[class*='ml-4']");

    public  SelenideElement VC_ICON = $("svg[class='vc-icon ms-1.5 text-[--color-accent-200] [--vc-icon-size:1rem] lg:text-[--color-primary-500] lg:[--vc-icon-size:0.625rem]']");

    public SelenideElement MOBILE_LOGOUT_BUTTON = $("button[class*='ml-12']");

}