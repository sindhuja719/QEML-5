package com.practice.ui.pages;

import com.practice.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.practice.utils.WebDriverUtils.*;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By rememberMe = By.id("rememberMe");
    private final By signInButton = By.cssSelector("button[type='submit']");
    private final By failed=By.tagName("strong");

    public void login(String username, String password, Boolean rememberMe) {
        WebElement user = findWebElement(getDriver(), this.username);
        WebElement pass = findWebElement(getDriver(), this.password);
        WebElement rememberMeCheckbox = findWebElement(getDriver(), this.rememberMe);
        WebElement signIn = findWebElement(getDriver(), signInButton);

        sendKeys(user, username);
        sendKeys(pass, password);
        if (rememberMe) {
            click(rememberMeCheckbox);
        }
        click(signIn);
    }

    public String invalidMessage() {
        return findWebElement(getDriver(), failed).getText();
    }

    @Override
    public String verifyTitle() {
        getWait(120).until(ExpectedConditions.titleIs("Welcome, Java Hipster!"));
        return getTitle(getDriver());
    }
}
