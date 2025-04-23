package com.practice.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static com.practice.utils.WebDriverUtils.getTitle;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void getUrlDriver(String url) {
        getDriver().get(url);
    }

    public void getImplicitlyWait(int duration) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
    }

    public Wait<WebDriver> getWait(int duration) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(duration));
    }

    public Wait<WebDriver> getWait(int duration, int poll) {
        return new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(poll))
                .ignoring(TimeoutException.class);
    }

    public String verifyTitle() {
        return getTitle(getDriver());
    }

    public void closeDriver() {
        getDriver().close();
    }
}
