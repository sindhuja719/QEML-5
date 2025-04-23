package com.practice.ui.pages;

import com.practice.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.practice.utils.WebDriverUtils.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By signIn = By.linkText("sign in");

    public void clickSignIn() {
        String url = "http://localhost:8080/";
        getUrlDriver(url);
        WebElement element = getWait(120).until(ExpectedConditions.visibilityOf(findWebElement(getDriver(), signIn)));
        click(element);
    }

    @Override
    public String verifyTitle() {
        getWait(120).until(ExpectedConditions.titleIs("Sign in"));
        return getTitle(getDriver());
    }
}
