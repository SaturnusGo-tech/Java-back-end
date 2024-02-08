package com.virtoworks.omnia.utils.locators;

public interface IAuthLocators {
    String EMAIL_INPUT = "input[placeholder='Enter your email address']";
    String PASSWORD_INPUT = "input[placeholder='Enter your password']";
    String LOGIN_BUTTON = "//span[text()='Log in']/ancestor::button";
    String ERROR_MESSAGE = "//div[contains(@class, 'error-class') and text()='Invalid login credentials']";
}
