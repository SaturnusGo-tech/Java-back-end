package com.virtoworks.omnia.test.auth;

import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import com.virtoworks.omnia.utils.locators.auth.IAuthLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {

    private WebDriver driver;
    private EnvironmentConfig envConfig;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        this.envConfig = new EnvironmentConfig("qa");
    }

    public void visitHomePage() {
        driver.get(envConfig.getUrl());
    }

    public void fillEmail() {
        driver.findElement(By.cssSelector(IAuthLocators.EMAIL_INPUT)).sendKeys(envConfig.getEmail());
    }

    public void fillPassword() {
        driver.findElement(By.cssSelector(IAuthLocators.PASSWORD_INPUT)).sendKeys(envConfig.getPassword());
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath(IAuthLocators.LOGIN_BUTTON)).click();
    }

    public void login() {
        visitHomePage();
        fillEmail();
        fillPassword();
        clickLoginButton();
    }
}
