package com.virtoworks.omnia.test.auth;

import com.virtoworks.omnia.utils.env.EnvironmentConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class AuthPageTest {

    private WebDriver driver;
    private AuthPage authPage;
    private EnvironmentConfig envConfig;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        envConfig = new EnvironmentConfig("qa");
        authPage = new AuthPage(driver);
    }

    @Test
    public void testLogin() {
        authPage.visitHomePage();
        authPage.fillEmail(envConfig.getEmail());
        authPage.fillPassword(envConfig.getPassword());
        authPage.clickLoginButton();

        WebElement educationImage = driver.findElement(By.xpath("//img[contains(@alt, 'Education')][1]"));
        assertTrue("Education image is not displayed after successful login",
                educationImage.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
